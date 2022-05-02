package net.nexus.primermod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;

public class ModItems {

    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_NUGGET = registerItem("mythril_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item RAW_MYTHRIL = registerItem("raw_mythril",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item BLOOD_INGOT = registerItem("blood_ingot",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item BLOOD_SWORD = registerItem("blood_sword",
            new Item(new FabricItemSettings().group(ItemGroup.COMBAT)));

    public static final Item FRIES_POTATO = registerItem("fries_potato",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD)));




    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(PrimerMod.MOD_ID, name), item);
    }
    public static void registerModItem(){
        PrimerMod.LOGGER.info("Registrando items de mod para"+ PrimerMod.MOD_ID);

    }

}
