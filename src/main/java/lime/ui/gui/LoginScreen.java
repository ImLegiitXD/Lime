package lime.ui.gui;

import lime.core.Lime;
import lime.management.FontManager;
import lime.ui.fields.ButtonField;
import lime.ui.fields.TextField;
import lime.utils.other.ChatUtils;
import lime.utils.other.Timer;
import lime.utils.other.security.User;
import lime.utils.render.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;

public class LoginScreen extends GuiScreen {
    private TextField textField;
    private String status;
    private final Timer timer = new Timer();

    @Override
    public void initGui() {
        textField = new TextField(FontManager.ProductSans20.getFont(), "UID", this.width / 2F - 75, this.height / 2F - 10, 150, 20);
        status = "§7Enter your UID";

        this.customButtonList.add(new ButtonField(
                FontManager.ProductSans20.getFont(),
                "Log in",
                this.width / 2F - 75, this.height / 2F + 12,
                150, 20,
                new Color(41, 41, 41, 255),
                () -> {
                    String uid = textField.getText();

                    if(uid == null || uid.trim().isEmpty()) {
                        status = "§cUID cannot be empty.";
                        return;
                    }

                    Lime.getInstance().setUser(new User(uid, "LOCAL_HWID"));
                    status = "§aLogged in as: " + uid;
                    timer.reset();
                }
        ));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Lime.getInstance().initClient();

        GuiScreen.drawRect(0, 0, mc.displayWidth, mc.displayHeight, new Color(21, 21, 21).getRGB());
        Gui.drawRect(this.width / 2F - 80, this.height / 2F - 15, this.width / 2F + 80, this.height / 2F + 50, new Color(25, 25, 25, 225).getRGB());
        RenderUtils.drawHollowBox(this.width / 2F - 80, this.height / 2F - 15, this.width / 2F + 80, this.height / 2F + 50, 0.5f, new Color(0, 255, 0, 100).getRGB());

        textField.drawTextField(mouseX, mouseY);
        FontManager.ProductSans20.getFont().drawStringWithShadow(
                status,
                this.width / 2F - (FontManager.ProductSans20.getFont().getStringWidth(ChatUtils.removeColors(status)) / 2F),
                this.height / 2F + 34,
                -1
        );
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        textField.keyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        textField.mouseClicked();
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
