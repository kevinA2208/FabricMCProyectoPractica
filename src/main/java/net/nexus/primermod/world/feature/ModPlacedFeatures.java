package net.nexus.primermod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {

    //Con esta función se registran ya los ConfiguredFeatures del spawn de los arboles llamado JACARANDA_SPAWN, donde se le añade el
    //VegetationPlacedFeatures.modifiers, y esto agrega un modificador al como van a spawnear los arboles
    //el primer parametro es 1 arbol por tamaño del feature, el 2 es el porcentaje de probabilidad de tener arboles extras en el tamaño del feature
    //y el tercero son los arboles extras si la probabilidad se cumple
    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED = PlacedFeatures.register("jacaranda_placed",
            ModConfiguredFeatures.JACARANDA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));

    //Se crea el placedFeature con el configuredFeatured de lilac flower,
    //y se añaden modificaciones del como deberia spawnearse la flor en el mundo
    //se recomienda probar con diferentes numeros en las modificaciones
    //para saber que hace cada una y ver para que son detalladamente
    public static final RegistryEntry<PlacedFeature> LILAC_PLACED = PlacedFeatures.register("lilac_placed",
            ModConfiguredFeatures.LILAC_FLOWER, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());



}
