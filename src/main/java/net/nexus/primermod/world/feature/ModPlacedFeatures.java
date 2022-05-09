package net.nexus.primermod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModPlacedFeatures {

    //Con esta función se registran ya los ConfiguredFeatures del spawn de los arboles llamado JACARANDA_SPAWN, donde se le añade el
    //VegetationPlacedFeatures.modifiers, y esto agrega un modificador al como van a spawnear los arboles
    //el primer parametro es 1 arbol por tamaño del feature, el 2 es el porcentaje de probabilidad de tener arboles extras en el tamaño del feature
    //y el tercero son los arboles extras si la probabilidad se cumple
    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED = PlacedFeatures.register("jacaranda_placed",
            ModConfiguredFeatures.JACARANDA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));
}
