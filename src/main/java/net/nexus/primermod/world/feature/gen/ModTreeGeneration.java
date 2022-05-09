package net.nexus.primermod.world.feature.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.nexus.primermod.world.feature.ModPlacedFeatures;

public class ModTreeGeneration {
    //en la clase ModTreeGeneration se generan los arboles en el mundo y con la funcion generateTrees se escogen los biomas en los que va a spawnear
    //en este caso se van a spawnear en los biomas que tengan categoria PLAINS y se le da el arbol que va a spanear, en este caso el JACARANDA_PLACED
    //y se le agrega generationStep.Feature.VEGETAL_DECORATION ya que son arboles los que van a spawnear, se pueden spawnear lagos y otras cosas
    public static void generateTrees() {
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.JACARANDA_PLACED.getKey().get());

    }
}
