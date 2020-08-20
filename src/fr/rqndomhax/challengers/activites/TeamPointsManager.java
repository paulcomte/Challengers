package fr.rqndomhax.challengers.activites;

import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.team.TeamList;
import org.bukkit.command.CommandSender;

public class TeamPointsManager {

    private final Setup setup;
    private final CommandSender sender;
    private final String[] args;

    public TeamPointsManager(Setup setup, CommandSender sender, String[] args) {
        this.setup = setup;
        this.sender = sender;
        this.args = args;
    }

    public boolean onSetPoints() {

        if(args.length != 3) {
            ActivityCommands.showHelp(sender, 4);
            return false;
        }

        TeamList selectedTeam = null;

        for (TeamList teams : TeamList.values()) {

            if(!args[1].equalsIgnoreCase(teams.getName())) continue;

            selectedTeam = teams;
        }

        if(selectedTeam == null) {
            sender.sendMessage("WARNING: VEUILLEZ SPECIFIER UNE EQUIPE CORRECT");
            sender.sendMessage("WARNING: LISTE DES EQUIPES: &bBLEUE &f- &cROUGE &f- &2VERTE &f- &eJAUNE");
        }

        if(0 < Integer.parseInt(args[2]) || Integer.parseInt(args[2]) > 2147483645) {
            sender.sendMessage("WARNING: VEUILLEZ SPECIFIER UN NOMBRE CORRECT");
            sender.sendMessage("WARNING: LE NOMBRE DOIT ETRE COMPRIS ENTRE 0 & 2 147 483 644");
            return false;
        }

        return false;
    }

}
