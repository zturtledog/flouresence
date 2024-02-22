package com.confusedparrotfish.fluorescence;

import com.confusedparrotfish.fluorescence.custom.tile.hidden_light.hidden_light_tile;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// import com.mojang.datafixers.types.Type;
// import net.minecraft.Util;
// import net.minecraft.core.Registry;

public class tileregistry {
    public static final DeferredRegister<BlockEntityType<?>> tiles = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Fluorescence.MODID);

    //entities

    public static final RegistryObject<BlockEntityType<hidden_light_tile>> HIDDEN_LIGHT = tiles.register("hidden_light",
        () -> BlockEntityType.Builder.of(hidden_light_tile::new, blockregistry.HIDDEN_LIGHT.get()).build(null));

    //end of entities

//     private static <T extends BlockEntity> BlockEntityType<T> register_(String p_58957_, BlockEntityType.Builder<T> p_58958_) {
//       Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, p_58957_);
//       return Registry.register(Registry.BLOCK_ENTITY_TYPE, p_58957_, p_58958_.build(type));
//    }

    public static void register(IEventBus eventBus) {
        tiles.register(eventBus);
    }
}
