package net.nexus.primermod.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nexus.primermod.PrimerMod;

public class ModParticles {


    public static final DefaultParticleType CITRINE_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles(){
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(PrimerMod.MOD_ID, "citrine_particle"),
                CITRINE_PARTICLE);
    }
}
