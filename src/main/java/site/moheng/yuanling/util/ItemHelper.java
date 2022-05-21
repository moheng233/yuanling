package site.moheng.yuanling.util;

import io.wispforest.owo.util.NbtKey;
import io.wispforest.owo.util.NbtKey.ListKey;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;

public class ItemHelper {
    public static final NbtKey<Integer> maxKey = new NbtKey<>("max", NbtKey.Type.INT);

    public static NbtCompound itemStackToNbt(ItemStack stack) {
        var nbt = new NbtCompound();

        stack.writeNbt(nbt);

        return nbt;
    }

    public static NbtCompound writeItemStackList(DefaultedList<ItemStack> stacks) {
        var nbt = new NbtCompound();

        maxKey.put(nbt, stacks.size());

        for (int i = 0; i < stacks.size(); i++) {
            var stack = itemStackToNbt(stacks.get(i));

            if(!stack.isEmpty()) {
                nbt.put(Integer.toString(i), stack);
            }
        }

        return nbt;
    }

    public static void readItemStackList(DefaultedList<ItemStack> stacks ,NbtCompound nbt) {
        var max = maxKey.get(nbt);
        stacks.clear();
        
        for (int i = 0; i < max; i++) {
            if(nbt.contains(Integer.toString(i))) {
                var stack = ItemStack.fromNbt(nbt.getCompound(Integer.toString(i)));
                stacks.set(i, stack);
            } else {
                stacks.set(i, ItemStack.EMPTY);
            }
        }
    }
}
