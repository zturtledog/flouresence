package com.confusedparrotfish.fluorescence.redstone;

import com.confusedparrotfish.fluorescence.lib.generic;
import com.confusedparrotfish.fluorescence.misc.shapes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.Shapes;

public class redstonenib extends generic {
    public static Properties def = BlockBehaviour.Properties.of(Material.METAL, MaterialColor.NONE)
            .requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion();

    public redstonenib(Properties p_49795_) {
        super(p_49795_);

        irot = (x) -> {
            return x.getClickedFace();
        };
        ishape = (s,g,p,c)->{
            switch (s.getValue(FACING)) {
                case SOUTH: return shapes.rednib_north;
                case NORTH: return shapes.rednib_south;
                case WEST : return shapes.rednib_east;
                case EAST : return shapes.rednib_west;
                case DOWN : return shapes.rednib_up;
                case UP   : return shapes.rednib_down;
            
                default   : return Shapes.block();
            }
        };
        iplace = (place) -> {
            place.getLevel().updateNeighborsAt(place.getClickedPos(), this);

            return this.defaultBlockState();
        };
    }

    public int getDirectSignal(BlockState state, BlockGetter getter, BlockPos pos, Direction dir) {
        return state.getSignal(getter, pos, dir);
    }

    public int getSignal(BlockState state, BlockGetter p_55550_, BlockPos p_55551_, Direction dir) {
        if (state.getValue(FACING) == dir) return 15;
        return 0;
    }

    public boolean isSignalSource(BlockState p_55213_) {
        return true;
    }
}
