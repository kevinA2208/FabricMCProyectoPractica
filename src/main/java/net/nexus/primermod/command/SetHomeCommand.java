package net.nexus.primermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.nexus.primermod.util.IEntityDataSaver;

public class SetHomeCommand {
    //Aqui se registra el comando para guardar el home con //home set
    //y despues de eso ejecuta la función run
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("set").executes(SetHomeCommand::run)));
    }

    //en esta función se guarda el home, primero obtiene el jugador de la interface que se creó con el mixin
    //y se obtiene la posicion del jugador, esa posición exacta se guarda en un string pos
    //para despues guardarse cada posición, x y z, en un array con key homepos
    //y se manda un mensaje diciendo que se posicionó un home en esa posición
    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer();
        BlockPos playerPos = context.getSource().getPlayer().getBlockPos();
        String pos = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";

        player.getPersistentData().putIntArray("homepos",
                new int[] {playerPos.getX(), playerPos.getY(), playerPos.getZ() });

        context.getSource().sendFeedback(new LiteralText("Set home at " + pos), true);
        return 1;
    }
}