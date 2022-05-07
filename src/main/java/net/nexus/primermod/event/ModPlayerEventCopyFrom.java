package net.nexus.primermod.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.nexus.primermod.util.IEntityDataSaver;

public class ModPlayerEventCopyFrom implements ServerPlayerEvents.CopyFrom {

    //con esta función o metodo se copia el homepos si es que tenia uno
    //del jugador original al jugador nuevo
    //esto pasa cuando por ejemplo un jugador muere, el pierde la información al reaparecer
    //aqui se le pasa ese homepos al nuevo jugador que reaparece
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        IEntityDataSaver original = ((IEntityDataSaver) oldPlayer);
        IEntityDataSaver player = ((IEntityDataSaver) newPlayer);

        player.getPersistentData().putIntArray("homepos", original.getPersistentData().getIntArray("homepos"));
    }
}
