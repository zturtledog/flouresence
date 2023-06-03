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
    public static final VoxelShape peter_lamp_turn = Block.box(-8, 14, 3, 24, 16, 13);

    public static final VoxelShape drip_light = Shapes.join(
        Block.box(7, 12, 7, 9, 16, 9), 
        Block.box(3, 3, 3, 13, 13, 13), BooleanOp.OR);

    public static final VoxelShape pendant_light = Stream.of(
        Block.box(12, 8, 4, 13, 11, 12),
        Block.box(7, 12, 7, 9, 16, 9),
        Block.box(6.5, 7, 6.5, 9.5, 12.4, 9.5),
        Block.box(4, 11, 4, 12, 12, 12),
        Block.box(4, 8, 3, 12, 11, 4),
        Block.box(4, 8, 12, 12, 11, 13),
        Block.box(3, 8, 4, 4, 11, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

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
}
