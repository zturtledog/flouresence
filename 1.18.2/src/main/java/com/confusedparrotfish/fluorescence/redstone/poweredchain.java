package com.confusedparrotfish.fluorescence.redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class poweredchain extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final IntegerProperty POWER = IntegerProperty.create("pow", 0, 15);

    protected static final VoxelShape Y_AXIS_SHAPE = Block.box(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
    protected static final VoxelShape Z_AXIS_SHAPE = Block.box(6.5D, 6.5D, 0.0D, 9.5D, 9.5D, 16.0D);
    protected static final VoxelShape X_AXIS_SHAPE = Block.box(0.0D, 6.5D, 6.5D, 16.0D, 9.5D, 9.5D);

    public poweredchain(Properties p_51452_) {
        super(p_51452_);
        
        this.registerDefaultState(this.defaultBlockState()
        .setValue(FACING, Direction.UP));
    }
    
    public static Properties defaultprops() {
        return BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE)
                .requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion();
    }

    @Override
    public int getDirectSignal(BlockState p_60559_, BlockGetter p_60560_, BlockPos p_60561_, Direction p_60562_) {
        return getSignal(p_60559_, p_60560_, p_60561_, p_60562_);
    }

    @Override
    public int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction direction) {
        // System.out.println(state.getValue(POWER));
        // if (!(state.getValue(POWER)-1 >= 0)) return 0;
        // return state.getValue(POWER)-1;

        return 2;
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block nblock,
            BlockPos npos, boolean p_60514_) {
        if (!world.isClientSide) {
            Direction udir = Direction.fromNormal(pos.subtract(npos));

            // if the update came from any facing dir
            if (udir == state.getValue(FACING) || udir == state.getValue(FACING).getOpposite()) {
                // world.scheduleTick(pos, this, 0);
                
                int signal = Math.max(
                    world.getSignal(pos.relative(state.getValue(FACING)), state.getValue(FACING)),
                    world.getSignal(pos.relative(state.getValue(FACING).getOpposite()), state.getValue(FACING).getOpposite()));

                System.out.println(state+" - "+signal);

                // if (world.) {
                    world.setBlock(pos, state.setValue(POWER, signal >= 0 ? signal : 0), 2);
                // }
            }
        }
    }
    
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH:
                return Z_AXIS_SHAPE;
            case SOUTH:
                return Z_AXIS_SHAPE;
            case EAST:
                return X_AXIS_SHAPE;
            case WEST:
                return X_AXIS_SHAPE;
            case UP:
                return Y_AXIS_SHAPE;
            case DOWN:
                return Y_AXIS_SHAPE;
    
            default:
                return Shapes.block();
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext place) {
        place.getLevel().scheduleTick(place.getClickedPos(), this, 1);
    
        return this.defaultBlockState()
                .setValue(FACING, place.getClickedFace())
                .setValue(POWER, 0);
    }
    
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> build) {
        build.add(FACING);
        build.add(POWER);
    }
    
    public boolean isPathfindable(BlockState p_51456_, BlockGetter p_51457_, BlockPos p_51458_,
            PathComputationType p_51459_) {
        return false;
    }
}/*


    public int getDirectSignal(BlockState p_55625_, BlockGetter p_55626_, BlockPos p_55627_, Direction p_55628_) {
        return p_55625_.getSignal(p_55626_, p_55627_, p_55628_);
    }

    public int getSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction dir) {
        System.out.println(dir+" - "+state.getValue(FACING)+" - "+(dir == state.getValue(FACING) || dir == state.getValue(FACING).getOpposite())+" - "+state.getValue(POWER));
        if (dir == state.getValue(FACING) || dir == state.getValue(FACING).getOpposite()) {
            // int power = 
            //         getter.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).getSignal(getter, pos,
            //                 dir);

            return state.getValue(POWER);
        } else {
            return 0;
        }
    }


    @Override
    public void randomTick(BlockState p_222954_, ServerLevel p_222955_, BlockPos p_222956_, RandomSource p_222957_) {
        //
    }

    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block nblock, BlockPos npos, boolean p_55671_) {
        if (!world.isClientSide) {
            //check if should update
            //get power
            //send tick to update next

            if (nblock.getClass() != this.getClass()) {
                Direction udir = Direction.fromNormal(pos.subtract(npos));

                if (udir == state.getValue(FACING) || udir == state.getValue(FACING).getOpposite()) {
                    state.setValue(POWER, 15);//world.getSignal(npos, udir)

                    // world.scheduleTick(pos, this, 1);

                    // BlockPos rel = pos.relative(udir.getOpposite());
                    // world.scheduleTick(rel, world.getBlockState(pos.relative(udir.getOpposite())).getBlock(), 0);
                }
            }
        }
    }


}
/*
 * public FluidState getFluidState(BlockState state) {
 * return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) :
 * Fluids.EMPTY.defaultFluidState();
 * }
 * 
 * public BlockState updateShape(BlockState state, Direction dir, BlockState
 * other_state, LevelAccessor accc,
 * BlockPos pos, BlockPos other_pos) {
 * if (state.getValue(WATERLOGGED)) {
 * accc.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(accc));
 * }
 * 
 * return state;
 * }
 * 
 * .setValue(WATERLOGGED, false)
 * .add(WATERLOGGED)
 * 
 * public static final BooleanProperty WATERLOGGED =
 * BlockStateProperties.WATERLOGGED;
 * 
 * .setValue(WATERLOGGED, false)
 * 
 * 
 */