package ytext.custom.client.event;

import ytext.custom.client.event.types.Event;
import ytext.custom.client.event.types.EventHandler;
import org.lwjgl.util.Timer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventManager {
    private final List<Object> listeners = new ArrayList<>();
    private final Timer timer = new Timer();
    private final Map<Object, Float> points = new HashMap<>();

    /**
     * Calls specified handlers that contains this event as a parameter in any registered listeners in the EventManager class.
     *
     * @param event: event that should be called
     * @see Event
     * @see EventHandler
     */
    public void callEvent(Event event) {
        for (Object listener : listeners.toArray()) {
            Class<?> cls = listener.getClass();
            Arrays.stream(cls.getMethods())
                    .filter(m -> m.isAnnotationPresent(EventHandler.class)
                            && m.getParameters().length == 1
                            && m.getParameterTypes()[0].equals(event.getClass())).forEach(m -> {
                        try {
                            EventHandler handler = m.getAnnotation(EventHandler.class);
                            var cooldown = handler.cooldown();
                            var prevTime = points.getOrDefault(listener, 0.0F);
                            if (cooldown <= timer.getTime() - prevTime) {
                                m.invoke(listener, event);
                                if (cooldown > 0)
                                    points.put(listener, timer.getTime());
                            }

                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    /**
     * Registers the specified listener.
     *
     * @param listener: given listener for registry
     * @apiNote event handlers in the listener class must have an EventHandler annotation tag.
     * @see EventHandler
     */
    public void register(Object listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    /**
     * Unregisters the specified listener if exists.
     *
     * @param listener: given listener for removing
     */
    public void unregister(Object listener) {
        listeners.remove(listener);
    }

    /**
     * Removes all registered listeners in contained in the list.
     *
     * @apiNote use {@link #unregister} for removing a specified listener.
     */
    public void clear() {
        listeners.clear();
    }
}
