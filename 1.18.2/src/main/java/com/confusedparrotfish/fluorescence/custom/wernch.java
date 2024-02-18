package com.confusedparrotfish.fluorescence.custom;

import java.util.List;

import javax.annotation.Nullable;

import com.confusedparrotfish.fluorescence.light;
import com.confusedparrotfish.fluorescence.Fluorescence.lightnonsencery;
import com.confusedparrotfish.fluorescence.misc.keybinds;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
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
            if (state.getBlock() instanceof lightnonsencery) {
                MinecraftServer svr = lvl.getServer();
                if (plr != null)
                    /*
                     * if integrated and players less than 2 -> key bind else shift
                     */

                    if (svr != null && !svr.isDedicatedServer() && lvl.players().size() < 2
                            ? keybinds.shift_mod.isDown()
                            : plr.isShiftKeyDown()) {
                        plr.sendMessage(
                                new TextComponent("current mode: " + inttomode(state.getValue(light.MODE)))
                                        .withStyle(ChatFormatting.LIGHT_PURPLE),
                                Util.NIL_UUID);
                    } else {
                        lvl.setBlockAndUpdate(context.getClickedPos(),
                                state.cycle(light.MODE).setValue(light.LIT,
                                        (state.getValue(light.MODE) == 1) || (state.getValue(light.MODE) == 2
                                                && context.getLevel().hasNeighborSignal(context.getClickedPos()))));

                        plr.sendMessage(
                                new TextComponent("mode: " + inttomode(state.cycle(light.MODE).getValue(light.MODE)))
                                        .withStyle(ChatFormatting.AQUA),
                                Util.NIL_UUID);
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
    @SuppressWarnings("null")
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components,
            TooltipFlag p_41424_) {
        if (keybinds.shift_mod != null)
            components.add(new TextComponent((new TranslatableComponent("tooltip.fluorescence.wernch")).getString()
                    .replace("<?:1>", keybinds.shift_mod.getKey().getDisplayName().getString())
                    .replace("<?:2>", (new TranslatableComponent("misc.fluorescence.shiftlocal")).getString()))
                    .withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, level, components, p_41424_);
    }
}
// components.add(new TextComponent((new
// TranslatableComponent("tooltip.fluorescence.wernch")).getString()
// .replace("<?>",
// //
// (level != null && level.getServer() != null &&
// !level.getServer().isDedicatedServer() && level.players().size() <= 2 )
// ? keybinds.shift_mod.getKey().getDisplayName().getString()
// : (new TranslatableComponent("misc.fluorescence.shiftlocal")).getString()))
// .withStyle(ChatFormatting.GRAY));