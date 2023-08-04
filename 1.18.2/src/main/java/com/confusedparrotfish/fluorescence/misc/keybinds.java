package com.confusedparrotfish.fluorescence.misc;

import com.confusedparrotfish.fluorescence.Fluorescence;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public final class keybinds {
    public static KeyMapping shift_mod;
    public static String catagory = "key.catagories.fluorescence";

    public static void init() {
        shift_mod = registerKey("modifier1", catagory, InputConstants.KEY_LSHIFT);
    }

    private static KeyMapping registerKey(String name, String category, int keycode) {
        final var key = new KeyMapping("key." + Fluorescence.MODID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
