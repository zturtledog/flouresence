package com.confusedparrotfish.fluorescence;

import com.confusedparrotfish.fluorescence.light.rotype;
import com.confusedparrotfish.fluorescence.lib.ais;
import com.confusedparrotfish.fluorescence.misc.shapes;
import com.google.common.base.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class blockregistry {
    public static void register(IEventBus bus) {
        Fluorescence.blocks.register(bus);
    }

    // public static final RegistryObject<Block> EXAMPLE_BLOCK =
    // registerblock("example_light",
    // () -> new light(
    // BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS).lightLevel(light.lightpropogate(0,
    // 15))));

    public static final RegistryObject<Block> TILED_LIGHT_PURPLE = registerblock("tiled_light",
            () -> (light.build(light.defaultprops(0, 15), false, true)
                    .setshape(
                            shapes.tiled_lamp_south,
                            shapes.tiled_lamp_west,
                            shapes.tiled_lamp_north,
                            shapes.tiled_lamp_east,
                            shapes.tiled_lamp_up,
                            shapes.tiled_lamp_down)
                    .setrothand(rotype.ALL, (x) -> {
                        return x.getClickedFace();
                    })));// .isValidSpawn(Blocks::always)

    public static final RegistryObject<Block> PETER_LAMP = registerblock("peter_lamp",
            () -> (light.build(light.defaultprops(0, 15), false, true))
                .setshape(shapes.peter_lamp_turn,
                    shapes.peter_lamp,
                    shapes.peter_lamp_turn,
                    shapes.peter_lamp,
                    Shapes.block(),
                    Shapes.block())
                .setrothand(rotype.ALL, ais.horizontal_facing));

    public static final RegistryObject<Block> DRIP_LIGHT = registerblock("drip_light",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, false)
                    .setshape(shapes.drip_light).setupd((state, level, pos) -> {
                        if (!(level.getBlockState(pos.above(1)).getBlock() instanceof ChainBlock)) {
                            System.out.println("insk");
                            level.destroyBlock(pos, !false);
                            // level.removeBlock(pos, !false);
                        }
                    })));// .isValidSpawn(Blocks::always)

    public static final RegistryObject<Block> PENDANT_LIGHT = registerblock("pendant_light",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, false)
                    .setshape(shapes.pendant_light)));

    public static final RegistryObject<Block> BAR_LIGHT = registerblock("bar_light",
            () -> (light.build(light.defaultprops(0, 15), false, true)
                    .setshape(shapes.bar_light,
                            shapes.bar_light_turn,
                            shapes.bar_light,
                            shapes.bar_light_turn,
                            Shapes.block(),
                            Shapes.block())
                    .setrothand(rotype.ALL, ais.horizontal_facing)));// new bar_light(light.defaultprops(0, 15)))

    public static final RegistryObject<Block> BAR_LIGHT_COVERED = registerblock("bar_light_covered",
            () -> (light.build(light.defaultprops(0, 15), false, true)
                    .setshape(shapes.bar_light,
                            shapes.bar_light_turn,
                            shapes.bar_light,
                            shapes.bar_light_turn,
                            Shapes.block(),
                            Shapes.block())
                    .setrothand(rotype.ALL, ais.horizontal_facing)));

    public static <T extends Block> RegistryObject<T> registerblock(String name, Supplier<T> block) {
        RegistryObject<T> retval = Fluorescence.blocks.register(name, block);

        itemregistry.registerblockitem(name, retval);

        return retval;
    }
}
