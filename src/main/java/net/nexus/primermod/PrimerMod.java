package net.nexus.primermod;

import net.fabricmc.api.ModInitializer;
import net.nexus.primermod.block.ModBlocks;
import net.nexus.primermod.block.entity.ModBlockEntities;
import net.nexus.primermod.effect.ModEffect;
import net.nexus.primermod.item.ModItems;
import net.nexus.primermod.painting.ModPaintings;
import net.nexus.primermod.particle.ModParticles;
import net.nexus.primermod.potion.ModPotions;
import net.nexus.primermod.recipe.ModRecipes;
import net.nexus.primermod.screen.ModScreenHandlers;
import net.nexus.primermod.util.ModLootTableModifiers;
import net.nexus.primermod.util.ModRegistries;
import net.nexus.primermod.world.feature.ModConfiguredFeatures;
import net.nexus.primermod.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimerMod implements ModInitializer {

	//se le da id al mod
	public static final String MOD_ID = "primermod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//en esta función se registran las clases del mod, como items, bloques, o cosas diferentes
	@Override
	public void onInitialize() {
		//Se agregan los features de los arboles de primero
		ModConfiguredFeatures.registerConfiguredFeatures();

		ModItems.registerModItem();
        ModBlocks.registerModBlock();
		ModRegistries.registerModStuffs();
		ModPaintings.registerPaintings();

		//Se agrega la generación de los arboles
		ModWorldGen.generateModWorldGen();

		//Se agregan las modificaciones a los drops de las clases vanilla de minecraft
		ModLootTableModifiers.modifyLootTables();

		//Se registran los efectos personalizados
		ModEffect.registerEffects();
		//Se registran las pociones, primero van los effects despues las potions
		ModPotions.RegisterPotions();

		ModBlockEntities.registerAllBlockEntities();
		ModRecipes.registerRecipes();
		ModScreenHandlers.registerAllScreenHandlers();

		ModParticles.registerParticles();
	}
}
