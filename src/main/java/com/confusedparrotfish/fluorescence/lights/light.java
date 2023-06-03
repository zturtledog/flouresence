package com.confusedparrotfish.fluorescence.lights;

import java.util.function.ToIntFunction;

import com.confusedparrotfish.fluorescence.misc.ais;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

//todo: flat rotation
//todo: connected model blocks

public class light extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty REDSTONE = BooleanProperty.create("redstone");

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public VoxelShape shape = Shapes.block();
    public VoxelShape north = Shapes.block();
    public VoxelShape east  = Shapes.block();
    public VoxelShape south = Shapes.block();
    public VoxelShape west  = Shapes.block();
    public VoxelShape up    = Shapes.block();
    public VoxelShape down  = Shapes.block();

    public static ToIntFunction<BlockState> lightpropogate(int min, int max) {//todo easings
        return (state)->{
            if (state.getValue(light.LIT)) {
                return max;
            } return min;
        };
    }
    
    public light(Properties props) {
        super(props);
        this.registerDefaultState(this.defaultBlockState()
        .setValue(REDSTONE, false)
        .setValue(LIT, false)
        .setValue(FACING, Direction.UP));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if (rotmode == rotype.ALL) {
            switch (state.getValue(FACING)) {
                case NORTH: return north;
                case SOUTH: return south;
                case EAST: return east;
                case WEST: return west;
                case UP: return up;
                case DOWN: return down;
            
                default: return shape;
            }
        } else if (rotmode == rotype.NONE) {
            return shape;
        }
        return shape;
    }
    
    public BlockState getStateForPlacement(BlockPlaceContext place) {
        place.getLevel().scheduleTick(place.getClickedPos(), this, 1);

        return this.defaultBlockState()
            .setValue(LIT,this.defaultBlockState().getValue(REDSTONE)?
                Boolean.valueOf(place.getLevel().hasNeighborSignal(place.getClickedPos())):
                    this.defaultBlockState().getValue(LIT))
            .setValue(FACING, rothand.rotate(place));
    }
    
    public rotype rotmode = rotype.NONE;
    public ais.update_ais update = (state, level, pos, rand) -> {};
    public ais.rotation_handler_ais rothand = (x) -> {return Direction.UP;};
    public light setupd(ais.update_ais sam) {//single abstract method
        update = sam;
        return this;
    }
    public light setrothand(rotype typ, ais.rotation_handler_ais hand) {
        rothand = hand;
        rotmode = typ;
        return this;
    }
    public light setshape(VoxelShape shap) {
        shape = shap;
        return this;
    }
    public light setshape(VoxelShape n,VoxelShape e,VoxelShape s,VoxelShape w,VoxelShape u,VoxelShape d) {
        north = n;
        east = e;
        south = s;
        west = w;
        up = u;
        down = d;

        return this;
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        update.update(state, level, pos, rand);
    }
    
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos npos, boolean p_55671_) {
        if (!world.isClientSide) {
            update.update(state, (ServerLevel)world, pos, world.random);
            if (state.getValue(REDSTONE)) {
                boolean flag = state.getValue(LIT);
                if (flag != world.hasNeighborSignal(pos)) {
                    if (flag) {
                        if (state.getValue(REDSTONE) && state.getValue(LIT) && !world.hasNeighborSignal(pos)) {
                            world.setBlock(pos, state.cycle(LIT), 2);
                        }
                    } else {
                        world.setBlock(pos, state.cycle(LIT), 2);
                    }
                }
            }
        }
    }
    
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mir) {
        return state.setValue(FACING, mir.mirror(state.getValue(FACING)));
    }


    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        builder.add(REDSTONE);
        builder.add(FACING);
    }
    
    public static light build(Properties props, boolean lit, boolean useredstone) {
        light temp = new light(props);
        temp.registerDefaultState(temp.defaultBlockState()
        .setValue(LIT, lit)
        .setValue(REDSTONE, useredstone));
        // temp.setshape(Shapes.block());
        return temp;
    }

    public static Properties defaultprops(int min, int max) {
        return BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).lightLevel(light.lightpropogate(min, max))
                .strength(0.3F).sound(SoundType.GLASS);
    }

    public static enum rotype {
        NONE,
        LOG,
        ALL
    }
}


// .setValue(FLICKER, flickers));
// world.scheduleTick(pos, this, 4);
// builder.add(FLICKER);
    //     // rand.nextInt(max-min)+min
    
    //     state.setValue(LITFLICKER, 15);
    // }
    
    // public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
        //     if (state.getValue(LIT) && rand.nextDouble()>0.5) {
            //         state.setValue(LITFLICKER, rand.nextInt(0, 15));
            //         world.scheduleTick(pos, this, 4);
            //     }
            // }
            // builder.add(LITFLICKER);
            // if (state.getValue(light.FLICKER)) {
                //     return (int)Math.round(((float)(state.getValue(light.LITFLICKER)))/15*(max-min)+min);
                // } 
                // public static final BooleanProperty FLICKER = BooleanProperty.create("flicker");
                
                // public static final IntegerProperty LITFLICKER = IntegerProperty.create("litflicker",0,15);
                // .setValue(FLICKER, false)
                // .setValue(LITFLICKER, 15));