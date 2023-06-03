package com.confusedparrotfish.fluorescence;

import com.confusedparrotfish.fluorescence.lights.bar_light;
import com.confusedparrotfish.fluorescence.lights.light;
import com.confusedparrotfish.fluorescence.lights.light.rotype;
import com.confusedparrotfish.fluorescence.misc.shapes;
import com.google.common.base.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class blockregistry {
    public static void register(IEventBus bus) {
        Fluorescence.blocks.register(bus);
    }

    // public static final RegistryObject<Block> EXAMPLE_BLOCK = registerblock("example_light",
    //         () -> new light(
    //                 BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).lightLevel(light.lightpropogate(0, 15))));

    public static final RegistryObject<Block> EXAMPLE_LIGHT = registerblock("example_light",
            () -> (light.build(BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).lightLevel(light.lightpropogate(0, 15))
                .strength(0.3F).sound(SoundType.GLASS),false,true)
                .setupd((state, level, pos, rand) -> {
                    System.out.println("eeee");
                })));//.isValidSpawn(Blocks::always)

    public static final RegistryObject<Block> TILED_LIGHT_PURPLE = registerblock("tiled_light",
            () -> (light.build(light.defaultprops(0,15),false,true)
                .setupd((state, level, pos, rand) -> {
                    System.out.println("eeee");
                }).setshape(
                    shapes.tiled_lamp_south,
                    shapes.tiled_lamp_west,
                    shapes.tiled_lamp_north,
                    shapes.tiled_lamp_east,
                    shapes.tiled_lamp_up,
                    shapes.tiled_lamp_down)
                .setrothand(rotype.ALL, (x) -> {return x.getClickedFace();})));//.isValidSpawn(Blocks::always)

    public static final RegistryObject<Block> PETER_LAMP = registerblock("peter_lamp", 
            () -> (light.build(light.defaultprops(0,15), false, false)).setshape(shapes.peter_lamp));

    public static final RegistryObject<Block> DRIP_LIGHT = registerblock("drip_light",
            () -> (light.build(light.defaultprops(0, 15)
                .strength(0.3F).sound(SoundType.GLASS),true,false)
                .setshape(shapes.drip_light).setupd((state, level, pos, rand)->{
                    if (!(level.getBlockState(pos.above(1)).getBlock() instanceof ChainBlock)) {
                        System.out.println("insk");
                    }
                })));//.isValidSpawn(Blocks::always)

    //redstone comp
    // public static final RegistryObject<Block> POWERED_CHAIN = registerblock("powered_chain", 
    //     () -> (new poweredchain(poweredchain.defaultprops())));

    public static final RegistryObject<Block> BAR_LIGHT = registerblock("bar_light", 
            () -> (new bar_light(light.defaultprops(0, 15))));

    public static <T extends Block> RegistryObject<T> registerblock(String name, Supplier<T> block) {
        RegistryObject<T> retval = Fluorescence.blocks.register(name, block);

        itemregistry.registerblockitem(name, retval);

        return retval;
    }
}
