package fr.rqndomhax.challengers.managers.game.activitymanagers;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
import org.bukkit.command.CommandSender;

public class FourthACM {

    private final Setup setup;
    private final CommandSender sender;

    public FourthACM(Setup setup, CommandSender sender) {
        this.setup = setup;
        this.sender = sender;
    }

    // TODO FourthAC next system

    public void onCommand() {

        switch(setup.getGm().getGame().getGameState().getCurrentState()) {

            case 0:
                setup.getGm().getGame().setGameState(GameState.FOURTHAC.withGameState(GameState.FOURTHAC, 1));
                break;
            case 1:
                setup.getGm().getGame().setGameState(GameState.FOURTHAC.withGameState(GameState.FOURTHAC, 2));
                break;
            case 2:
                setup.getGm().getGame().setGameState(GameState.FOURTHAC.withGameState(GameState.FOURTHAC, 3));
                break;
            default:
                sender.sendMessage("§4Erreur interne, veuillez contacter le développeur §e_Paul#6918 §4si cette erreur survient de nouveau");
                break;

        }

    }
}
