package com.confusedparrotfish.fluorescence.custom.block;

import com.confusedparrotfish.fluorescence.misc.shapes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class fence_topper extends Block {
    public static final IntegerProperty MODEL = IntegerProperty.create("model", 0, 4);

    public fence_topper(Properties p_49795_) {
        super(p_49795_);

        this.registerDefaultState(this.defaultBlockState()
                .setValue(MODEL, 0));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        switch (state.getValue(MODEL)) {
            case 1:
                return shapes.fence_topper_north;
            case 2:
                return shapes.fence_topper_east;
            case 3:
                return shapes.fence_topper_south;
            case 4:
                return shapes.fence_topper_west;

            default:
                return shapes.fence_topper_post;
        }
    }

    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos npos,
            boolean p_55671_) {
        if (!world.isClientSide) {
            // iupdate.update(state, (ServerLevel)world, pos);/

            int mod = state.getValue(MODEL);
            if (mod != 0) {
                if (dirtoint(Direction.fromNormal(pos.subtract(npos))) == mod) {
                    if (world.getBlockState(npos).isAir()) world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext place) {
        return this.defaultBlockState();// .setValue(MODEL, (int)Math.round(Math.random()*5-0.5));
    }

    @Override
    public void destroy(LevelAccessor la, BlockPos pos, BlockState state) {
        System.out.println("uwu");

        // todo: break blocks

        // if (state.getValue(MODEL) == 0) {
            super.destroy(la, pos, state);
        // }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player p_49855_) {
        super.playerWillDestroy(world, pos, state, p_49855_);

        if (!world.isClientSide) if (state.getValue(MODEL) != 0) world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
    }

    private int dirtoint(Direction dir) {
        switch (dir) {
            case NORTH:
                return 1;
            case EAST:
                return 2;
            case SOUTH:
                return 3;
            case WEST:
                return 4;
            default:
                return 0;
        }
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hit) {
        if (!level.isClientSide) {
            if (state.getValue(MODEL) != 0 || hit.getDirection() == Direction.UP
                    || hit.getDirection() == Direction.DOWN)
                return InteractionResult.FAIL;
            BlockPos lpos = pos.relative(hit.getDirection());
            if (level.getBlockState(lpos).isAir()) {
                level.setBlock(lpos, this.defaultBlockState().setValue(MODEL, dirtoint(hit.getDirection())), 3);
            } else if (level.getBlockState(lpos).getBlock() == this && 
                    level.getBlockState(lpos).getValue(MODEL)!=0) {
                level.setBlock(lpos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
        return InteractionResult.CONSUME;
    }

    public PushReaction getPistonPushReaction(BlockState p_49556_) {
        return PushReaction.BLOCK;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MODEL);
    }
}
