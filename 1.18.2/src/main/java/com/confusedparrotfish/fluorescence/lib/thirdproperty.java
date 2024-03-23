package com.confusedparrotfish.fluorescence.lib;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import java.util.function.Predicate;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class thirdproperty extends EnumProperty<thirdproperty.third_plane_facing> {
    protected thirdproperty(String p_61579_, Class<third_plane_facing> p_61580_, Collection<third_plane_facing> p_61581_) {
        super(p_61579_, p_61580_, p_61581_);
    }

    public static thirdproperty create(String p_156004_) {
        return create(p_156004_, (p_187558_) -> {
            return true;
        });
    }

    public static thirdproperty create(String p_61547_, Predicate<third_plane_facing> p_61548_) {
        return create(p_61547_, Arrays.stream(third_plane_facing.values()).filter(p_61548_).collect(Collectors.toList()));
    }

    public static thirdproperty create(String p_61550_, third_plane_facing... p_61551_) {
        return create(p_61550_, Lists.newArrayList(p_61551_));
    }

    public static thirdproperty create(String p_61544_, Collection<third_plane_facing> p_61545_) {
        return new thirdproperty(p_61544_, third_plane_facing.class, p_61545_);
    }

    public static enum third_plane_facing implements StringRepresentable {
        NORTH("north", null, true),
        EAST("east", null, true),
        SOUTH("south", null, true),
        WEST("west", null, true),
        UP("up", null, true),

        UP_NORTH("up_north", UP, false),
        UP_EAST("up_east", UP, false),
        UP_SOUTH("up_south", UP, false),
        UP_WEST("up_west", UP, false),
        ;

        private final String name;
        private final third_plane_facing flat;

        private final boolean ordinal;

        private third_plane_facing(String name, third_plane_facing flatten, boolean ordinal) {
            this.name = name;
            this.flat = flatten;
            this.ordinal = ordinal;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        public static third_plane_facing fromdir(Direction dir) {
            switch (dir) {
                case NORTH:return third_plane_facing.NORTH;
                case EAST:return third_plane_facing.EAST;
                case SOUTH:return third_plane_facing.SOUTH;
                case WEST:return third_plane_facing.WEST;
                case UP:return third_plane_facing.UP;
            }
            return third_plane_facing.NORTH;
        }

        public third_plane_facing flatten() {
            if (this.flat == null)
                return this;
            return this.flat;
        }

        public third_plane_facing align(Direction dir) {
            Direction pla = this.todir();
            switch (dir) {
                case NORTH:return third_plane_facing.NORTH;
                case EAST:return  third_plane_facing.EAST;
                case SOUTH:return third_plane_facing.SOUTH;
                case WEST:return  third_plane_facing.WEST;
                case UP:
                    switch (pla) {
                        case NORTH:return third_plane_facing.UP_NORTH;
                        case EAST:return  third_plane_facing.UP_EAST;
                        case SOUTH:return third_plane_facing.UP_SOUTH;
                        case WEST:return  third_plane_facing.UP_WEST;
                        case UP:return    third_plane_facing.UP;
                        default:
                            break;
                    }
                default:
                    break;
            }


            return fromdir(dir);
        }

        private Direction todir() {
            third_plane_facing flat = this.flatten();

            if (flat == third_plane_facing.NORTH) return Direction.NORTH;
            if (flat == third_plane_facing.EAST ) return Direction.EAST;
            if (flat == third_plane_facing.SOUTH) return Direction.SOUTH;
            if (flat == third_plane_facing.WEST ) return Direction.WEST;
            if (flat == third_plane_facing.UP   ) return Direction.UP;

            return Direction.DOWN;
        }

        public boolean isordinal() {
            return this.ordinal;
        }
    }
}

