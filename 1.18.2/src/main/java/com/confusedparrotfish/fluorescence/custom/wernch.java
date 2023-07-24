package com.confusedparrotfish.fluorescence.custom;

import com.confusedparrotfish.fluorescence.light;
import com.confusedparrotfish.fluorescence.lib.tooltipitem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class wernch extends tooltipitem {
    public wernch(Properties p_41383_) {
        super(p_41383_);
    }

    // redo for lang
    public InteractionResult useOn(UseOnContext context) {
        Level lvl = context.getLevel();
        Player plr = context.getPlayer();
        if (!lvl.isClientSide) {
            BlockState state = lvl.getBlockState(context.getClickedPos());
            if (state.getBlock() instanceof light) {
                if (Screen.hasShiftDown()) {
                    if (plr != null)
                        plr.sendMessage(
                                new TextComponent("current mode: " + inttomode(state.getValue(light.MODE)))
                                        .withStyle(ChatFormatting.LIGHT_PURPLE),
                                null);
                } else {
                    lvl.setBlockAndUpdate(context.getClickedPos(),
                            state.cycle(light.MODE).setValue(light.LIT, state.getValue(light.MODE) == 1));
                    // lvl.setBlockAndUpdate(null, state)

                    // System.out.println("mode: " + inttomode(state.cycle(light.MODE).getValue(light.MODE)));

                    if (plr != null)
                        plr.sendMessage(
                                new TextComponent("mode: " + inttomode(state.cycle(light.MODE).getValue(light.MODE)))
                                        .withStyle(ChatFormatting.AQUA),
                                null);
                }
            }
        }
        return InteractionResult.CONSUME;
    }

    private String inttomode(int value) {
        switch (value) {
            case 0:
                return "redstone";
            case 1:
                return "click";
            case 2:
                return "all-on";
            default:
                return "none";
        }
    }
}