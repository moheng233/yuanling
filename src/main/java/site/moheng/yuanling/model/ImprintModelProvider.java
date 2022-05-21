package site.moheng.yuanling.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import com.mojang.datafixers.util.Pair;

import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.BlockRenderView;
import site.moheng.yuanling.YuanLing;

public class ImprintModelProvider implements UnbakedModel, BakedModel, FabricBakedModel {
    private static final Identifier DEFAULT_ITEM_MODEL = new Identifier("minecraft:item/generated");

    private final SpriteIdentifier voidImprintIdentifier = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE,
            new Identifier(YuanLing.MOD_ID, "item/imprint"));

    private Sprite voidImprintSprite;

    private Mesh mesh;

    private ModelTransformation transformation;

    @Override
    public Collection<Identifier> getModelDependencies() {
        return Arrays.asList(DEFAULT_ITEM_MODEL);
    }

    @Override
    public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> var1,
            Set<Pair<String, String>> var2) {
        return Arrays.asList(voidImprintIdentifier);
    }

    @Override
    public BakedModel bake(ModelLoader modelLoader, Function<SpriteIdentifier, Sprite> sprGet, ModelBakeSettings var3,
            Identifier var4) {
        JsonUnbakedModel defaultItemModel = (JsonUnbakedModel) modelLoader.getOrLoadModel(DEFAULT_ITEM_MODEL);
        transformation = defaultItemModel.getTransformations();


        voidImprintSprite = sprGet.apply(voidImprintIdentifier);
        var renderer = RendererAccess.INSTANCE.getRenderer();
        var builder = renderer.meshBuilder();
        var emitter = builder.getEmitter();
        
        // JsonUnbakedModel model = (JsonUnbakedModel) modelLoader.getOrLoadModel(new Identifier(YuanLing.MOD_ID, "item/v_imprint"));

        emitter.square(Direction.DOWN, 0.0f, 0.0f, 1.0f, 1.0f, 0.4f);
        emitter.spriteBake(0, voidImprintSprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.spriteColor(0, -1, -1, -1, -1);
        emitter.emit();
        
        emitter.square(Direction.UP, 0.0f, 0.0f, 1.0f, 1.0f, 0.4f);
        emitter.spriteBake(0, voidImprintSprite, MutableQuadView.BAKE_LOCK_UV);
        emitter.spriteColor(0, -1, -1, -1, -1);
        emitter.emit();

        mesh = builder.build();

        return this;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos,
            Supplier<Random> randomSupplier, RenderContext context) {

    }

    @Override
    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
        

        context.meshConsumer().accept(mesh);
    }

    @Override
    public List<BakedQuad> getQuads(BlockState var1, Direction var2, Random var3) {
        return Collections.emptyList();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean hasDepth() {
        return false;
    }

    @Override
    public boolean isSideLit() {
        return true;
    }

    @Override
    public boolean isBuiltin() {
        return false;
    }

    @Override
    public Sprite getParticleSprite() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelTransformation getTransformation() {
        return ModelHelper.MODEL_TRANSFORM_BLOCK;
    }

    @Override
    public ModelOverrideList getOverrides() {
        return ModelOverrideList.EMPTY;
    }

}
