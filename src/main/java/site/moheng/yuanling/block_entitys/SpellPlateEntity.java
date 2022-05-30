package site.moheng.yuanling.block_entitys;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import site.moheng.yuanling.ModBlockEntitys;

public class SpellPlateEntity extends BlockEntity {

    public SpellPlateEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntitys.SPELL_PLATE_ENTITY, pos, state);
    }
    
}
