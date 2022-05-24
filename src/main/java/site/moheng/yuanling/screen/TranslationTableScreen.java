package site.moheng.yuanling.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import site.moheng.yuanling.YuanLing;

public class TranslationTableScreen extends HandledScreen<TranslationTableScreenHandler> {

    private static final Identifier BACKAGE_TEXTURE = new Identifier(YuanLing.MOD_ID, "textures/gui/programmer.png");

    public TranslationTableScreen(TranslationTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrixStack, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKAGE_TEXTURE);

        drawTexture(matrixStack, x, y, 0, 0, 172, 183);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        // super.drawForeground(matrices, mouseX, mouseY);
    }

}
