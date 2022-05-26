package site.moheng.yuanling.block_render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import site.moheng.yuanling.ModBlocks;
import site.moheng.yuanling.block_entitys.TranslationTableEntity;
import site.moheng.yuanling.blocks.TranslationTable;

public class TranslationTableRender implements BlockEntityRenderer<TranslationTableEntity> {

    public TranslationTableRender(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(TranslationTableEntity blockEntity, float delta, MatrixStack matrixs,
            VertexConsumerProvider vertexConsumers, int light,
            int overlay) {

        var item = blockEntity.inventory.getStack(0);

        if (!item.isEmpty()) {
            matrixs.push();

            matrixs.translate(0.5, 0.0625 * 16, 0.5);
            matrixs.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90));

            var dir = blockEntity.getWorld().getBlockState(blockEntity.getPos()).get(TranslationTable.FACING);

            switch (dir) {
                case NORTH:
                    break;
                case EAST:
                    break;
                case SOUTH:
                    break;
                case WEST:
                    break;
                default:
                    break;

            }

            MinecraftClient.getInstance().getItemRenderer().renderItem(item, ModelTransformation.Mode.GROUND, light,
                    overlay, matrixs, vertexConsumers, 0);

            matrixs.pop();
        }
    }

}
