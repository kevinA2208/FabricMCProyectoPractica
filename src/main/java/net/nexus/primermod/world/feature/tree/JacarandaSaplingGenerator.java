package net.nexus.primermod.world.feature.tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.nexus.primermod.world.feature.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

//Se crea la función jacaranda sapling generator donde se le retorna el feature del arbol que va a generar el sapling
public class JacarandaSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.JACARANDA_TREE;
    }
}
