package site.moheng.yuanling;

import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import site.moheng.yuanling.blocks.LingShiCluster;
import site.moheng.yuanling.blocks.SpellPlate;
import site.moheng.yuanling.blocks.TranslationTable;

public class ModBlocks implements BlockRegistryContainer {

    public static final Block LINGSHI_CLUSTER = new LingShiCluster(7, 3,
            FabricBlockSettings.of(Material.AMETHYST, MapColor.WHITE_GRAY)
                    .nonOpaque()
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                    .strength(1.5f)
                    .luminance(s -> 5));

    public static final SpellPlate SPELL_PLATE = new SpellPlate(FabricBlockSettings.of(Material.STONE)
                    .nonOpaque());

    public static final TranslationTable TRANSLATION_TABLE = new TranslationTable(
            FabricBlockSettings.of(Material.WOOD)
                    .nonOpaque());

    public static void initClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(LINGSHI_CLUSTER, RenderLayer.getCutout());
    }

    public static void init() {
        
    }

    @Override
    public BlockItem createBlockItem(Block block, String id) {
        return new BlockItem(block, new FabricItemSettings().group(YuanLing.YUANLING_GROUP));
    }
}
