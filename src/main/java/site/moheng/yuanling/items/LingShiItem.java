package site.moheng.yuanling.items;

import java.util.List;

import io.wispforest.owo.particles.ClientParticles;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * 元灵石
 */
public class LingShiItem extends Item {
    /**
     * 灵石品质的枚举
     */
    enum EQuality {
        /**
         * 次品
         */
        Def(0),
        /**
         * 下品
         */
        Inf(1),
        /**
         * 中品
         */
        Med(2),
        /**
         * 上品
         */
        Top(3),
        /**
         * 极品
         */
        Bes(4);

        private final int value;

        private EQuality(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    static final String QualityNBTKey = "quality";

    public LingShiItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.yuanling.lingshi.tooltip"));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack stack) {
        return getQualityTranslatable(stack).append(super.getName(stack));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        if (stack.hasNbt()) {
            var nbt = stack.getNbt();
            var quality = nbt.getInt(QualityNBTKey);

            switch (quality) {
                case 0: {
                    return Rarity.COMMON;
                }
                case 1: {
                    return Rarity.UNCOMMON;
                }
                case 2: {
                    return Rarity.RARE;
                }
                case 3: {
                    return Rarity.EPIC;
                }
                case 4: {
                    return Rarity.EPIC;
                }
            }
        }

        return super.getRarity(stack);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (stack.hasNbt()) {
            var nbt = stack.getNbt();
            var quality = nbt.getInt(QualityNBTKey);

            if (quality == 4) {
                return true;
            }
        }

        return false;
    }

    public EQuality getQualityLevel(ItemStack stack) {
        if (stack.hasNbt()) {
            var nbt = stack.getNbt();
            var q = nbt.getInt(QualityNBTKey);

            switch (q) {
                case 0:
                    return EQuality.Def;
                case 1:
                    return EQuality.Inf;
                case 2:
                    return EQuality.Med;
                case 3:
                    return EQuality.Top;
                case 4:
                    return EQuality.Bes;
            }
        }

        return EQuality.Def;
    }

    public TranslatableText getQualityTranslatable(ItemStack stack) {
        var quality = getQualityLevel(stack);

        switch (quality) {
            case Inf:
                return new TranslatableText("text.yuanling.quality.1");
            case Med:
                return new TranslatableText("text.yuanling.quality.2");
            case Top:
                return new TranslatableText("text.yuanling.quality.3");
            case Bes:
                return new TranslatableText("text.yuanling.quality.4");
            default:
                return new TranslatableText("text.yuanling.quality.0");
        }
    }

    public ItemStack buildStack(EQuality quality) {
        var stack = new ItemStack(this, 1);

        var nbt = new NbtCompound();
        nbt.putInt(QualityNBTKey, quality.getValue());
        stack.setNbt(nbt);

        return stack;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {
            stacks.add(buildStack(EQuality.Def));
            stacks.add(buildStack(EQuality.Inf));
            stacks.add(buildStack(EQuality.Med));
            stacks.add(buildStack(EQuality.Top));
            stacks.add(buildStack(EQuality.Bes));
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();
        if(world.isClient) {
            ClientParticles.spawnEnchantParticles(world, context.getPlayer().getPos().add(0,1,0), Vec3d.ofCenter(context.getBlockPos()), 2f);
        }

        return ActionResult.CONSUME;
    }
}
