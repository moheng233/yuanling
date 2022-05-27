package site.moheng.yuanling;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import site.moheng.yuanling.block_render.TranslationTableRender;
import site.moheng.yuanling.model.ModModelProvider;
import site.moheng.yuanling.screen.hud.TableHUD;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 初始化MOD场景
        ModClientScreen.init();

        // 初始化方块仅客户端内容
        ModBlocks.initClient();

        BlockEntityRendererRegistry.register(ModBlockEntitys.TRANSLATION_TABLE_ENTITY, TranslationTableRender::new);
        
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModModelProvider());

        // 初始化HUD
        HudRenderCallback.EVENT.register(new TableHUD()::onHudRender);
    }
}
