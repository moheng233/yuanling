package site.moheng.yuanling;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import site.moheng.yuanling.block_entitys.TranslationTableEntity;

public class ModBlockEntitys {
    public static BlockEntityType<TranslationTableEntity> TRANSLATION_TABLE_ENTITY;

    public static void init() {
        TRANSLATION_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(YuanLing.MOD_ID, "translation_table"),
                FabricBlockEntityTypeBuilder.create(TranslationTableEntity::new, ModBlocks.TRANSLATION_TABLE).build(null));
    }
}
