package net.nexus.primermod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.block.custom.SpeedyBlock;
import net.nexus.primermod.item.ModItemGroup;

public class ModBlocks {

    public static final Block MYTHRIL_BLOCK = registerBlock("mythril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ModItemGroup.MYTHRIL);

    public static final Block MYTHRIL_ORE = registerBlock("mythril_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4.5f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block NETHERRACK_MYTHIL_ORE = registerBlock("netherrack_mythril_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4.5f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block RAW_MYTHRIL_BLOCK = registerBlock("raw_mythril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block SPEEDY_BLOCK = registerBlock("speedy_block",
            new SpeedyBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ItemGroup.BUILDING_BLOCKS);

    //con esta función se registran los bloques nuevos, con el nombre, el bloque y el grupo del bloque, se llama a la funcion
    //registerblockitem para poder registrar el item del bloque a la vez
    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(PrimerMod.MOD_ID, name), block);
    }

    //con esta función se registran los items de los bloques nuevos, para poder verse en el inventario
    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(PrimerMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlock(){
        PrimerMod.LOGGER.info("Registrando ModBlocks para " + PrimerMod.MOD_ID);
    }
}
