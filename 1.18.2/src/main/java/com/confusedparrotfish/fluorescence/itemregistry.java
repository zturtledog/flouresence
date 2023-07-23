package com.confusedparrotfish.fluorescence;

import net.minecraft.world.item.Item;

import com.confusedparrotfish.fluorescence.custom.wernch;
import com.google.common.base.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class itemregistry {
    public static void register(IEventBus bus) {
        Fluorescence.items.register(bus);
    }

    public static final RegistryObject<Item> WERNCH = registeritem("wernch", 
        ()->(new wernch((new Item.Properties()).tab(Fluorescence.fluorescencetab))));

    public static <T extends Item> RegistryObject<T> registeritem(String name, Supplier<T> item) {
        return Fluorescence.items.register(name, item);
    }

    public static <T extends Block> void registerblockitem(String name, RegistryObject<T> block) {
        Fluorescence.items.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(Fluorescence.fluorescencetab)));//.tab(Terrq.terrqtab)
    }
}
