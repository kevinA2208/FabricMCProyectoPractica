package net.nexus.primermod.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.nexus.primermod.block.ModBlocks;

public class ModSaplingBlock extends SaplingBlock {
    /**
     * Access widened by fabric-transitive-access-wideners-v1 to accessible
     *
     * @param generator
     * @param settings
     */
    public ModSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

}
