package com.confusedparrotfish.fluorescence.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

//abstract interface singleton
public class ais {
    public static interface update_ais {
        public void update(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand);
    }

    public static interface rotation_handler_ais {
        public Direction rotate(BlockPlaceContext contxt);
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
