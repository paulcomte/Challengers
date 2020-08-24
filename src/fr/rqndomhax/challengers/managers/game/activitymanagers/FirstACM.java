/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers.game.activitymanagers;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
import fr.rqndomhax.challengers.managers.tasks.FirstT;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;

public class FirstACM {


    private final Setup setup;
    private final CommandSender sender;

    public FirstACM(Setup setup, CommandSender sender) {
        this.setup = setup;
        this.sender = sender;
    }

    // TODO First activity command

    public void onCommand() {

        switch (setup.getGm().getGame().getGameState().getCurrentState()) {

            case 0:
                setup.getGm().getGame().setGameState(GameState.FIRSTAC.withGameState(GameState.FIRSTAC, 1));
                sender.sendMessage("Vous venez de démarrer la séléction des vips");
                break;
            case 1:

                if(!setup.getVip().isVIPCooldownFinished()) {
                    sender.sendMessage("Impossible de passer à la séléction des gardes du corps, une équipe au moins n'a pas séléctionné de vip");
                    break;
                }

                setup.getGm().getGame().setGameState(GameState.FIRSTAC.withGameState(GameState.FIRSTAC, 2));
                sender.sendMessage("Vous venez de démarrer la séléction des gardes du corps");
                break;

            case 2:
                if(!setup.getbG().isBodyGuardCooldownFinished()) {
                    sender.sendMessage("Impossible de passer au début de l'activité une équipe au moins n'a pas choisi de garde du corps");
                    break;
                }

                setup.getGm().getGame().setGameState(GameState.FIRSTAC.withGameState(GameState.FIRSTAC, 3));

                try {
                    setup.getTaskM().start(FirstT.class);
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                sender.sendMessage("Vous venez de démarrer l'activité 1");
                break;

            case 3:
                sender.sendMessage("L'activité est soit en cours soit déjà terminé, pour en lancer une autre veuillez effectuer la commande /ac start");
                break;

            default:
                sender.sendMessage("§4Erreur interne, veuillez contacter le développeur §e_Paul#6918 §4si cette erreur survient de nouveau");
                break;

        }


    }

}
