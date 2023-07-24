package com.confusedparrotfish.fluorescence.dev;

import java.util.Random;

import com.confusedparrotfish.fluorescence.lib.drawglyphs;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class nullsafty extends Block {
    public nullsafty(Properties p_49795_) {
        super(p_49795_);
    }

    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos npos,
            boolean p_55671_) {
        if (!world.isClientSide) {Random rand = new Random();
            drawglyphs.hexagon((ServerLevel)world, Vec3.atBottomCenterOf(pos), 3, 0.3f);
        switch (rand.nextInt(5)) {
            case 0: drawglyphs.air((ServerLevel)world, Vec3.atBottomCenterOf(pos), 3, 0.3f);break;  
            case 1: drawglyphs.earth((ServerLevel)world, Vec3.atBottomCenterOf(pos), 3, 0.3f);break;
            case 2: drawglyphs.fire((ServerLevel)world, Vec3.atBottomCenterOf(pos), 3, 0.3f);break;
            case 3: drawglyphs.power((ServerLevel)world, Vec3.atBottomCenterOf(pos), 3, 0.3f);break;
            case 4: drawglyphs.water((ServerLevel)world, Vec3.atBottomCenterOf(pos), 3, 0.3f);break;
        }}
    }
}
