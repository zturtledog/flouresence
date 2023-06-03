package com.confusedparrotfish.fluorescence.misc;

import java.util.stream.Stream;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class shapes {
    public static final VoxelShape tiled_lamp_up = Stream.of(
        Block.box(0, 0, 0, 16, 1.5, 16),
        Block.box(1.5, 1.5, 8.5, 7.5, 2.5, 14.5),
        Block.box(1.5, 1.5, 1.5, 7.5, 2.5, 7.5),
        Block.box(8.5, 1.5, 1.5, 14.5, 2.5, 7.5),
        Block.box(8.5, 1.5, 8.5, 14.5, 2.5, 14.5)      
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_down = Stream.of(
        Block.box(0, 14.5, 0, 16, 16, 16),
        Block.box(1.5, 13.5, 1.5, 7.5, 14.5, 7.5),
        Block.box(1.5, 13.5, 8.5, 7.5, 14.5, 14.5),
        Block.box(8.5, 13.5, 8.5, 14.5, 14.5, 14.5),
        Block.box(8.5, 13.5, 1.5, 14.5, 14.5, 7.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_north = Stream.of(
        Block.box(0, 0, 0, 16, 16, 1.5),
        Block.box(1.5, 1.5, 1.5, 7.5, 7.5, 2.5),
        Block.box(1.5, 8.5, 1.5, 7.5, 14.5, 2.5),
        Block.box(8.5, 8.5, 1.5, 14.5, 14.5, 2.5),
        Block.box(8.5, 1.5, 1.5, 14.5, 7.5, 2.5)        
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_south = Stream.of(
        Block.box(0, 0, 14.5, 16, 16, 16),
        Block.box(1.5, 8.5, 13.5, 7.5, 14.5, 14.5),
        Block.box(1.5, 1.5, 13.5, 7.5, 7.5, 14.5),
        Block.box(8.5, 1.5, 13.5, 14.5, 7.5, 14.5),
        Block.box(8.5, 8.5, 13.5, 14.5, 14.5, 14.5)      
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_west = Stream.of(
        Block.box(0, 0, 0, 1.5, 16, 16),
        Block.box(1.5, 8.5, 1.5, 2.5, 14.5, 7.5),
        Block.box(1.5, 1.5, 1.5, 2.5, 7.5, 7.5),
        Block.box(1.5, 1.5, 8.5, 2.5, 7.5, 14.5),
        Block.box(1.5, 8.5, 8.5, 2.5, 14.5, 14.5)     
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_east = Stream.of(
        Block.box(14.5, 0, 0, 16, 16, 16),
        Block.box(13.5, 8.5, 8.5, 14.5, 14.5, 14.5),
        Block.box(13.5, 1.5, 8.5, 14.5, 7.5, 14.5),
        Block.box(13.5, 1.5, 1.5, 14.5, 7.5, 7.5),
        Block.box(13.5, 8.5, 1.5, 14.5, 14.5, 7.5)       
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        

    public static final VoxelShape peter_lamp = Block.box(3, 14, -8, 13, 16, 24);

    public static final VoxelShape drip_light = Shapes.join(
        Block.box(7, 12, 7, 9, 16, 9), 
        Block.box(3, 3, 3, 13, 13, 13), BooleanOp.OR);
}
