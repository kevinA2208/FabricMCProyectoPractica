package net.nexus.primermod.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.item.ModItems;

public class ModRegistries {

    public static void registerModStuffs(){
        registerFuels();
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
}
