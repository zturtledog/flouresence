package com.confusedparrotfish.fluorescence.custom;

import com.confusedparrotfish.fluorescence.Fluorescence;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class fez {
    public static class fezmaterial implements ArmorMaterial {
        private static final int[] healthperslot = new int[] { 13, 15, 16, 11 };
        private int[] slotprot = new int[] { 2, 5, 6, 2 };
        private int durabilitymulti = 12;
        private int ench = 12;

        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return healthperslot[slot.getIndex()] * durabilitymulti;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return slotprot[slot.getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return ench;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.DIAMOND);
        }

        @Override
        public String getName() {
            return Fluorescence.MODID + ":fez";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    }
}
