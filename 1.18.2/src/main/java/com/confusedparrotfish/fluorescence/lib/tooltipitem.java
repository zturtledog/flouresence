package com.confusedparrotfish.fluorescence.lib;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class tooltipitem extends Item {
    public tooltipitem(Properties p_41383_) {
        super(p_41383_);
    }

    private String key = "";

    public Item setkey(String kek) {
        key = kek;
        return this;
    } 
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components,
            TooltipFlag p_41424_) {
        
        if (key == "") {
            key = stack.getItem().toString();
        }
        components.add((new TranslatableComponent("tooltip.fluorescence."+key)).withStyle(ChatFormatting.GRAY));
        // components.add(Component.translatable("tooltip.terrq."+stack.getItem()).withStyle(ChatFormatting.GRAY));

        super.appendHoverText(stack, level, components, p_41424_);
    }
}
