package ytext.custom.client.modules.impl.misc.visual;

import ytext.custom.client.event.impl.Render2DEvent;
import ytext.custom.client.event.types.EventHandler;
import ytext.custom.client.modules.types.Module;
import ytext.custom.client.modules.types.ModuleCategory;
import ytext.custom.client.modules.types.ModuleInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@ModuleInfo(name = "Keystrokes", description = "", type = ModuleCategory.VISUAL)
public class KeystrokeModule extends Module {
    private Minecraft mc = Minecraft.getMinecraft();
    private float smoothAnimationFactor = 0.2f;
    private float currentOpacityW = 0f;
    private float currentOpacityA = 0f;
    private float currentOpacityS = 0f;
    private float currentOpacityD = 0f;
    private float currentOpacitySpace = 0f;

    @EventHandler
    public void onRender2D(Render2DEvent e) {
        GameSettings set = mc.gameSettings;
        KeyBinding w = set.keyBindForward;
        KeyBinding s = set.keyBindBack;
        KeyBinding a = set.keyBindLeft;
        KeyBinding d = set.keyBindRight;
        KeyBinding space = set.keyBindJump;

        updateAnimation(w.isKeyDown(), a.isKeyDown(), s.isKeyDown(), d.isKeyDown(), space.isKeyDown());

        ScaledResolution sc = new ScaledResolution(mc);
        int centerX = sc.getScaledWidth() / 2;
        int baseY = sc.getScaledHeight() / 2 + 20;


        drawRoundedRect(centerX - 35, baseY - 50, "W", currentOpacityW);
        drawRoundedRect(centerX - 70, baseY - 15, "A", currentOpacityA);
        drawRoundedRect(centerX - 35, baseY - 15, "S", currentOpacityS);
        drawRoundedRect(centerX, baseY - 15, "D", currentOpacityD);


        drawRoundedRect(centerX - 70, baseY + 20, "Space", currentOpacitySpace, 140, 30);
    }

    private void updateAnimation(boolean wPressed, boolean aPressed, boolean sPressed, boolean dPressed, boolean spacePressed) {

        float speed = smoothAnimationFactor;
        currentOpacityW += (wPressed ? speed : -speed);
        currentOpacityA += (aPressed ? speed : -speed);
        currentOpacityS += (sPressed ? speed : -speed);
        currentOpacityD += (dPressed ? speed : -speed);
        currentOpacitySpace += (spacePressed ? speed : -speed);


        currentOpacityW = clamp(currentOpacityW);
        currentOpacityA = clamp(currentOpacityA);
        currentOpacityS = clamp(currentOpacityS);
        currentOpacityD = clamp(currentOpacityD);
        currentOpacitySpace = clamp(currentOpacitySpace);
    }

    private float clamp(float value) {
        return Math.max(0, Math.min(1, value));
    }


    private void drawRoundedRect(int x, int y, String text, float opacity) {
        drawRoundedRect(x, y, text, opacity, 32, 32);
    }

    private void drawRoundedRect(int x, int y, String text, float opacity, int width, int height) {

        int baseColor = new Color(0, 0, 0, (int) (opacity * 160)).getRGB();
        int borderColor = new Color(255, 255, 255, (int) (opacity * 200)).getRGB();


        drawRoundedRectWithBorder(x, y, width, height, 8, baseColor, borderColor);


        FontRenderer fr = mc.fontRendererObj;  // Use Minecraft instance to get FontRenderer
        int textWidth = fr.getStringWidth(text);
        fr.drawStringWithShadow(text, x + (width - textWidth) / 2, y + (height - fr.FONT_HEIGHT) / 2, Color.WHITE.getRGB());
    }


    private void drawRoundedRectWithBorder(int x, int y, int width, int height, int radius, int bgColor, int borderColor) {

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);


        GL11.glColor4f((bgColor >> 16 & 255) / 255.0F, (bgColor >> 8 & 255) / 255.0F, (bgColor & 255) / 255.0F, (bgColor >> 24 & 255) / 255.0F);
        Gui.drawRect(x, y, x + width, y + height, bgColor);


        GL11.glLineWidth(1.5F);
        GL11.glColor4f((borderColor >> 16 & 255) / 255.0F, (borderColor >> 8 & 255) / 255.0F, (borderColor & 255) / 255.0F, (borderColor >> 24 & 255) / 255.0F);
        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x + width - radius, y);
        GL11.glVertex2f(x + width, y + radius);
        GL11.glVertex2f(x + width, y + height - radius);
        GL11.glVertex2f(x + width - radius, y + height);
        GL11.glVertex2f(x + radius, y + height);
        GL11.glVertex2f(x, y + height - radius);
        GL11.glVertex2f(x, y + radius);
        GL11.glEnd();


        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }
}

