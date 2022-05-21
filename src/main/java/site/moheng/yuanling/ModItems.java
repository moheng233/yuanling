package site.moheng.yuanling;

import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Rarity;
import site.moheng.yuanling.items.ImprintItem;
import site.moheng.yuanling.items.LingShiItem;

public class ModItems implements ItemRegistryContainer {
    public static final LingShiItem LINGSHI = new LingShiItem(new FabricItemSettings().rarity(Rarity.COMMON).group(YuanLing.YUANLING_GROUP));
    public static final ImprintItem IMPRINT = new ImprintItem(new FabricItemSettings().rarity(Rarity.UNCOMMON).group(YuanLing.YUANLING_GROUP));

    public static void init() {

    }
}
