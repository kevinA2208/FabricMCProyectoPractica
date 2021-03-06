package net.nexus.primermod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;
import net.nexus.primermod.block.ModBlocks;

public class ModBlockEntities {

    public static BlockEntityType<MythrilBlasterBlockEntity> MYTHRIL_BLASTER;

    public static void registerAllBlockEntities(){
        MYTHRIL_BLASTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(PrimerMod.MOD_ID, "mythril_blaster"), FabricBlockEntityTypeBuilder.create(MythrilBlasterBlockEntity::new,
                        ModBlocks.MYTHRIL_BLASTER).build(null));
    }
}
