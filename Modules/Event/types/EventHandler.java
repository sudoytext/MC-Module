package ytext.custom.client.event.types;

import ytext.custom.client.event.EventManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Flags that method should receive the specified event in the parameter.
 * Needs the class to be registered.
 *
 * @see EventManager for how to register listeners
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    float cooldown() default 0.0F;

    /**
     * Event impact on call
     */
    EventImpact impact() default EventImpact.NORMAL;


}