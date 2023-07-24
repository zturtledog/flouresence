package com.confusedparrotfish.fluorescence;

import com.confusedparrotfish.fluorescence.custom.fence_topper;
import com.confusedparrotfish.fluorescence.dev.nullsafty;
import com.confusedparrotfish.fluorescence.lib.ais;
import com.confusedparrotfish.fluorescence.misc.shapes;
import com.google.common.base.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import com.confusedparrotfish.fluorescence.lib.quarterproperty.plane_facing;

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
            () -> (light.build(light.defaultprops(0, 15), false, 0)
                    .setshape(
                            shapes.tiled_lamp_south,
                            shapes.tiled_lamp_west,
                            shapes.tiled_lamp_north,
                            shapes.tiled_lamp_east,
                            shapes.tiled_lamp_up,
                            shapes.tiled_lamp_down)
                    .setrothand((x) -> {
                        return plane_facing.fromdir(x.getClickedFace());
                    })));// .isValidSpawn(Blocks::always)

    public static final RegistryObject<Block> PETER_LAMP = registerblock("peter_lamp",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_lamp_turn,
                            shapes.peter_lamp,
                            shapes.peter_lamp_turn,
                            shapes.peter_lamp,

                            shapes.peter_lamp_turn_up,
                            shapes.peter_lamp_up,
                            shapes.peter_lamp_turn_up,
                            shapes.peter_lamp_up))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> PETER_LAMP_COVERED = registerblock("peter_lamp_covered",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_lamp_turn,
                            shapes.peter_lamp,
                            shapes.peter_lamp_turn,
                            shapes.peter_lamp,

                            shapes.peter_lamp_turn_up,
                            shapes.peter_lamp_up,
                            shapes.peter_lamp_turn_up,
                            shapes.peter_lamp_up))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> DRIP_LIGHT = registerblock("drip_light",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, 2)
                    .setshape(shapes.drip_light).setupd((state, level, pos) -> {
                        Block ubuv = level.getBlockState(pos.above(1)).getBlock();
                        if (!(ubuv instanceof ChainBlock || ubuv instanceof fence_topper)) {
                            System.out.println("insk");
                            level.destroyBlock(pos, !false);
                            // level.removeBlock(pos, !false);
                        }
                    })));// .isValidSpawn(Blocks::always)

    public static final RegistryObject<Block> PENDANT_LIGHT = registerblock("pendant_light",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, 2)
                    .setshape(shapes.pendant_light)));

    public static final RegistryObject<Block> BEDSIDE_LAMP = registerblock("bedside",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, 1)
                    .setshape(shapes.bedside_lamp)));

    public static final RegistryObject<Block> BAR_LIGHT = registerblock("bar_light",
            () -> (light.build(light.defaultprops(0, 15), false, 0)
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.bar_light,
                            shapes.bar_light_turn,
                            shapes.bar_light,
                            shapes.bar_light_turn,

                            shapes.bar_light_up,
                            shapes.bar_light_turn_up,
                            shapes.bar_light_up,
                            shapes.bar_light_turn_up))
                    .setrothand(ais.horizontal_up_down_facing)));// new bar_light(light.defaultprops(0, 15)))

    public static final RegistryObject<Block> BAR_LIGHT_COVERED = registerblock("bar_light_covered",
            () -> (light.build(light.defaultprops(0, 15), false, 0)
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.bar_light,
                            shapes.bar_light_turn,
                            shapes.bar_light,
                            shapes.bar_light_turn,

                            shapes.bar_light_up,
                            shapes.bar_light_turn_up,
                            shapes.bar_light_up,
                            shapes.bar_light_turn_up))
                    .setrothand(ais.horizontal_up_down_facing)));

    public static final RegistryObject<Block> TEST = registerblock("test",
            () -> (new nullsafty(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(0.3F).sound(SoundType.ANVIL))));

    public static final RegistryObject<Block> TOPPER = registerblock("fence_topper",
            () -> (new fence_topper(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(0.3F).sound(SoundType.LANTERN))));

    // public static final RegistryObject<Block> POT_LAMP =
    // registerblock("pot_lamp",
    // ()->(new FlowerPotBlock(null, BAR_LIGHT, null)));

    public static <T extends Block> RegistryObject<T> registerblock(String name, Supplier<T> block) {
        RegistryObject<T> retval = Fluorescence.blocks.register(name, block);

        itemregistry.registerblockitem(name, retval);

        return retval;
    }

    // public static <T extends Block> RegistryObject<T>
    // registerblockwithtooltip(String tip, String name, Supplier<T> block) {
    // RegistryObject<T> retval = Fluorescence.blocks.register(name, block);

    // Fluorescence.items.register(name, () -> new tooltipblockitem(block.get(),
    // new
    // Item.Properties().tab(Fluorescence.fluorescencetab)).setkey(tip));//.tab(Terrq.terrqtab)

    // return retval;
    // }
}
