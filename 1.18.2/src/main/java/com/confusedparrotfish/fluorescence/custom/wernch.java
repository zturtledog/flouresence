package com.confusedparrotfish.fluorescence.custom;

import java.util.List;

import javax.annotation.Nullable;

import com.confusedparrotfish.fluorescence.light;
import com.confusedparrotfish.fluorescence.misc.keybinds;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class wernch extends Item {
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
                if (keybinds.shift_mod.isDown()) {
                    if (plr != null)
                        plr.sendMessage(
                                new TextComponent("current mode: " + inttomode(state.getValue(light.MODE)))
                                        .withStyle(ChatFormatting.LIGHT_PURPLE),
                                null);
                } else {
                    lvl.setBlockAndUpdate(context.getClickedPos(),
                            state.cycle(light.MODE).setValue(light.LIT,
                                    (state.getValue(light.MODE) == 1) || (state.getValue(light.MODE) == 2
                                            && context.getLevel().hasNeighborSignal(context.getClickedPos()))));

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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components,
            TooltipFlag p_41424_) {
        if (keybinds.shift_mod != null)
        components.add(new TextComponent((new TranslatableComponent("tooltip.fluorescence.wernch")).getString()
                .replace("<?>", keybinds.shift_mod.getKey().getDisplayName().getString())).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, level, components, p_41424_);
    }
}