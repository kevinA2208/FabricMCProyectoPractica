package net.nexus.primermod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.block.ModBlocks;
import net.nexus.primermod.item.custom.*;
import net.nexus.primermod.sound.ModSounds;

public class ModItems {

    //Se crean los items, con su nombre, el tipo de item, y el grupo de item
    //los tres pueden ser customizados

    //Materiales u ores
    public static final Item BLOOD_INGOT = registerItem("blood_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.BLOOD)));
    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_NUGGET = registerItem("mythril_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item RAW_MYTHRIL = registerItem("raw_mythril",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));


    //ARMAS O HERRAMIENTAS
    //Para crear las herramientas se crean en base al tipo de herramienta o arma, en este caso se crea un item de tipo SwordItem, se le agrega el
    //daño bonus por ataque, en este caso es 1 pero se puede dejar en 0, y la velocidad de ataque si la pones en 0 va mas rapido de lo normal la
    // recarga del arma o herramienta al atacar, por lo tanto se pone en negativo para que vaya igual o similar o un poco mejor a herramientas
    // del minecraft
    public static final Item BLOOD_SWORD = registerItem("blood_sword",
            new ModBloodSwordItem(ModToolMaterials.BLOOD, 1, -3.0F, new FabricItemSettings().group(ModItemGroup.BLOOD)));

    public static final Item MYTHRIL_SWORD = registerItem("mythril_sword",
            new SwordItem(ModToolMaterials.MYTHRIL, 1, -2.4F, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    //en este caso se hace la herramienta hacha con el ModAxeItem que es el item original de minecraft y se le modifican los stats
    public static final Item MYTHRIL_AXE = registerItem("mythril_axe",
            new ModAxeItem(ModToolMaterials.MYTHRIL, 1, -3.2F,new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_HOE = registerItem("mythril_hoe",
            new ModHoeItem(ModToolMaterials.MYTHRIL, 0, -3.2F,new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_SHOVEL = registerItem("mythril_shovel",
            new ShovelItem(ModToolMaterials.MYTHRIL, 0, -3.2F, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_PICKAXE = registerItem("mythril_pickaxe",
            new ModPickaxeItem(ModToolMaterials.MYTHRIL, 0, -2.8F, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item KAUPENBOW = registerItem("kaupenbow",
            new BowItem(new FabricItemSettings().group(ItemGroup.COMBAT).maxDamage(640)));

    //ARMADURAS
    //Se crea el casco de tipo mythril, se crea el objeto en base al ModArmorItem de minecraft, con esa clase, le da un efecto completo
    // a toda nuestra armadura, tan solo poniendosela en una parte de la armadura, despues se le agrega el material mythril y el equipmentSlot que
    //en este caso es la cabeza pq es el casco

    public static final Item MYTHRIL_HELMET = registerItem("mythril_helmet",
            new ModArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.HEAD, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_CHESTPLATE = registerItem("mythril_chestplate",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.CHEST, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_LEGGINGS = registerItem("mythril_leggings",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.LEGS, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    public static final Item MYTHRIL_BOOTS = registerItem("mythril_boots",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.FEET, new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    //COMIDA
    //Se crea el item de comida, y al final se le pone .food para que sea comestible, y se le añade el foodComponent del tipo de comida que sea
    public static final Item FRIES_POTATO = registerItem("fries_potato",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFoodComponents.FRIES_POTATO)));

    public static final Item SODA_DRINK = registerItem("soda_drink",
            new SodaDrinkItem(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFoodComponents.SODA_DRINK)));

    public static final Item GRAPE = registerItem("grape",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFoodComponents.GRAPE)));

    //SEMILLAS
    //Se crean las semillas de uva para plantarlas, es necesario crearlo con el AliasedBlockItem, para asociar el bloque en el que se plantarán estas semillas
    //en este caso se le agrega el bloque GRAPE_VINE
    public static final Item GRAPE_SEEDS = registerItem("grape_seeds",
            new AliasedBlockItem(ModBlocks.GRAPE_VINE,new FabricItemSettings().group(ItemGroup.FOOD)));



    //ITEMS ESPECIALES
    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ItemGroup.MISC).maxDamage(16).rarity(Rarity.EPIC)));

    public static final Item DATA_TABLET = registerItem("data_tablet",
            new DataTabletItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));


    //COMBUSTIBLES O POLVOS
    public static final Item LILAC_FLOWER_BULB = registerItem("lilac_flower_bulb",
            new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));

    public static final Item MAGIC_MYTHRIL_DUST = registerItem("magic_mythril_dust",
            new Item(new FabricItemSettings().group(ModItemGroup.MYTHRIL)));

    //DISCOS DE MUSICA
    //Se crea el disco de musica, con la clase ModMusicDiscItem, donde tiene unos parametros
    //el primero es el comparatorOutput, el segundo es el sonido que se crea desde la clase
    //ModSounds, despues se le agrega al grupo MISC y se pone el maxCount(1)
    //para que el disco no se stackee y solo sea 1 en el inventario.
    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            new ModMusicDiscItem(7, ModSounds.BAR_BRAWL,new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));



    //CONSTRUCTORES

    //se crea el constructor para los items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(PrimerMod.MOD_ID, name), item);
    }

    //Se envia un mensaje de información por consola, cuando el mod de items haya cargado en minecraft
    public static void registerModItem(){
        PrimerMod.LOGGER.info("Registrando items de mod para"+ PrimerMod.MOD_ID);

    }

}
