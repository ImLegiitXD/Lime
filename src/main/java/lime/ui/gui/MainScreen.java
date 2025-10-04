package lime.ui.gui;

import lime.core.Information;
import lime.core.Lime;
import lime.management.FontManager;
import lime.ui.fields.ButtonField;
import lime.utils.render.RenderUtils;
import net.minecraft.client.gui.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import oshi.SystemInfo;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class MainScreen extends GuiScreen {
    public static boolean clickGui;

    public static boolean anim = false;

    @Override
    public void initGui() {
        System.gc();
        clickGui = false;
        final Color color = new Color(41, 41, 41, 255);

        this.customButtonList.add(new ButtonField(FontManager.ProductSans20.getFont(), "Singleplayer", width / 2F - 75, height / 2F - 22 - 22, 150, 20, false, color, true, new Color(255, 255, 255, 200), !anim, () -> {
            mc.displayGuiScreen(new GuiSelectWorld(this));
        }));
        this.customButtonList.add(new ButtonField(FontManager.ProductSans20.getFont(), "Multiplayer", width / 2F - 75, height / 2F - 22, 150, 20, false, color, true, new Color(255, 255, 255, 200), !anim, () -> {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        }));
        this.customButtonList.add(new ButtonField(FontManager.ProductSans20.getFont(), "Alt Manager", width / 2F - 75, height / 2F, 150, 20, false, color, true, new Color(255, 255, 255, 200), !anim, () -> {
            mc.displayGuiScreen(Lime.getInstance().getAltManager().getAltManagerScreen());
        }));
        this.customButtonList.add(new ButtonField(FontManager.ProductSans20.getFont(), "Options", width / 2F - 75, height / 2F + 22, 150, 20, false, color, true, new Color(255, 255, 255, 200), !anim, () -> {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }));

        this.customButtonList.add(new ButtonField(FontManager.ProductSans20.getFont(), "Exit", width / 2F - 75, height / 2F + 22 + 22, 150, 20, false, color, true, new Color(255, 255, 255, 200), !anim, () -> {
            mc.shutdown();
        }));
        anim = true;
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiScreen.drawRect(0, 0, mc.displayWidth, mc.displayHeight, new Color(21, 21, 21).getRGB());
        FontManager.ProductSans20.getFont().drawStringWithShadow("Made by " + Information.getAuthor(), width - (FontManager.ProductSans20.getFont().getStringWidth("Made by" + Information.getAuthor())) - 6, height - FontManager.ProductSans20.getFont().getFontHeight(), -1);
        FontManager.ProductSans20.getFont().drawStringWithShadow("Lime " + Information.getVersion() + " | Build: " + Information.getBuild(), 1, height - (FontManager.ProductSans20.getFont().getFontHeight()), -1);

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        RenderUtils.prepareScissorBox((width / 2F - (FontManager.ProductSans76.getFont().getStringWidth("Lime")  / 2F)), this.height / 2F - 150, (width / 2F - (FontManager.ProductSans76.getFont().getStringWidth("Lime")  / 2F)) + (FontManager.ProductSans76.getFont().getStringWidth("Lime")), this.height / 2F - 150 + FontManager.ProductSans76.getFont().getFontHeight() - 5);
        FontManager.ProductSans76.getFont().drawStringWithShadow(EnumChatFormatting.GREEN + "L§fime", (width / 2F - (FontManager.ProductSans76.getFont().getStringWidth("Lime")  / 2F)), this.height / 2F - 150, -1);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        ScaledResolution sr = new ScaledResolution(mc);
        if(hover(sr.getScaledWidth() - 72, 3, mouseX, mouseY, 32, 40)) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        if(hover(sr.getScaledWidth() -34, 3, mouseX, mouseY, 32, 40)) {
            mc.shutdown();
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == 54) {
            clickGui = true;
            mc.displayGuiScreen(Lime.getInstance().getClickGUI2());
        }
        super.keyTyped(typedChar, keyCode);
    }
}
