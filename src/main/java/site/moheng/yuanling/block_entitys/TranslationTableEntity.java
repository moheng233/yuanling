package site.moheng.yuanling.block_entitys;

import javax.annotation.Nullable;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import site.moheng.yuanling.ModBlockEntitys;
import site.moheng.yuanling.ModScreenHandle;

public class TranslationTableEntity extends BlockEntity {
    private static final String ITEM_KEY_STRING = "stacks";

    public final SimpleInventory inventory = new SimpleInventory(1) {
        public boolean isValid(int slot, ItemStack stack) {
            return true;
        };
    };

    public TranslationTableEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitys.TRANSLATION_TABLE_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.put(ITEM_KEY_STRING, inventory.toNbtList());
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        var nlist = nbt.getList(ITEM_KEY_STRING, NbtType.COMPOUND);

        inventory.clear();
        inventory.readNbtList(nlist);

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

}
