package net.ananaseek.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties LEVIO = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0.2f)
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 35), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20), 0.4f).build();
}
