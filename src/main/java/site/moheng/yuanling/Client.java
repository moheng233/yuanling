package site.moheng.yuanling;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import site.moheng.yuanling.block_render.TranslationTableRender;
import site.moheng.yuanling.model.ModModelProvider;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModClientScreen.init();

        BlockEntityRendererRegistry.register(ModBlockEntitys.TRANSLATION_TABLE_ENTITY, TranslationTableRender::new);
        
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(rm -> new ModModelProvider());
    }
    
}
