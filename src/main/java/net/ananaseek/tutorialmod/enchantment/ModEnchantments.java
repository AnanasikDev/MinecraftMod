package net.ananaseek.tutorialmod.enchantment;

import net.ananaseek.tutorialmod.TutorialMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TutorialMod.MOD_ID);

    public static RegistryObject<Enchantment> PATH_BUILDER =
            ENCHANTMENTS.register("path_builder", () -> new PathBuilderEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{ EquipmentSlot.MAINHAND }));

    public static RegistryObject<Enchantment> EXPLOSIVE =
            ENCHANTMENTS.register("explosive", () -> new PathBuilderEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{ EquipmentSlot.MAINHAND }));

    public static void register(IEventBus eventBus){
        ENCHANTMENTS.register(eventBus);
    }
}
