package com.confusedparrotfish.fluorescence.custom;

import com.confusedparrotfish.fluorescence.light;
import com.confusedparrotfish.fluorescence.lib.quarterproperty.plane_facing;
import com.confusedparrotfish.fluorescence.misc.shapes;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class sconce extends Block {
    public sconce(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(light.FACING, plane_facing.NORTH));
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        plane_facing face = state.getValue(light.FACING);
        if (face == plane_facing.NORTH)
            return shapes.sconce_north;
        if (face == plane_facing.EAST)
            return shapes.sconce_east;
        if (face == plane_facing.SOUTH)
            return shapes.sconce_south;
        if (face == plane_facing.WEST)
            return shapes.sconce_west;

        if (face == plane_facing.UP_NORTH)
            return shapes.sconce_north_down;
        if (face == plane_facing.UP_EAST)
            return shapes.sconce_east_down;
        if (face == plane_facing.UP_SOUTH)
            return shapes.sconce_south_down;
        if (face == plane_facing.UP_WEST)
            return shapes.sconce_west_down;

        return Shapes.block();
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(light.FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext place) {// crap function todo rewrite
        plane_facing face = plane_facing.NORTH;
        Player plr = place.getPlayer();

        if (plr != null) {
            Vec3 look = plr.getLookAngle().normalize();
            boolean shift = Screen.hasShiftDown();
            Direction near = Direction.getNearest(look.x, 0, look.z);
            Direction y = !shift ? Direction.DOWN : Direction.UP;

            if (place.getClickedFace().getAxis() != Axis.Y) near = place.getClickedFace();

            if (shift) near = near.getOpposite();
            face = plane_facing.fromdir(shift?near:near.getOpposite());

            if (y != Direction.DOWN)
                face = plane_facing.fromdir(near).align(y);

            // Direction near = Direction.getNearest(look.x, 0, look.z);
            // Direction plf = place.getClickedFace();
            // boolean shift = Screen.hasShiftDown();

            // plane_facing horiz = plane_facing.fromdir(!shift ? near :
            // near.getOpposite());
            // face = horiz;

            // plane_facing verti = plf.getAxis() != Axis.Y
            // ? plane_facing.fromdir(!shift ? plf : plf.getOpposite())
            // .align(shift ? Direction.UP : Direction.DOWN)
            // : horiz.align(shift ? Direction.UP : Direction.DOWN);

            // if (verti.flatten() != plane_facing.DOWN)
            // face = verti;
        }

        return this.defaultBlockState()
                .setValue(light.FACING, face);
    }
}
