package net.nexus.primermod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.nexus.primermod.world.feature.ModPlacedFeatures;

public class ModFlowerGeneration {

    //Se crea el generador de flores, parecido al de arboles
    public static void generateFlower() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.LILAC_PLACED.getKey().get());

    }
}
