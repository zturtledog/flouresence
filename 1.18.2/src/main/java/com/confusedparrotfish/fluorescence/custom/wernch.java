package com.confusedparrotfish.fluorescence.custom;

import com.confusedparrotfish.fluorescence.light;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class wernch extends Item {
    public wernch(Properties p_41383_) {
        super(p_41383_);
    }

    public InteractionResult useOn(UseOnContext context) {
        Level lvl = context.getLevel();
        if (!lvl.isClientSide) {
            BlockState state = lvl.getBlockState(context.getClickedPos());
            if (state.getBlock() instanceof light) {
                lvl.setBlockAndUpdate(context.getClickedPos(), 
                    state.cycle(light.MODE).setValue(light.LIT,state.getValue(light.MODE)==1));
                // lvl.setBlockAndUpdate(null, state)

                System.out.println("mode: " + inttomode(state.cycle(light.MODE).getValue(light.MODE)));
            }
        }
        return InteractionResult.CONSUME_PARTIAL;
    }

    private String inttomode(int value) {
        switch (value) {
            case 0: return "redstone";
            case 1: return "click";
            case 2: return "all-on";
            default: return "none";
        }
    }
}