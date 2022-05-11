package net.nexus.primermod.util;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.nexus.primermod.item.ModItems;

public class ModLootTableModifiers {
    //Esta clase sirve para añadir loot que dropean otras clases de los archivos vanilla de minecraft
    //como por ejemplo añadir un nuevo item que dropee al asesinar un creeper
    //o un nuevo item en un cofre de una aldea o un iglu

    //Primero se llaman los id de los loottables que queremos modificar de minecraft
    private static final Identifier GRASS_BLOCK_ID
            = new Identifier("minecraft", "blocks/grass");
    private static final Identifier IGLOO_STRUCTURE_CHEST_ID
            = new Identifier("minecraft", "chests/igloo_chest");
    private static final Identifier CREEPER_ID
            = new Identifier("minecraft", "entities/creeper");

    private static final Identifier ZOMBIE_ID
            = new Identifier("minecraft","entities/zombie");

    //En la función modifyLootTables se registran las modificaciones a los loottables vanilla
    public static void modifyLootTables() {
        LootTableLoadingCallback.EVENT.register(((resourceManager, manager, id, supplier, setter) -> {
            //Se revisa el loot table del bloque grass de minecraft
            if(GRASS_BLOCK_ID.equals(id)) {
                // Se añaden el drop de semillas de grape o uvas al romper el grass block
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        //Se le da un chance de que se dropee estas semillas en un 35%
                        .conditionally(RandomChanceLootCondition.builder(0.35f))
                        //se le añade el item que va a dropear
                        .with(ItemEntry.builder(ModItems.GRAPE_SEEDS))
                        //Se le añade la cantidad de semillas que va a dropear, en este caso será entre 1 y 2
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());
                supplier.withPool(poolBuilder.build());
            }

            if(IGLOO_STRUCTURE_CHEST_ID.equals(id)) {
                // Añadir el Dowsing Rod en los cofres de los iglus de minecraft
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        //Se pone el porcentaje de probabilidad de que salga el dowsing rod en el cofre del iglu
                        //en este caso es 100%
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        //se añade el item
                        .with(ItemEntry.builder(ModItems.DOWSING_ROD))
                        //se le añade la cantidad del item que va haber, en este caso el minimo será 1 y el max 1, osea solo habrá un item en el cofre
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                supplier.withPool(poolBuilder.build());
            }

            if(CREEPER_ID.equals(id)) {
                // Añadir lilac flower bulb a los drops de los creepers
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // Se dropean en un 100%
                        .with(ItemEntry.builder(ModItems.LILAC_FLOWER_BULB))
                        //Se dropea un lilac flower bulb
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                supplier.withPool(poolBuilder.build());
            }

            //Drop de raw blood al matar un zombie (1 raw blood)
            if(ZOMBIE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) // Se dropean en un 100%
                        .with(ItemEntry.builder(ModItems.RAW_BLOOD))
                        .withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                supplier.withPool(poolBuilder.build());
            }
        }));
    }
}
