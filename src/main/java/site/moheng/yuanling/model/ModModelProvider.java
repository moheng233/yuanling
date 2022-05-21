package site.moheng.yuanling.model;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelProviderException;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import site.moheng.yuanling.YuanLing;

public class ModModelProvider implements ModelResourceProvider {
    public static final UnbakedModel IMPRINT_ITEM_MODEL_PROVIDER = new ImprintModelProvider();

    public static final Identifier VOID_IMPRINT_MODEL = new Identifier(YuanLing.MOD_ID, "item/imprint");

    @Override
    public @Nullable UnbakedModel loadModelResource(Identifier resourceId, ModelProviderContext context) {
        if (resourceId.equals(VOID_IMPRINT_MODEL)) {
            YuanLing.LOGGER.info("成功加载自定义");
            return IMPRINT_ITEM_MODEL_PROVIDER;
        }

        return null;
    }

}
