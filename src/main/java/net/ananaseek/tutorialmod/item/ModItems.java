package net.ananaseek.tutorialmod.item;

import net.ananaseek.tutorialmod.TutorialMod;
import net.ananaseek.tutorialmod.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(50)));
    public static final RegistryObject<Item> LEVIO = ITEMS.register("levio",
            () -> new Item(new Item.Properties().food(ModFoods.LEVIO).stacksTo(32)));
    public static final RegistryObject<Item> FIRE_BALL = ITEMS.register("fire_ball",
            () -> new FireBallItem(new Item.Properties()));
    public static final RegistryObject<Item> SILLY_BOW = ITEMS.register("silly_bow",
            () -> new SillyBowItem(new Item.Properties().durability(200)));
    public static final RegistryObject<Item> SILLY_ARROW = ITEMS.register("silly_arrow",
            () -> new SillyArrowItem(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
