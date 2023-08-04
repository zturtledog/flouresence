package com.confusedparrotfish.fluorescence.lib;

import com.confusedparrotfish.fluorescence.lib.quarterproperty.plane_facing;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

//abstract interface singleton
public class ais {
    public static interface update_ais {
        public void update(BlockState state, ServerLevel level, BlockPos pos);
    }

    public static interface rotation_handler_ais {
        public plane_facing rotate(BlockPlaceContext contxt);
    }

    public static interface shape_handler_ais {
        public VoxelShape shape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context);
    }

    public static interface placement_ais {
        public BlockState place(BlockPlaceContext place);
    }

    public static interface block_surviveable_handler {
        public boolean survive(BlockState state, LevelReader reader, BlockPos pos);
    }

    // instance

    public static rotation_handler_ais horizontal_facing = (place) -> {
        Player plr = place.getPlayer();
        if (plr != null) {
            Vec3 look = plr.getLookAngle().normalize();

            return plane_facing.fromdir(Direction.getNearest(look.x, 0, look.z));
        }
        return plane_facing.NORTH;
    };

    public static rotation_handler_ais horizontal_up_down_facing = (place)->{
        plane_facing horiz = horizontal_facing.rotate(place);

        Player plr = place.getPlayer();
        if (plr != null) {
            Vec3 look = plr.getLookAngle().normalize();
            plane_facing verti = horiz.align(Direction.getNearest(0, look.y, 0).getOpposite());
            if (verti.flatten() == plane_facing.DOWN) return horiz;
            return verti;
        }

        // if (place.getClickedFace()==Direction.UP) {
        //     return horiz.align(Direction.UP);
        // }
        return horiz;
    };
}
