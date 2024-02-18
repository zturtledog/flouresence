package com.confusedparrotfish.fluorescence.custom;

import java.util.List;

import javax.annotation.Nullable;

import com.confusedparrotfish.fluorescence.misc.keybinds;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class sconce_item extends BlockItem {
    public sconce_item(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    @Override
    @SuppressWarnings("null")
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components,
            TooltipFlag p_41424_) {
        if (keybinds.shift_mod != null) {
            components.add(new TextComponent((new TranslatableComponent("tooltip.fluorescence.sconce")).getString()
                    .replace("<?:1>", keybinds.shift_mod.getKey().getDisplayName().getString())
                    .replace("<?:2>", (new TranslatableComponent("misc.fluorescence.shiftlocal")).getString()))
                    .withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, level, components, p_41424_);
    }
}

// components.add(new TextComponent((new TranslatableComponent("tooltip.fluorescence.sconce")).getString()
//         .replace("<?>",
//                 (level != null && level.getServer() != null && !level.getServer().isDedicatedServer() && level.players().size() <= 2 )
//                         ? keybinds.shift_mod.getKey().getDisplayName().getString()
//                         : (new TranslatableComponent("misc.fluorescence.shiftlocal")).getString()))
//         .withStyle(ChatFormatting.GRAY));
// if ((level != null)&&(level.getServer() != null)) System.out.println(level.getServer().getLocalIp()); else System.out.println("_");