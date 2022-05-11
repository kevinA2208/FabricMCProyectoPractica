package net.nexus.primermod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.block.custom.GrapeVineBlock;
import net.nexus.primermod.block.custom.ModSaplingBlock;
import net.nexus.primermod.block.custom.MythrilLampBlock;
import net.nexus.primermod.block.custom.SpeedyBlock;
import net.nexus.primermod.item.ModItemGroup;
import net.nexus.primermod.sound.ModSounds;
import net.nexus.primermod.world.feature.tree.JacarandaSaplingGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBlocks {

    //BLOQUES COMUNES
    public static final Block MYTHRIL_BLOCK = registerBlock("mythril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()), ModItemGroup.MYTHRIL, "tooltip.primermod.mythril_block");

    //El mythril ore se crea a base del Ore Block para que de exp al minar el ore, en este caso con el UniformIntProvider.create nos da la experiencia
    //entre los dos parametros que se les da, nos puede dar entre 2 a 6 de exp
    public static final Block MYTHRIL_ORE = registerBlock("mythril_ore",
            new OreBlock(FabricBlockSettings.of(Material.METAL).strength(4.5f).requiresTool(), UniformIntProvider.create(2, 6)),ModItemGroup.MYTHRIL);

    public static final Block NETHERRACK_MYTHIL_ORE = registerBlock("netherrack_mythril_ore",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block RAW_MYTHRIL_BLOCK = registerBlock("raw_mythril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block DEEPSLATE_MYTHRIL_ORE = registerBlock("deepslate_mythril_ore",
            new OreBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.MYTHRIL);

    public static final Block BLOOD_ORE = registerBlock("blood_ore",
            new OreBlock(FabricBlockSettings.of(Material.METAL).strength(5.0f).requiresTool(), UniformIntProvider.create(3, 7)),ModItemGroup.BLOOD);

    public static final Block DEEPSLATE_BLOOD_ORE = registerBlock("deepslate_blood_ore",
            new OreBlock(FabricBlockSettings.of(Material.METAL).strength(5.0f).requiresTool(), UniformIntProvider.create(3, 7)), ModItemGroup.BLOOD);



    //Se añaden los bloques troncos de arboles jacaranda, se separan en los troncos, en la madera, en los troncos stripped, en la madera stripped y en los tablones
    //la madera y troncos se crean con el pillarblock porque tienen textura a los lados y una distinta arriba y los tablones si son bloques normales
    public static final Block JACARANDA_LOG = registerBlock("jacaranda_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0f)), ItemGroup.BUILDING_BLOCKS);

    public static final Block JACARANDA_WOOD = registerBlock("jacaranda_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(4.0f)), ItemGroup.BUILDING_BLOCKS);

    public static final Block STRIPPED_JACARANDA_LOG = registerBlock("stripped_jacaranda_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0f)), ItemGroup.BUILDING_BLOCKS);

    public static final Block STRIPPED_JACARANDA_WOOD = registerBlock("stripped_jacaranda_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0f)), ItemGroup.BUILDING_BLOCKS);

    public static final Block JACARANDA_PLANKS = registerBlock("jacaranda_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f)), ItemGroup.BUILDING_BLOCKS);

    public static final Block JACARANDA_LEAVES = registerBlock("jacaranda_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()), ItemGroup.BUILDING_BLOCKS);

    public static final Block JACARANDA_SAPLING = registerBlock("jacaranda_sapling",
            new ModSaplingBlock(new JacarandaSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.BUILDING_BLOCKS);


    //BLOQUES ESPECIALES

    public static final Block SPEEDY_BLOCK = registerBlock("speedy_block",
            new SpeedyBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()),ItemGroup.BUILDING_BLOCKS);

    //BLOQUES "FURNITURE"

    //ATENCION, una vez creado los bloques "furniture" , es necesario crear los archivos json en blockstates, models/block y apartir de ahi se les da una textura, ya sea nueva
    //o usandose una que ya exista, ya que solo se utiliza la textura, pero la forma del bloque es el que se le da en este archivo
    //En los archivos blockstates de los bloques "furniture", se pueden copiar y pegar de los archivos de los bloques originales en los assets de minecraft
    //y modificar al gusto

    //se crea el mythril button, el boton customizado con mythril, que se basa en el esquema o estructura del boton original de piedra de minecraft
    //para esto se pone que el bloque sea un bloque de tipo StoneButtonBlock, se pone que no tiene colision porque es un boton

    public static final Block MYTHRIL_BUTTON = registerBlock("mythril_button",
            new StoneButtonBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(1f).noCollision()),ModItemGroup.MYTHRIL);

    //Se crea el mythril pressure plate, que se basa del pressure plate de minecraft, se pone que el bloque sea de tipo PressurePlateBlock
    //Se le agrega el ActivationRule.EVERYTHING que es necesario, para que la plataforma de presion se active o se presione con cualquier objeto o entidad.
    public static final Block MYTHRIL_PRESSURE_PLATE = registerBlock("mythril_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.of(Material.METAL)
                    .strength(1f)),ModItemGroup.MYTHRIL);

    //Se crean los fences o cercas, de mythril, que se construyen a partir de las cercas originales de madera de minecraft
    public static final Block MYTHRIL_FENCE = registerBlock("mythril_fence", new FenceBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()),ModItemGroup.MYTHRIL);

    //Se crean los mismos bloques a partir de sus bloques originales de minecraft
    public static final Block MYTHRIL_FENCE_GATE = registerBlock("mythril_fence_gate", new FenceGateBlock(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()),ModItemGroup.MYTHRIL);

    public static final Block MYTHRIL_WALL = registerBlock("mythril_wall", new WallBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()),ModItemGroup.MYTHRIL);


    public static final Block MYTHRIL_SLAB = registerBlock("mythril_slab", new SlabBlock(FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()),ModItemGroup.MYTHRIL);


    public static final Block MYTHRIL_STAIRS = registerBlock("mythril_stairs", new StairsBlock(ModBlocks.MYTHRIL_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.METAL).strength(4f).requiresTool()),ModItemGroup.MYTHRIL);

    //En la puerta como se supone que va a ser de madera, es necesario ponerle el Material.WOOD, para que sea de madera, si se pone Material.METAL será necesario usar redstone para abrirla
    //como una puerta de metal
    public static final Block KAUPEN_DOOR = registerBlock("kaupen_door", new DoorBlock(
            FabricBlockSettings.of(Material.WOOD).strength(1.8f).nonOpaque()),ModItemGroup.MYTHRIL);

    public static final Block KAUPEN_TRAPDOOR = registerBlock("kaupen_trapdoor", new TrapdoorBlock(
            FabricBlockSettings.of(Material.WOOD).strength(1.8f).nonOpaque()),ModItemGroup.MYTHRIL);


    //FLORES Y POTS PARA FLORES
    //Las flores se crean con el tipo de bloque Flower de Minecraft, este bloque necesita dos parametros
    //el status effect que se le añade, y la duración de este efecto que es en segundos en esta ocasión
    //el efecto que se le añade es cuando se craftea un suspicious stew que es un estofado de flores que da
    //un efecto dependiendo de la flor, despues se le añade los mismos settings de bloque que la flor dandelion de minecraft
    //y el nonOpaque se queda porque no es un bloque que nos choque
    public static final Block LILAC_FLOWER = registerBlock("lilac_flower",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 12,
                    FabricBlockSettings.copy(Blocks.DANDELION).strength(0f).nonOpaque()),ItemGroup.MISC);

    //en si este no es un bloque real, es solo para que la flor que creamos se pueda poner en los pots de minecraft que ya están
    //Los pot para flores se crean con el tipo de bloque FlowerPotBlock, pero esta vez se llama una nueva funcion para registrar
    //se llama registerBlockWithoutBlockItem, se llama con esta función ya que basicamente el pot es un bloque sin item, pero se le pone la flor cuando se le da click derecho
    //tambien se pone que copie la estructura de Blocks.POTTED_ALLIUM de minecraft
    public static final Block POTTED_LILAC_FLOWER = registerBlockWithoutBlockItem("potted_lilac_flower",
            new FlowerPotBlock(ModBlocks.LILAC_FLOWER, FabricBlockSettings.copy(Blocks.POTTED_ALLIUM).nonOpaque()),ItemGroup.MISC);

    //CRISTALES BLOQUES TRANSPARENTES Y LAMPARAS

    //Se crea la lampara de tipo mythrillampblock, se le añade luminance porque es necesario que ilumine
    //y se le añade el estado inicial, y se obtiene el estado CLICKED, y si el estado es True,
    //se le añade el nivel de luz 15 que es el maximo, si no lo es se le añade 0
    //tambien se le añade el grupo de sonidos que se le agregan en ModSounds
    public static final Block MYTHRIL_LAMP = registerBlock("mythril_lamp",
            new MythrilLampBlock(FabricBlockSettings.of(Material.METAL)
                    .strength(2.0f).requiresTool().luminance((state)
                            -> state.get(MythrilLampBlock.CLICKED) ? 15 : 0).sounds(ModSounds.MYTHRIL_SOUNDS)),ModItemGroup.MYTHRIL);


    public static final Block WINTER_WINDOW = registerBlock("winter_window",
            new GlassBlock(FabricBlockSettings.copy(Blocks.GLASS).strength(1.0f).nonOpaque()), ItemGroup.MISC);

    //CULTIVOS
    //Se crea el cultivo de uvas o grape y tiene que registrarse con la funcion registerBlockWithoutBlockItem, ya que no es un bloque en si, sino que se le
    //agregan semillas encima
    public static final Block GRAPE_VINE = registerBlockWithoutBlockItem("grape_vine",
            new GrapeVineBlock(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque()), ItemGroup.MISC);


    //CONSTRUCTORES



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


    //La funcion de registerBlockWithoutBlockItem es la misma que registerBlock, solo que se le remueve el registerBlockItem que contiene
    //ya que esta no tiene item, solo la flor que se le pone despues
    private static Block registerBlockWithoutBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.BLOCK, new Identifier(PrimerMod.MOD_ID, name), block);
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
