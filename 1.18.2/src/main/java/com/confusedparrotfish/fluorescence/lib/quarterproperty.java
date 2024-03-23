package com.confusedparrotfish.fluorescence.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import java.util.function.Predicate;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import com.confusedparrotfish.fluorescence.lib.quarterproperty.plane_facing;

public class quarterproperty extends EnumProperty<plane_facing> {
    protected quarterproperty(String p_61579_, Class<plane_facing> p_61580_, Collection<plane_facing> p_61581_) {
        super(p_61579_, p_61580_, p_61581_);
    }

    public static quarterproperty create(String p_156004_) {
        return create(p_156004_, (p_187558_) -> {
            return true;
        });
    }

    public static quarterproperty create(String p_61547_, Predicate<plane_facing> p_61548_) {
        return create(p_61547_, Arrays.stream(plane_facing.values()).filter(p_61548_).collect(Collectors.toList()));
    }

    public static quarterproperty create(String p_61550_, plane_facing... p_61551_) {
        return create(p_61550_, Lists.newArrayList(p_61551_));
    }

    public static quarterproperty create(String p_61544_, Collection<plane_facing> p_61545_) {
        return new quarterproperty(p_61544_, plane_facing.class, p_61545_);
    }

    public static enum plane_facing implements StringRepresentable {
        NORTH("north", null, true),
        EAST("east", null, true),
        SOUTH("south", null, true),
        WEST("west", null, true),
        UP("up", null, true),
        DOWN("down", null, true),

        NORTH_EAST("north_east", NORTH, false),
        NORTH_SOUTH("north_south", NORTH, false),
        NORTH_WEST("north_west", NORTH, false),
        NORTH_UP("north_up", NORTH, false),
        NORTH_DOWN("north_down", NORTH, false),

        EAST_NORTH("east_north", EAST, false),
        EAST_SOUTH("east_south", EAST, false),
        EAST_WEST("east_west", EAST, false),
        EAST_UP("east_up", EAST, false),
        EAST_DOWN("east_down", EAST, false),

        SOUTH_NORTH("south_north", SOUTH, false),
        SOUTH_EAST("south_east", SOUTH, false),
        SOUTH_WEST("south_west", SOUTH, false),
        SOUTH_UP("south_up", SOUTH, false),
        SOUTH_DOWN("south_down", SOUTH, false),

        WEST_NORTH("west_north", WEST, false),
        WEST_EAST("west_east", WEST, false),
        WEST_SOUTH("west_south", WEST, false),
        WEST_UP("west_up", WEST, false),
        WEST_DOWN("west_down", WEST, false),

        UP_NORTH("up_north", UP, false),
        UP_EAST("up_east", UP, false),
        UP_SOUTH("up_south", UP, false),
        UP_WEST("up_west", UP, false),
        UP_DOWN("up_down", UP, false),

        DOWN_NORTH("down_north", DOWN, false),
        DOWN_EAST("down_east", DOWN, false),
        DOWN_SOUTH("down_south", DOWN, false),
        DOWN_WEST("down_west", DOWN, false),
        DOWN_UP("down_up", DOWN, false),
        ;

        private final String name;
        private final plane_facing flat;

        private final boolean ordinal;

        private plane_facing(String name, plane_facing flatten, boolean ordinal) {
            this.name = name;
            this.flat = flatten;
            this.ordinal = ordinal;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        public static plane_facing fromdir(Direction dir) {
            switch (dir) {
                case NORTH:return plane_facing.NORTH;
                case EAST:return plane_facing.EAST;
                case SOUTH:return plane_facing.SOUTH;
                case WEST:return plane_facing.WEST;
                case UP:return plane_facing.UP;
                case DOWN:return plane_facing.DOWN;
            }
            return plane_facing.NORTH;
        }

        public plane_facing flatten() {
            if (this.flat == null)
                return this;
            return this.flat;
        }

        public plane_facing align(Direction dir) {
            return dir_align(dir, this.todir());
        }

        public plane_facing inv_align(Direction dir) {
            return dir_align(this.todir(),dir);
        }

        public static plane_facing dir_align(Direction first, Direction second) {
            switch (first) {
                case NORTH:
                    switch (second) {
                        case NORTH:return plane_facing.NORTH;
                        case EAST:return  plane_facing.NORTH_EAST;
                        case SOUTH:return plane_facing.NORTH_SOUTH;
                        case WEST:return  plane_facing.NORTH_WEST;
                        case UP:return    plane_facing.NORTH_UP;
                        case DOWN:return  plane_facing.NORTH_DOWN;
                    }
                case EAST:
                    switch (second) {
                        case NORTH:return plane_facing.EAST_NORTH;
                        case EAST:return  plane_facing.EAST;
                        case SOUTH:return plane_facing.EAST_SOUTH;
                        case WEST:return  plane_facing.EAST_WEST;
                        case UP:return    plane_facing.EAST_UP;
                        case DOWN:return  plane_facing.EAST_DOWN;
                    }
                case SOUTH:
                    switch (second) {
                        case NORTH:return plane_facing.SOUTH_NORTH;
                        case EAST:return  plane_facing.SOUTH_EAST;
                        case SOUTH:return plane_facing.SOUTH;
                        case WEST:return  plane_facing.SOUTH_WEST;
                        case UP:return    plane_facing.SOUTH_UP;
                        case DOWN:return  plane_facing.SOUTH_DOWN;
                    }
                case WEST:
                    switch (second) {
                        case NORTH:return plane_facing.WEST_NORTH;
                        case EAST:return  plane_facing.WEST_EAST;
                        case SOUTH:return plane_facing.WEST_SOUTH;
                        case WEST:return  plane_facing.WEST;
                        case UP:return    plane_facing.WEST_UP;
                        case DOWN:return  plane_facing.WEST_DOWN;
                    }
                case UP:
                    switch (second) {
                        case NORTH:return plane_facing.UP_NORTH;
                        case EAST:return  plane_facing.UP_EAST;
                        case SOUTH:return plane_facing.UP_SOUTH;
                        case WEST:return  plane_facing.UP_WEST;
                        case UP:return    plane_facing.UP;
                        case DOWN:return  plane_facing.UP_DOWN;
                    }
                case DOWN:
                    switch (second) {
                        case NORTH:return plane_facing.DOWN_NORTH;
                        case EAST:return  plane_facing.DOWN_EAST;
                        case SOUTH:return plane_facing.DOWN_SOUTH;
                        case WEST:return  plane_facing.DOWN_WEST;
                        case UP:return    plane_facing.DOWN_UP;
                        case DOWN:return  plane_facing.DOWN;
                    }
            }


            return fromdir(first);
        }

        private Direction todir() {
            plane_facing flat = this.flatten();

            if (flat == plane_facing.NORTH) return Direction.NORTH;
            if (flat == plane_facing.EAST ) return Direction.EAST;
            if (flat == plane_facing.SOUTH) return Direction.SOUTH;
            if (flat == plane_facing.WEST ) return Direction.WEST;
            if (flat == plane_facing.UP   ) return Direction.UP;
            if (flat == plane_facing.DOWN ) return Direction.DOWN;

            return Direction.DOWN;
        }

        public boolean isordinal() {
            return this.ordinal;
        }
    }
}
