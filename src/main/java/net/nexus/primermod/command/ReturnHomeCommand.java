package net.nexus.primermod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.nexus.primermod.util.IEntityDataSaver;

public class ReturnHomeCommand {

    //Aqui se registra el comando para regresar a el home con /home return
    //primero se registra el home, despues el return, y despues de eso, que ejecute la funcion
    //run
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("return").executes(ReturnHomeCommand::run)));
    }


    //en esta funcion se regresa al home que se posicionó con el sethome
    //primero se crea el jugador con la interface que creamos
    //despues de eso podemos acceder a la persistentData
    //y con está obtenemos el array homepos con la posicion x y z del jugador
    // que ha posicionado antes con el setHome command
    //si la homepos es distinto de 0, quiere decir que si tiene un home posicionado
    //y teleporta al jugador a ese lugar
    //si el homepos es 0 y no tiene nada, quiere decir que no hay un homeset
    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        IEntityDataSaver player = (IEntityDataSaver)context.getSource().getPlayer();

        // not 0 means it contains SOMETHING
        int[] homepos = player.getPersistentData().getIntArray("homepos");

        if(homepos.length != 0) {
            int[] playerPos = player.getPersistentData().getIntArray("homepos");
            context.getSource().getPlayer().requestTeleport(playerPos[0], playerPos[1], playerPos[2]);

            context.getSource().sendFeedback(new LiteralText("Player returned Home!"), true);
            return 1;
        }
        else {
            context.getSource().sendFeedback(new LiteralText("No Home Position has been Set!"), true);
            return -1;
        }
    }
}
