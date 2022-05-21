package site.moheng.yuanling.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import site.moheng.yuanling.ModBlocks;
import site.moheng.yuanling.block_entitys.TranslationTableEntity;

public class TranslationTable extends HorizontalFacingBlock implements BlockEntityProvider {
    protected final VoxelShape shape;

    public TranslationTable(Settings settings) {
        super(settings);

        shape = Block.createCuboidShape(0, 13, 0, 16, 15, 16);

        setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.appendProperties(builder);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        // return (BlockState) this.getDefaultState().with(FACING,
        // ctx.getPlayerFacing().getOpposite());
        var dir = ctx.getPlayerFacing().getOpposite();
        var otherPos = getOtherBlockPos(ctx.getBlockPos(), dir);

        var world = ctx.getWorld();

        if (world.getBlockState(otherPos).canReplace(ctx) && world.getWorldBorder().contains(otherPos)) {
            return this.getDefaultState().with(FACING, dir);
        }

        return null;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TranslationTableEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {

        player.openHandledScreen((TranslationTableEntity)world.getBlockEntity(pos));

        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!world.isClient()) {
            var facing = state.get(FACING);

            if (getOtherBlockPos(pos, facing).equals(neighborPos)) {

                if (neighborState.isAir()) {
                    return Blocks.AIR.getDefaultState();
                }

            }
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state,
            LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        if (!world.isClient) {
            Direction facing = state.get(FACING);

            world.setBlockState(getOtherBlockPos(pos, facing), this.getDefaultState().with(FACING,
                    getOtherDirection(facing)));

            // world.updateNeighbors(pos, Blocks.AIR);
            // state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
        }
    }

    /**
     * 获取桌子其他部分的方向
     * @param direction 当前桌子部分的朝向
     * @return 桌子其他部分的朝向
     */
    public static Direction getOtherDirection(Direction direction) {
        return direction.getOpposite();
    }

    public static BlockPos getOtherBlockPos(BlockPos pos, Direction direction) {
        return pos.add(direction.rotateClockwise(Axis.Y).getVector());
    }

    public static BlockPos getMainBlockPosFromOther(BlockPos pos, Direction direction) {
        return pos.add(direction.rotateCounterclockwise(Axis.Y).getVector());
    }
}
