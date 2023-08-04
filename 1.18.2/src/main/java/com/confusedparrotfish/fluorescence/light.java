package com.confusedparrotfish.fluorescence;

import java.util.Random;
import java.util.function.ToIntFunction;

import com.confusedparrotfish.fluorescence.lib.ais;
import com.confusedparrotfish.fluorescence.lib.ais.shape_handler_ais;
import com.confusedparrotfish.fluorescence.lib.quarterproperty;
import com.confusedparrotfish.fluorescence.lib.quarterproperty.plane_facing;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

//todo: flat rotation
//todo: connected model blocks

public class light extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public static final quarterproperty FACING = quarterproperty.create("facing");

    public static final IntegerProperty MODE = IntegerProperty.create("mode", 0, 2);    
    // 0: redstone
    // 1: click
    // 2: always on
    
    public VoxelShape shape = Shapes.block();
    public VoxelShape north = Shapes.block();
    public VoxelShape east = Shapes.block();
    public VoxelShape south = Shapes.block();
    public VoxelShape west = Shapes.block();
    public VoxelShape up = Shapes.block();
    public VoxelShape down = Shapes.block();

    public static ToIntFunction<BlockState> lightpropogate(int min, int max) {// todo easings
        return (state) -> {
            if (state.getValue(light.LIT) || state.getValue(light.MODE) == 2) {
                return max;
            }
            return min;
        };
    }

    public light(Properties props) {
        super(props);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(LIT, false)
                .setValue(FACING, plane_facing.UP)
                .setValue(MODE, 0));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if (rotmode == rotype.ALL) {
            switch (state.getValue(FACING).flatten()) {
                case NORTH:
                    return north;
                case SOUTH:
                    return south;
                case EAST:
                    return east;
                case WEST:
                    return west;
                case UP:
                    return up;
                case DOWN:
                    return down;

                default:
                    return shape;
            }
        } else if (rotmode == rotype.NONE) {
            return shape;
        } 
        else if (rotmode == rotype.CUSTOM) {
            return ishape.shape(state, getter, pos, context);
        }
        return shape;
    }

    public BlockState getStateForPlacement(BlockPlaceContext place) {
        place.getLevel().scheduleTick(place.getClickedPos(), this, 1);

        return this.defaultBlockState()
                .setValue(LIT,
                        this.defaultBlockState().getValue(MODE)==0
                                ? Boolean.valueOf(place.getLevel().hasNeighborSignal(place.getClickedPos()))
                                : this.defaultBlockState().getValue(LIT))
                .setValue(FACING, irotation.rotate(place));
    }

    public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
        return isurvive.survive(state, reader, pos);
        // return Block.canSupportCenter(reader, pos.relative(direction), direction.getOpposite());
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hit) {
        if (!level.isClientSide) {
            if (state.getValue(MODE)==1 && hand.equals(InteractionHand.MAIN_HAND)) {
                level.setBlockAndUpdate(pos, state.cycle(LIT));
            }
        }
        return InteractionResult.FAIL;
    }

    private rotype rotmode = rotype.NONE;
    private ais.update_ais iupdate = (state, level, pos) -> {};
    private ais.rotation_handler_ais irotation = (x) -> {
        return plane_facing.UP;
    };
    private ais.shape_handler_ais ishape = (state, getter, pos, context)->{
        return shape;
    };
    private ais.block_surviveable_handler isurvive = (state, reader, pos) -> {return true;};

    public light setupd(ais.update_ais sam) {// single abstract method
        iupdate = sam;
        return this;
    }

    public light setrothand(ais.rotation_handler_ais hand) {
        irotation = hand;
        return this;
    }

    public light setsurv(ais.block_surviveable_handler surv) {
        isurvive = surv;
        return this;
    } 

    public light setshape(VoxelShape shap) {
        shape = shap;
        rotmode = rotype.NONE;
        return this;
    }

    public light setshape(VoxelShape n, VoxelShape e, VoxelShape s, VoxelShape w, VoxelShape u, VoxelShape d) {
        north = n;
        east = e;
        south = s;
        west = w;
        up = u;
        down = d;

        rotmode = rotype.ALL;

        return this;
    }

    public light setshape(ais.shape_handler_ais hand) {
        rotmode = rotype.CUSTOM;
        ishape = hand;

        return this;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random p_60465_) {
        iupdate.update(state, level, pos);
    }

    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos npos,
            boolean p_55671_) {
        if (!world.isClientSide) {
            iupdate.update(state, (ServerLevel) world, pos);
            if (state.getValue(MODE) == 0) {
                boolean flag = state.getValue(LIT);
                if (world.hasNeighborSignal(pos) != flag) {
                    if (flag) {
                        if (!world.hasNeighborSignal(pos)) {
                            world.setBlock(pos, state.cycle(LIT), 2);
                        }
                    } else {
                        world.setBlock(pos, state.cycle(LIT), 2);
                    }
                }
            }
        }
    }

    // public BlockState rotate(BlockState state, Rotation rot) {
    // return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    // }

    // public BlockState mirror(BlockState state, Mirror mir) {
    // return state.setValue(FACING, mir.mirror(state.getValue(FACING)));
    // }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
        builder.add(FACING);
        builder.add(MODE);
    }

    public static light build(Properties props, boolean lit, int defmode) {
        light temp = new light(props);
        temp.registerDefaultState(temp.defaultBlockState()
                .setValue(LIT, lit)
                .setValue(MODE, defmode));
        // temp.setshape(Shapes.block());
        // temp.mode = defmode;
        return temp;
    }

    public static Properties defaultprops(int min, int max) {
        return BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).lightLevel(light.lightpropogate(min, max))
                .strength(0.3F).sound(SoundType.GLASS);
    }

    // @Override
    // public List<ItemStack> getDrops(BlockState p_60537_, Builder p_60538_) {
    // ArrayList<ItemStack> def = new ArrayList<ItemStack>();
    // def.add(new ItemStack(Items.ACACIA_BOAT));
    // return def;
    // }

    public static enum rotype {
        NONE,
        CUSTOM,
        ALL
    }

    public static shape_handler_ais horizontal_up_down_facing_shape(VoxelShape n,VoxelShape e,VoxelShape s,VoxelShape w, VoxelShape un,VoxelShape ue,VoxelShape us,VoxelShape uw) {
        return (state, getter, pos, context)->{
            plane_facing face = state.getValue(FACING);
            if (face == plane_facing.NORTH) return n;
            if (face == plane_facing.EAST ) return e;
            if (face == plane_facing.SOUTH) return s;
            if (face == plane_facing.WEST ) return w;

            if (face == plane_facing.UP_NORTH) return un;
            if (face == plane_facing.UP_EAST ) return ue;
            if (face == plane_facing.UP_SOUTH) return us;
            if (face == plane_facing.UP_WEST ) return uw;

            return Shapes.block();
        };
    }
}

// .setValue(FLICKER, flickers));
// world.scheduleTick(pos, this, 4);
// builder.add(FLICKER);
// // rand.nextInt(max-min)+min

// state.setValue(LITFLICKER, 15);
// }

// public void randomTick(BlockState state, ServerLevel world, BlockPos pos,
// RandomSource rand) {
// if (state.getValue(LIT) && rand.nextDouble()>0.5) {
// state.setValue(LITFLICKER, rand.nextInt(0, 15));
// world.scheduleTick(pos, this, 4);
// }
// }
// builder.add(LITFLICKER);
// if (state.getValue(light.FLICKER)) {
// return
// (int)Math.round(((float)(state.getValue(light.LITFLICKER)))/15*(max-min)+min);
// }
// public static final BooleanProperty FLICKER =
// BooleanProperty.create("flicker");

// public static final IntegerProperty LITFLICKER =
// IntegerProperty.create("litflicker",0,15);
// .setValue(FLICKER, false)
// .setValue(LITFLICKER, 15));