package com.confusedparrotfish.fluorescence.lib;

import java.util.Random;

import com.confusedparrotfish.fluorescence.light.rotype;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class generic extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public rotype rotp = rotype.ALL;

    public ais.shape_handler_ais ishape = (s,g,p,c)->{return Shapes.block();};
    public ais.update_ais iupdate = (s,l,p)->{};
    public ais.placement_ais iplace = (p)->{return this.defaultBlockState();};
    public ais.rotation_handler_ais irot = (c)->{return Direction.UP;};

    public generic(Properties p_49795_) {
        super(p_49795_);

        this.registerDefaultState(this.defaultBlockState()
            .setValue(FACING, Direction.UP));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return ishape.shape(state,getter,pos,context);
    }
    
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos npos, boolean p_55671_) {
        if (!world.isClientSide) {
            iupdate.update(state, (ServerLevel)world, pos);
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext place) {
        BlockState state = iplace.place(place);
        if (rotp == rotype.ALL) {
            state = state.setValue(FACING, irot.rotate(place));
        }
        // place.getLevel().scheduleTick(place.getClickedPos(), this.asBlock(), 1);
        return state;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random p_60465_) {
        iupdate.update(state, level, pos);
    }
}
