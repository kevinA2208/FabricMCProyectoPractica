package net.nexus.primermod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {

    //ARBOLES PLACED

    //Con esta función se registran ya los ConfiguredFeatures del spawn de los arboles llamado JACARANDA_SPAWN, donde se le añade el
    //VegetationPlacedFeatures.modifiers, y esto agrega un modificador al como van a spawnear los arboles
    //el primer parametro es 1 arbol por tamaño del feature, el 2 es el porcentaje de probabilidad de tener arboles extras en el tamaño del feature
    //y el tercero son los arboles extras si la probabilidad se cumple
    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED = PlacedFeatures.register("jacaranda_placed",
            ModConfiguredFeatures.JACARANDA_SPAWN, VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2)));

    //FLORES PLACED

    //Se crea el placedFeature con el configuredFeatured de lilac flower,
    //y se añaden modificaciones del como deberia spawnearse la flor en el mundo
    //se recomienda probar con diferentes numeros en las modificaciones
    //para saber que hace cada una y ver para que son detalladamente
    public static final RegistryEntry<PlacedFeature> LILAC_PLACED = PlacedFeatures.register("lilac_placed",
            ModConfiguredFeatures.LILAC_FLOWER, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());


    //ORES PLACED

    //Se crea el placedFeature del mythril ore, donde se le añade el configuredFeature y el modifiersWithCount de la clase
    //ModOreFeatures, el count 7, es la cantidad de grupos de menas de mythril ore que hay por chunk
    //el heightRangePlacementModifier.trapezoid modifica la distribución de estas menas en los grupos de ores
    //lo que hace es formar una especie de trapezoid con las menas, donde una apuna al rango -80 y la otra punta al rango 80
    //haciendo que la mayor cantidad de menas de ore esten en el medio del trapecio
    //hay diferentes formas de distribuir estas menas de ore, ademas del trapezoid
    public static final RegistryEntry<PlacedFeature> MYTHRIL_ORE_PLACED = PlacedFeatures.register("mythril_ore_placed",
            ModConfiguredFeatures.MYTHRIL_ORE, ModOreFeatures.modifiersWithCount(40,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));

    //Se agrega el placed Feature de los blood ores, con una altura uniform entre la altura 20
    public static final RegistryEntry<PlacedFeature> BLOOD_ORE_PLACED = PlacedFeatures.register("blood_ore_placed",
            ModConfiguredFeatures.BLOOD_ORE, ModOreFeatures.modifiersWithCount(10,
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(20)))); //Altura del ore



}
