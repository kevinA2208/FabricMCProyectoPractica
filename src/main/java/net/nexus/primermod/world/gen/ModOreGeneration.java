package net.nexus.primermod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.nexus.primermod.world.feature.ModPlacedFeatures;

public class ModOreGeneration {

    //Se crea el metodo generateOres, donde simplemente se le agrega el Feature.UNDERGROUND_ORES para que se generen
    //junto a los demas ores
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.MYTHRIL_ORE_PLACED.getKey().get());

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.BLOOD_ORE_PLACED.getKey().get());

    }
}
