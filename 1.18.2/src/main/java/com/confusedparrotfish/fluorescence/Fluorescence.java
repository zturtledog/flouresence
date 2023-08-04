package com.confusedparrotfish.fluorescence;

import com.confusedparrotfish.fluorescence.misc.keybinds;
import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Fluorescence.MODID)
public class Fluorescence {
    public static final String MODID = "fluorescence";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static CreativeModeTab fluorescencetab = new CreativeModeTab("Fluorescence") {
        // ItemStack ima = new ItemStack(blockregistry.TILED_LIGHT_PURPLE.get().asItem());

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(blockregistry.DRIP_LIGHT.get().asItem());
        }

        public ItemStack getIconItem() {
            return new ItemStack(blockregistry.DRIP_LIGHT.get().asItem());
        };
    };

    public Fluorescence() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);        
        modEventBus.addListener(this::clientsetup);

        blockregistry.register(modEventBus);
        itemregistry.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void clientsetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(blockregistry.TOPPER.get(), RenderType.cutout());        
        ItemBlockRenderTypes.setRenderLayer(blockregistry.SCONCE.get(), RenderType.cutout());

        keybinds.init();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
