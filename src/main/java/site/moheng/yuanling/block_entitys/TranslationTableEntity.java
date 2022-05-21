package site.moheng.yuanling.block_entitys;

import javax.annotation.Nullable;

import io.wispforest.owo.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import site.moheng.yuanling.ModBlockEntitys;
import site.moheng.yuanling.ModScreenHandle;
import site.moheng.yuanling.util.ItemHelper;

public class TranslationTableEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {
    private static final String ITEM_KEY_STRING = "stacks";

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);


    public TranslationTableEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitys.TRANSLATION_TABLE_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
    
    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.put(ITEM_KEY_STRING, ItemHelper.writeItemStackList(items));
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        ItemHelper.readItemStackList(items ,nbt.getCompound(ITEM_KEY_STRING));

        super.readNbt(nbt);
    }

    @Override
    @Nullable
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        var nbt = new NbtCompound();
        writeNbt(nbt);

        return nbt;
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        if(slot == 1) {
            if(stack.isOf(Items.ENCHANTED_BOOK)) {
                return true;
            }
        }

        return false;
    }

    public void setBook(ItemStack stack) {
        setStack(0, stack);
    }

    public boolean hasBook() {
        return getItems().get(0) != null;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory var2, PlayerEntity var3) {
        return ModScreenHandle.TRANSLATION_TABLE_SCREEN_HANDLER.create(syncId, var2);
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("ui.yuanling.table.title");
    }

    
}
