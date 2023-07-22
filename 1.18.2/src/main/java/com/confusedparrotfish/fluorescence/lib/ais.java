package com.confusedparrotfish.fluorescence.lib;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
        public Direction rotate(BlockPlaceContext contxt);
    }

    public static interface shape_handler_ais {
        public VoxelShape shape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context);
    }

    public static interface placement_ais {
        public BlockState place(BlockPlaceContext place);
    }

    //instance

    public static rotation_handler_ais horizontal_facing = (place)->{ 
        if (place.getPlayer()!=null) {
            Vec3 look = place.getPlayer().getLookAngle().normalize();
        
            return Direction.getNearest(look.x, 0, look.z);
        }
        return Direction.NORTH;
    };
}
