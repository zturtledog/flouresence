package com.confusedparrotfish.fluorescence;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class itemregistry {
    public static void register(IEventBus bus) {
        Fluorescence.items.register(bus);
    }

    public static <T extends Block> void registerblockitem(String name, RegistryObject<T> block) {
        Fluorescence.items.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(Fluorescence.fluorescencetab)));//.tab(Terrq.terrqtab)
    }
}
