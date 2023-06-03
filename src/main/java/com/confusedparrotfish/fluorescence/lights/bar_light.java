package com.confusedparrotfish.fluorescence.lights;

import java.util.stream.Stream;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class bar_light extends Block {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty CONNECTED = BooleanProperty.create("redstone");
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final VoxelShape bar_light = Stream.of(
        Block.box(3, 14.4, 0, 13, 16, 16),
        Block.box(10.5, 12.4, 0, 12.5, 14.4, 16),
        Block.box(3.7, 12.4, 0, 5.7, 14.4, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape bar_light_turn = Stream.of(
        Block.box(0, 12.4, 10.3, 16, 14.4, 12.3),
        Block.box(0, 12.4, 3.5, 16, 14.4, 5.5),
        Block.box(0, 14.4, 3, 16, 16, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public bar_light(Properties props) {
        super(props);
        this.registerDefaultState(this.defaultBlockState()
            .setValue(CONNECTED, false)
            .setValue(LIT, false)
            .setValue(FACING, Direction.UP));
    }

    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos npos, boolean p_55671_) {
        if (!world.isClientSide) {
            boolean flag = state.getValue(LIT);
            if (flag != world.hasNeighborSignal(pos)) {
                if (flag) {
                    if (state.getValue(LIT) && !world.hasNeighborSignal(pos)) {
                        world.setBlock(pos, state.cycle(LIT), 2);
                    }
                } else {
                    world.setBlock(pos, state.cycle(LIT), 2);
                }
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos,
            CollisionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH: return bar_light;
            case SOUTH: return bar_light;
            case EAST: return bar_light_turn;
            case WEST: return bar_light_turn;
        
            default: return Shapes.block();
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext place) {
        place.getLevel().scheduleTick(place.getClickedPos(), this, 1);  

        Vec3 look = place.getPlayer().getLookAngle();

        return this.defaultBlockState()
            .setValue(LIT, Boolean.valueOf(place.getLevel().hasNeighborSignal(place.getClickedPos())))
            .setValue(FACING, Direction.getNearest(look.x, look.y, look.z));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, CONNECTED, FACING);
    }
}