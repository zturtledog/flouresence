package com.confusedparrotfish.fluorescence.misc;

import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class shapes {
    // tiled_lamp
    public static final VoxelShape tiled_lamp_up = Stream.of(
            Block.box(0, 0, 0, 16, 1.5, 16),
            Block.box(1.5, 1.5, 8.5, 7.5, 2.5, 14.5),
            Block.box(1.5, 1.5, 1.5, 7.5, 2.5, 7.5),
            Block.box(8.5, 1.5, 1.5, 14.5, 2.5, 7.5),
            Block.box(8.5, 1.5, 8.5, 14.5, 2.5, 14.5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_down = Stream.of(
            Block.box(0, 14.5, 0, 16, 16, 16),
            Block.box(1.5, 13.5, 1.5, 7.5, 14.5, 7.5),
            Block.box(1.5, 13.5, 8.5, 7.5, 14.5, 14.5),
            Block.box(8.5, 13.5, 8.5, 14.5, 14.5, 14.5),
            Block.box(8.5, 13.5, 1.5, 14.5, 14.5, 7.5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_north = Stream.of(
            Block.box(0, 0, 0, 16, 16, 1.5),
            Block.box(1.5, 1.5, 1.5, 7.5, 7.5, 2.5),
            Block.box(1.5, 8.5, 1.5, 7.5, 14.5, 2.5),
            Block.box(8.5, 8.5, 1.5, 14.5, 14.5, 2.5),
            Block.box(8.5, 1.5, 1.5, 14.5, 7.5, 2.5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_south = Stream.of(
            Block.box(0, 0, 14.5, 16, 16, 16),
            Block.box(1.5, 8.5, 13.5, 7.5, 14.5, 14.5),
            Block.box(1.5, 1.5, 13.5, 7.5, 7.5, 14.5),
            Block.box(8.5, 1.5, 13.5, 14.5, 7.5, 14.5),
            Block.box(8.5, 8.5, 13.5, 14.5, 14.5, 14.5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_west = Stream.of(
            Block.box(0, 0, 0, 1.5, 16, 16),
            Block.box(1.5, 8.5, 1.5, 2.5, 14.5, 7.5),
            Block.box(1.5, 1.5, 1.5, 2.5, 7.5, 7.5),
            Block.box(1.5, 1.5, 8.5, 2.5, 7.5, 14.5),
            Block.box(1.5, 8.5, 8.5, 2.5, 14.5, 14.5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape tiled_lamp_east = Stream.of(
            Block.box(14.5, 0, 0, 16, 16, 16),
            Block.box(13.5, 8.5, 8.5, 14.5, 14.5, 14.5),
            Block.box(13.5, 1.5, 8.5, 14.5, 7.5, 14.5),
            Block.box(13.5, 1.5, 1.5, 14.5, 7.5, 7.5),
            Block.box(13.5, 8.5, 1.5, 14.5, 14.5, 7.5)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    // peter_lamp
    public static final VoxelShape peter_lamp = Block.box(3, 14, -8, 13, 16, 24);
    public static final VoxelShape peter_lamp_turn = Block.box(-8, 14, 3, 24, 16, 13);
    public static final VoxelShape peter_lamp_up = Block.box(3, 0, -8, 13, 2, 24);
    public static final VoxelShape peter_lamp_turn_up = Block.box(-8, 0, 3, 24, 2, 13);

    // drip_light
    public static final VoxelShape drip_light = Shapes.join(
            Block.box(7, 12, 7, 9, 16, 9),
            Block.box(3, 3, 3, 13, 13, 13), BooleanOp.OR);

    // pendant_light
    public static final VoxelShape pendant_light = Stream.of(
            Block.box(12, 8, 4, 13, 11, 12),
            Block.box(7, 12, 7, 9, 16, 9),
            Block.box(6.5, 7, 6.5, 9.5, 12.4, 9.5),
            Block.box(4, 11, 4, 12, 12, 12),
            Block.box(4, 8, 3, 12, 11, 4),
            Block.box(4, 8, 12, 12, 11, 13),
            Block.box(3, 8, 4, 4, 11, 12)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    // barlight
    public static final VoxelShape bar_light = Stream.of(
            Block.box(3, 14.4, 0, 13, 16, 16),
            Block.box(10.5, 12.4, 0, 12.5, 14.4, 16),
            Block.box(3.7, 12.4, 0, 5.7, 14.4, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape bar_light_turn = Stream.of(
            Block.box(0, 12.4, 10.3, 16, 14.4, 12.3),
            Block.box(0, 12.4, 3.5, 16, 14.4, 5.5),
            Block.box(0, 14.4, 3, 16, 16, 13)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape bar_light_up = Stream.of(
            Block.box(3, 0, 0, 13, 1.6, 16),
            Block.box(10.5, 1.6, 0, 12.5, 3.6, 16),
            Block.box(3.7, 1.6, 0, 5.7, 3.6, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape bar_light_turn_up = Stream.of(
            Block.box(0, 8.881784197001252e-16, 2.9000000000000004, 16, 1.5999999999999988, 12.9),
            Block.box(0, 1.5999999999999988, 10.4, 16, 3.5999999999999988, 12.4),
            Block.box(0, 1.5999999999999988, 3.6000000000000014, 16, 3.5999999999999988, 5.600000000000001))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    // rednib
    public static final VoxelShape rednib_down = Shapes.join(
            Block.box(5.5, 0, 5.5, 10.5, 0.8, 10.5),
            Block.box(6.5, 0.5, 6.5, 9.5, 1.5, 9.5), BooleanOp.OR);

    public static final VoxelShape rednib_up = Shapes.join(
            Block.box(5.5, 15.2, 5.5, 10.5, 16, 10.5),
            Block.box(6.5, 14.5, 6.5, 9.5, 15.5, 9.5), BooleanOp.OR);

    public static final VoxelShape rednib_north = Shapes.join(
            Block.box(5.5, 5.5, 0, 10.5, 10.5, 0.8),
            Block.box(6.5, 6.5, 0.5, 9.5, 9.5, 1.5), BooleanOp.OR);

    public static final VoxelShape rednib_south = Shapes.join(
            Block.box(5.5, 5.5, 15.2, 10.5, 10.5, 16),
            Block.box(6.5, 6.5, 14.5, 9.5, 9.5, 15.5), BooleanOp.OR);

    public static final VoxelShape rednib_east = Shapes.join(
            Block.box(15.2, 5.5, 5.5, 16, 10.5, 10.5),
            Block.box(14.5, 6.5, 6.5, 15.5, 9.5, 9.5), BooleanOp.OR);

    public static final VoxelShape rednib_west = Shapes.join(
            Block.box(0, 5.5, 5.5, 0.8, 10.5, 10.5),
            Block.box(0.5, 6.5, 6.5, 1.5, 9.5, 9.5), BooleanOp.OR);

    // fence_topper
    public static final VoxelShape fence_topper_post = Stream.of(
            Block.box(5, -1, 10, 10, 1, 11),
            Block.box(10, -1, 6, 11, 1, 11),
            Block.box(6, -1, 5, 11, 1, 6),
            Block.box(5, -1, 5, 6, 1, 10),
            Block.box(5.9, 0.95, 5.9, 10.1, 11.25, 10.1))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    static int topper_zoff = 16;
    public static final VoxelShape fence_topper_south = Stream.of(
            Block.box(6.9, 3.95, 10.1 - topper_zoff, 9.1, 9.75, 13.1 - topper_zoff),
            Block.box(6.9, 6.95, 13.1 - topper_zoff, 9.1, 9.75, 16.1 - topper_zoff),
            Block.box(6.9, 7.95, 16.1 - topper_zoff, 9.1, 9.75, 26.1 - topper_zoff),
            Block.box(6.9, -0.05, 23 - topper_zoff, 9.1, 7.95, 25 - topper_zoff))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape fence_topper_north = Stream.of(
            Block.box(6.9, 3.95, 2.90 + topper_zoff, 9.1, 9.75, 5.9 + topper_zoff),
            Block.box(6.9, 6.95, -0.1 + topper_zoff, 9.1, 9.75, 2.90 + topper_zoff),
            Block.box(6.9, -0.05, -9 + topper_zoff, 9.1, 7.95, -7 + topper_zoff),
            Block.box(6.9, 7.95, -10.1 + topper_zoff, 9.1, 9.75, -0.1 + topper_zoff))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape fence_topper_east = Stream.of(
            Block.box(23 - topper_zoff, -0.05, 6.9, 25 - topper_zoff, 7.95, 9.1),
            Block.box(13.1 - topper_zoff, 6.95, 6.9, 16.1 - topper_zoff, 9.75, 9.1),
            Block.box(16.1 - topper_zoff, 7.95, 6.9, 26.1 - topper_zoff, 9.75, 9.1),
            Block.box(10.1 - topper_zoff, 3.953, 6.9, 13.1 - topper_zoff, 9.75, 9.1))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape fence_topper_west = Stream.of(
            Block.box(2.9 + topper_zoff, 3.953, 6.9, 5.9 + topper_zoff, 9.75, 9.1),
            Block.box(-10.1 + topper_zoff, 7.95, 6.9, -0.1 + topper_zoff, 9.75, 9.1),
            Block.box(-0.1 + topper_zoff, 6.95, 6.9, 2.9 + topper_zoff, 9.75, 9.1),
            Block.box(-9 + topper_zoff, -0.05, 6.9, -7 + topper_zoff, 7.95, 9.1))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    // bedside lamp
    public static final VoxelShape bedside_lamp = Stream.of(
            Block.box(11.100000000000001, 5.25, 4, 12.55, 13.5, 11.999999999999986),
            Block.box(4.000000000000007, 5, 4, 11.999999999999993, 5.5, 12),
            Block.box(4.000000000000014, 5.25, 11.100000000000001, 12, 13.5, 12.55),
            Block.box(3.4499999999999993, 5.25, 4.000000000000014, 4.899999999999999, 13.5, 12),
            Block.box(4, 5.25, 3.4499999999999993, 11.999999999999986, 13.5, 4.899999999999999),
            Block.box(6.25, 0, 6.299999999999999, 9.75, 5.25, 9.799999999999999))
            .reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    //sconce
    public static final VoxelShape sconce_north = Stream.of(
            Block.box(4, 15, 4, 12, 16, 12),
            Block.box(4, 6, 0, 12, 16, 4),
            Block.box(7, 9, 7, 9, 15, 9),
            Block.box(6.25, 7.5, 4, 9.75, 15, 7)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape sconce_east = Stream.of(
        Block.box(4, 15, 4, 12, 16, 12),
        Block.box(12, 6, 4, 16, 16, 12),
        Block.box(7, 9, 7, 9, 15, 9),
        Block.box(9, 7.5, 6.25, 12, 15, 9.75)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape sconce_south = Stream.of(
        Block.box(4, 15, 4, 12, 16, 12),
        Block.box(4, 6, 12, 12, 16, 16),
        Block.box(7, 9, 7, 9, 15, 9),
        Block.box(6.25, 7.5, 9, 9.75, 15, 12)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape sconce_west = Stream.of(
        Block.box(4, 15, 4, 12, 16, 12),
        Block.box(0, 6, 4, 4, 16, 12),
        Block.box(7, 9, 7, 9, 15, 9),
        Block.box(4, 7.5, 6.25, 7, 15, 9.75)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape sconce_north_down = Stream.of(
        Block.box(4, 0, 4, 12, 1, 12),
        Block.box(4, 0, 0, 12, 10, 4),
        Block.box(7, 1, 7, 9, 7, 9),
        Block.box(6.25, 1, 4, 9.75, 8.5, 7)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape sconce_east_down = Stream.of(
        Block.box(4, 0, 4, 12, 1, 12),
        Block.box(12, 0, 4, 16, 10, 12),
        Block.box(7, 1, 7, 9, 7, 9),
        Block.box(9, 1, 6.25, 12, 8.5, 9.75)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape sconce_south_down = Stream.of(
        Block.box(4, 0, 4, 12, 1, 12),
        Block.box(4, 0, 12, 12, 10, 16),
        Block.box(7, 1, 7, 9, 7, 9),
        Block.box(6.25, 1, 9, 9.75, 8.5, 12)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape sconce_west_down = Stream.of(
        Block.box(4, 0, 4, 12, 1, 12),
        Block.box(0, 0, 4, 4, 10, 12),
        Block.box(7, 1, 7, 9, 7, 9),
        Block.box(4, 1, 6.25, 7, 8.5, 9.75)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static ArrayList<VoxelShape> calc_enumeration(VoxelShape n, VoxelShape e, VoxelShape s, VoxelShape w,
            VoxelShape b) {
        ArrayList<VoxelShape> inl = new ArrayList<VoxelShape>();

        for (int i = 0; i < 16; i++) {
            String bin = leftpad(Integer.toBinaryString(i), 4);

            // slow and there is a better way to do this
            Builder<VoxelShape> build = Stream.builder();

            build.add(b);
            if (bin.charAt(0) == '1')
                build.add(n);
            if (bin.charAt(1) == '1')
                build.add(e);
            if (bin.charAt(2) == '1')
                build.add(s);
            if (bin.charAt(3) == '1')
                build.add(w);

            inl.add(build.build().reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get());
        }
        return inl;
    }

    // util
    public static String leftpad(String b, int n) {
        String end = "";
        for (int i = 0; i < n - b.length(); i++) {
            end += "0";
        }
        return end + b;
    }
}

/*
 * 0: 0000
 * 1: 0001
 * 2: 0010
 * 3: 0011
 * 4: 0100
 * 5: 0101
 * 6: 0110
 * 7: 0111
 * 8: 1000
 * 9: 1001
 * 10:1010
 * 11:1011
 * 12:1100
 * 13:1101
 * 14:1110
 * 15:1111
 */