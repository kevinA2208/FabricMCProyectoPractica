package net.nexus.primermod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.block.custom.SpeedyBlock;
import net.nexus.primermod.item.ModItemGroup;
import net.nexus.primermod.item.custom.ModPressurePlateBlock;
import net.nexus.primermod.item.custom.ModStoneButtonBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBlocks {

    public static final Block MYTHRIL_BLOCK = registerBlock("mythril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ModItemGroup.MYTHRIL, "tooltip.primermod.mythril_block");

    public static final Block MYTHRIL_ORE = registerBlock("mythril_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4.5f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block NETHERRACK_MYTHIL_ORE = registerBlock("netherrack_mythril_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4.5f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block RAW_MYTHRIL_BLOCK = registerBlock("raw_mythril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block SPEEDY_BLOCK = registerBlock("speedy_block",
            new SpeedyBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ItemGroup.BUILDING_BLOCKS);

    //ATENCION, una vez creado los bloques no bloques, es necesario crear los archivos json en blockstates, models/block y apartir de ahi se les da una textura, ya sea nueva
    //o usandose una que ya exista, ya que solo se utiliza la textura, pero la forma del bloque es el que se le da en este archivo
    //En los archivos blockstates de los bloques no bloques, se pueden copiar y pegar de los archivos de los bloques originales en los assets de minecraft
    //y modificar al gusto

    //se crea el mythril button, el boton customizado con mythril, que se basa en el esquema o estructura del boton original de piedra de minecraft
    //para esto se pone que el bloque sea un bloque de tipo ModStoneButtonBlock, se pone que no tiene colision porque es un boton
    public static final Block MYTHRIL_BUTTON = registerBlock("mythril_button",
            new ModStoneButtonBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(6f).noCollision()),ModItemGroup.MYTHRIL);

    //Se crea el mythril pressure plate, que se basa del pressure plate de minecraft, se pone que el bloque sea de tipo ModPressurePlateBlock
    //Se le agrega el ActivationRule.EVERYTHING que es necesario, para que la plataforma de presion se active o se presione con cualquier objeto o entidad.
    public static final Block MYTHRIL_PRESSURE_PLATE = registerBlock("mythril_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.of(Material.METAL)
                    .strength(6f).requiresTool()),ModItemGroup.MYTHRIL);

    //Se crean los fences o cercas, de mythril, que se construyen a partir de las cercas originales de madera de minecraft
    public static final Block MYTHRIL_FENCE = registerBlock("mythril_fence", new FenceBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ModItemGroup.MYTHRIL);

    //Se crean los mismos bloques a partir de sus bloques originales de minecraft
    public static final Block MYTHRIL_FENCE_GATE = registerBlock("mythril_fence_gate", new FenceGateBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block MYTHRIL_WALL = registerBlock("mythril_wall", new WallBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ModItemGroup.MYTHRIL);







    //Se copian las mismas funciones de registrar bloques y item bloques, pero esta vez se les agrega un parametro nuevo
    //llamado toolTipKey, con esta key, se les asignará un mensaje de tip en el inventario
    private static Block registerBlock(String name, Block block, ItemGroup group, String toolTipKey){
        registerBlockItem(name, block, group, toolTipKey);
        return Registry.register(Registry.BLOCK, new Identifier(PrimerMod.MOD_ID, name), block);
    }

    //con esta función se registran los items de los bloques nuevos, para poder verse en el inventario
    private static Item registerBlockItem(String name, Block block, ItemGroup group, String toolTipKey){
        return Registry.register(Registry.ITEM, new Identifier(PrimerMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)){
                    @Override
                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                        tooltip.add(new TranslatableText(toolTipKey));
                    }

                });

    }


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
