package site.moheng.yuanling.screen.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.ColorHelper;
import site.moheng.yuanling.YuanLing;
import site.moheng.yuanling.block_entitys.TranslationTableEntity;
import site.moheng.yuanling.blocks.TranslationTable;

public class TableHUD extends DrawableHelper implements HudRenderCallback {

    private MinecraftClient client = MinecraftClient.getInstance();
    private Window windows = client.getWindow();

    public int getCenterX(int offset) {
        return windows.getScaledWidth() / 2 + offset;
    }

    public int getCenterX() {
        return getCenterX(0);
    }

    public int getCenterY(int offset) {
        return windows.getScaledHeight() / 2 + offset;
    }

    public int getCenterY() {
        return getCenterY(0);
    }

    @Override
    public void onHudRender(MatrixStack matrixs, float tickDelta) {
        HitResult hit = client.crosshairTarget;

        if(hit instanceof BlockHitResult bhit) {
            var state = client.player.getWorld().getBlockState(bhit.getBlockPos());

            if(state.getBlock() instanceof TranslationTable table) {
                var entity = (TranslationTableEntity)client.player.getWorld().getBlockEntity(bhit.getBlockPos());
                var stack = entity.inventory.getStack(0);

                if(!stack.isEmpty()) {
                    drawCenteredText(matrixs, client.textRenderer, stack.getName(), getCenterX(), getCenterY(-30), stack.getRarity().formatting.getColorValue());
                }
            }
        }

    }

}
