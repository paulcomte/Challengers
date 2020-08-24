package fr.rqndomhax.challengers.managers.game.activitymanagers;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.game.GameState;
import org.bukkit.command.CommandSender;

public class FifthACM {

    private final Setup setup;
    private final CommandSender sender;

    public FifthACM(Setup setup, CommandSender sender) {
        this.setup = setup;
        this.sender = sender;
    }

    // TODO FifthAC next system

    public void onCommand() {

        switch(setup.getGm().getGame().getGameState().getCurrentState()) {

            case 0:
                setup.getGm().getGame().setGameState(GameState.FIFTHAC.withGameState(GameState.FIFTHAC, 1));
                break;
            case 1:
                setup.getGm().getGame().setGameState(GameState.FIFTHAC.withGameState(GameState.FIFTHAC, 2));
                break;
            case 2:
                setup.getGm().getGame().setGameState(GameState.FIFTHAC.withGameState(GameState.FIFTHAC, 3));
                break;
            default:
                sender.sendMessage("§4Erreur interne, veuillez contacter le développeur §e_Paul#6918 §4si cette erreur survient de nouveau");
                break;

        }


    }

}
