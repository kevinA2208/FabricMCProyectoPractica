package net.nexus.primermod.util;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.impl.object.builder.TradeOfferInternals;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.block.ModBlocks;
import net.nexus.primermod.command.ReturnHomeCommand;
import net.nexus.primermod.command.SetHomeCommand;
import net.nexus.primermod.event.ModPlayerEventCopyFrom;
import net.nexus.primermod.item.ModItems;

public class ModRegistries {

    public static void registerModStuffs() {
        registerFuels();
        registerCommands();
        registerEvents();
        registerFlammableBlock();
        registerStrippables();
        registerCustomTrades();
    }

    //con esta funcion se registran combustibles nuevos al juego, para cocinar o fundir
    private static void registerFuels() {
        //Se manda un mensaje cuando carguen los fuels en minecraft
        PrimerMod.LOGGER.info("Se registran los fuels para " + PrimerMod.MOD_ID);


        FuelRegistry registry = FuelRegistry.INSTANCE;
        //se añade el valor del combustible que es lilac flower bulb, se le añade 200 en ticks
        //100 ticks equivale a 5 segundos, por lo tanto el lilac flower bulb se desgastará o apagará en 40 segundos
        //value es el tiempo en que se apaga el material en el horno
        registry.add(ModItems.LILAC_FLOWER_BULB, 800);
    }

    //se registran los comandos creados, hay que ver que las funciones register
    //tengan CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated
    //en los parametros
    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
    }

    private static void registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(new ModPlayerEventCopyFrom());
    }

    //en registerFlammableBlock se registran los bloques que pueden ser flamables y pueden expandir el fuego
    private static void registerFlammableBlock() {
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(ModBlocks.JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.JACARANDA_PLANKS, 5, 20);

        instance.add(ModBlocks.JACARANDA_LEAVES, 30, 60);
    }

    //aqui se registran los bloques que pueden ser strippables en este caso el tronco y la madera, y se ponen primero con los que se hacen el stripped y despues
    //los bloques ya stripped
    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_LOG, ModBlocks.STRIPPED_JACARANDA_LOG);
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_WOOD, ModBlocks.STRIPPED_JACARANDA_WOOD);
    }

    //Con esta clase se registran trades o intercambios con aldeanos customizados
    private static void registerCustomTrades(){
        //boilerplate se refiere a sección de codigo que no tiene mucha o nada de alteración o modificación

        //De esta forma se registra un trae personalizado, el villagerprofession es la profesion del aldeano
        //con el que se puede intercambiar, y el level es el nivel de este aldeano
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    //con new tradeOffer se crea la nueva oferta de intercambio
                    factories.add((entity, random) -> new TradeOffer(
                            //El item a dar a cambio es EMERALD, y el count es la cantidad que se tienen que dar, en este caso son 2
                            new ItemStack(Items.EMERALD, 2),
                            //el segundo item es el que recibes a cambio, en este caso son 12 grapes o uvas
                            new ItemStack(ModItems.GRAPE, 12),
                            6,2,0.02f));// el primer parametro son las veces maximas que puedes usar el trade
                    // el segundo parametro es la experiencia que recibe el aldeano, y el tercero es un multiplicador del precio
                });

        //En este caso se registra un nuevo intercambio con otro aldeano con diferente profesión, y que sea nivel 3
        TradeOfferInternals.registerVillagerOffers(VillagerProfession.TOOLSMITH, 3,
                (factories) -> {
                    factories.add((entity, random) -> new TradeOffer(
                            //Se dan 6 esmeraldas por 1 mythril pickaxe
                            new ItemStack(Items.EMERALD, 6),
                            new ItemStack(ModItems.MYTHRIL_PICKAXE, 1),
                            12,7,0.08f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 7),
                            new ItemStack(ModItems.BLOOD_SWORD, 1),
                            12,7,0.08f));
                            //se puede usar 12 veces maximo, el aldeano recibe 7 de experiencia y el multiplicador de precio es 0.08

                });

    }

}
