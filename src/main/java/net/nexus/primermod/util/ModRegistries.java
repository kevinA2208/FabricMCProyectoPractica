package net.nexus.primermod.util;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.command.ReturnHomeCommand;
import net.nexus.primermod.command.SetHomeCommand;
import net.nexus.primermod.event.ModPlayerEventCopyFrom;
import net.nexus.primermod.item.ModItems;

public class ModRegistries {

    public static void registerModStuffs(){
        registerFuels();
        registerCommands();
        registerEvents();
    }

    //con esta funcion se registran combustibles nuevos al juego, para cocinar o fundir
    private static void registerFuels(){
        //Se manda un mensaje cuando carguen los fuels en minecraft
        PrimerMod.LOGGER.info("Se registran los fuels para "+PrimerMod.MOD_ID);


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
}
