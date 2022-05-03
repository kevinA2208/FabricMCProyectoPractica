package net.nexus.primermod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.item.custom.DowsingRodItem;

public class ModItems {

    //Se crean los items, con su nombre, el tipo de item, y el grupo de item
    //los tres pueden ser customizados
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

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ItemGroup.MISC).maxDamage(16).rarity(Rarity.EPIC)));




    //se crea el constructor para los items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(PrimerMod.MOD_ID, name), item);
    }

    //Se envia un mensaje de información por consola, cuando el mod de items haya cargado en minecraft
    public static void registerModItem(){
        PrimerMod.LOGGER.info("Registrando items de mod para"+ PrimerMod.MOD_ID);

    }

}
