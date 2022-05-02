package net.nexus.primermod;

import net.fabricmc.api.ModInitializer;
import net.nexus.primermod.block.ModBlocks;
import net.nexus.primermod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimerMod implements ModInitializer {
	public static final String MOD_ID = "primermod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItem();
        ModBlocks.registerModBlock();
	}
}
