package com.confusedparrotfish.fluorescence;

import com.confusedparrotfish.fluorescence.custom.block.fence_topper;
import com.confusedparrotfish.fluorescence.custom.block.sconce;
import com.confusedparrotfish.fluorescence.custom.tile.hidden_light;
import com.confusedparrotfish.fluorescence.dev.nullsafty;
import com.confusedparrotfish.fluorescence.lib.ais;
import com.confusedparrotfish.fluorescence.misc.shapes;
import com.google.common.base.Supplier;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.Direction;
//flourecsence-v1.18.2_3_dev_beta
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
                    .setshape(shapes.drip_light).setsurv((state, reader, pos) -> {
                        return Block.canSupportCenter(reader, pos.relative(Direction.UP), Direction.DOWN);
                    }).setupd((state, level, pos) -> {
                        if (!state.canSurvive(level, pos)) {
                            level.destroyBlock(pos, !false);
                        }
                    })));

    public static final RegistryObject<Block> PENDANT_LIGHT = registerblock("pendant_light",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, 2)
                    .setshape(shapes.pendant_light)));

    public static final RegistryObject<Block> BEDSIDE_LAMP = registerblock("bedside",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, 1)
                    .setshape(shapes.bedside_lamp)));

    public static final RegistryObject<Block> FIRE_PIT = registerblock("pit",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.METAL), true, 1)
                    .setshape(shapes.fire_pit).setsurv((state, reader, pos) -> {
                        return Block.canSupportCenter(reader, pos.relative(Direction.DOWN), Direction.UP);
                    }).setupd((state, level, pos) -> {
                        if (!state.canSurvive(level, pos)) {
                            level.destroyBlock(pos, !false);
                        }
                    }).setsmokes(true).setclickitem(Items.FLINT_AND_STEEL)));

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

    public static final RegistryObject<Block> SCONCE = registerblock_no_item("sconce",
            () -> (new sconce(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(0.3F).sound(SoundType.LANTERN))));

    public static final RegistryObject<Block> DROP_LIGHT = registerblock("drop_light",
            () -> (light.build(light.defaultprops(0, 15)
                    .strength(0.3F).sound(SoundType.GLASS), true, 2)
                    .setshape(shapes.drop_light).setsurv((state, reader, pos) -> {
                        return Block.canSupportCenter(reader, pos.relative(Direction.UP), Direction.DOWN)
                                || Block.canSupportCenter(reader, pos.relative(Direction.DOWN), Direction.UP);
                    }).setupd((state, level, pos) -> {
                        if (!state.canSurvive(level, pos)) {
                            level.destroyBlock(pos, !false);
                        }
                    })));

    public static final RegistryObject<Block> HIDDEN_LIGHT = registerblock_no_item("hidden_light",
            () -> (hidden_light.build(light.defaultprops(1, 15), true, 2)));

    public static final RegistryObject<Block> PETER_TUBE_LIGHT = registerblock("peter_tube_light",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_tube_light,
                            shapes.peter_tube_light_turn,
                            shapes.peter_tube_light,
                            shapes.peter_tube_light_turn,

                            shapes.peter_tube_light_up,
                            shapes.peter_tube_light_turn_up,
                            shapes.peter_tube_light_up,
                            shapes.peter_tube_light_turn_up))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> PETER_GRILLE_LIGHT = registerblock("peter_grille_light",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_tube_light,
                            shapes.peter_tube_light_turn,
                            shapes.peter_tube_light,
                            shapes.peter_tube_light_turn,

                            shapes.peter_tube_light_up,
                            shapes.peter_tube_light_turn_up,
                            shapes.peter_tube_light_up,
                            shapes.peter_tube_light_turn_up))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> PETER_DOUBLE_LIGHT = registerblock("peter_double_light",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_double_light_turn,
                            shapes.peter_double_light,
                            shapes.peter_double_light_turn,
                            shapes.peter_double_light,

                            shapes.peter_double_light_turn_up,
                            shapes.peter_double_light_up,
                            shapes.peter_double_light_turn_up,
                            shapes.peter_double_light_up))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> PETER_LONG_LIGHT = registerblock("peter_long_light",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_wood_light_down,
                            shapes.peter_wood_light_down_turn,
                            shapes.peter_wood_light_down,
                            shapes.peter_wood_light_down_turn,

                            shapes.peter_wood_light_up,
                            shapes.peter_wood_light_up_turn,
                            shapes.peter_wood_light_up,
                            shapes.peter_wood_light_up_turn))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> PETER_LONG_LIGHT_COVERED = registerblock("peter_long_light_covered",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_wood_light_down,
                            shapes.peter_wood_light_down_turn,
                            shapes.peter_wood_light_down,
                            shapes.peter_wood_light_down_turn,

                            shapes.peter_wood_light_up,
                            shapes.peter_wood_light_up_turn,
                            shapes.peter_wood_light_up,
                            shapes.peter_wood_light_up_turn))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> PETER_TROUGH_LIGHT = registerblock("peter_trough_light",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_up_down_facing_shape(
                            shapes.peter_tube_light,
                            shapes.peter_tube_light_turn,
                            shapes.peter_tube_light,
                            shapes.peter_tube_light_turn,

                            shapes.peter_tube_light_up,
                            shapes.peter_tube_light_turn_up,
                            shapes.peter_tube_light_up,
                            shapes.peter_tube_light_turn_up))
                    .setrothand(ais.horizontal_up_down_facing));

    public static final RegistryObject<Block> PETER_WOOD_LIGHT = registerblock("peter_wood_light",
            () -> (light.build(light.defaultprops(0, 15), false, 0))
                    .setshape(light.horizontal_shape(
                            shapes.peter_wood_light_north,
                            shapes.peter_wood_light_east,
                            shapes.peter_wood_light_south,
                            shapes.peter_wood_light_west,

                            shapes.peter_wood_light_up,
                            shapes.peter_wood_light_up_turn,
                            shapes.peter_wood_light_up,
                            shapes.peter_wood_light_up_turn,

                            shapes.peter_wood_light_down,
                            shapes.peter_wood_light_down_turn,
                            shapes.peter_wood_light_down,
                            shapes.peter_wood_light_down_turn))
                    .setrothand(ais.horizontal_multi_facing));

    // public static final RegistryObject<Block> POWERED_CHAIN =
    // registerblock("powered_chain",
    // () -> (new poweredchain(BlockBehaviour.Properties.of(Material.METAL)
    // .strength(0.3F).sound(SoundType.LANTERN))));

    // public static final RegistryObject<Block> SPOT = registerblock("spop",
    // () -> (new spot(BlockBehaviour.Properties.of(Material.BUILDABLE_GLASS)
    // .strength(0.3F).sound(SoundType.GLASS))));

    // public static final RegistryObject<Block> POT_LAMP =
    // registerblock("pot_lamp",
    // ()->(new FlowerPotBlock(null, BAR_LIGHT, null)));

    public static <T extends Block> RegistryObject<T> registerblock(String name, Supplier<T> block) {
        RegistryObject<T> retval = Fluorescence.blocks.register(name, block);

        itemregistry.registerblockitem(name, retval);

        return retval;
    }

    public static <T extends Block> RegistryObject<T> registerblock_no_item(String name, Supplier<T> block) {
        RegistryObject<T> retval = Fluorescence.blocks.register(name, block);
        // itemregistry.registertooltipblockitem(name, retval,tip);
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

/*
 * .setupd((state, level, pos) -> {
 * Block ubuv = level.getBlockState(pos.above(1)).getBlock();
 * if (!(ubuv instanceof ChainBlock || ubuv instanceof fence_topper || ubuv
 * instanceof sconce)) {
 * System.out.println("insk");
 * level.destroyBlock(pos, !false);
 * // level.removeBlock(pos, !false);
 * }
 * })
 */