package net.nexus.primermod.world.feature;

import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModOreFeatures {
    //Se agregan los modifiers ya que estos estan privados por minecraft, entonces se ponen publicos para usarse en ModPlacedFeatures
    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
