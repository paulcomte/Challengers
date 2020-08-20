package fr.rqndomhax.challengers.activites;

import fr.rqndomhax.challengers.core.Setup;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ActivityCommands implements CommandExecutor {

    private final Setup setup;

    public ActivityCommands(Setup setup) {
        this.setup = setup;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(!sender.hasPermission(setup.getCore().getConfig().getString("Permissions.ActivityManager"))) {
            sender.sendMessage(setup.getCore().getConfig().getString("Messages.NotAllowed"));
            return false;
        }

        if(args.length < 1) {
            showHelp(sender, 1);
            return false;
        }

        if(args[0].equalsIgnoreCase("help")) {

            if(args.length == 1) {
                showHelp(sender, 1);
                return false;
            }

            showHelp(sender, Integer.parseInt(args[1]));

            return false;
        }

        switch(args[0].toLowerCase()) {

            case "start":

                return new Start(setup, sender, args).onCommand();

            case "pause":

                return new Pause(setup, sender).onCommand();

            case "stop":

                return new Stop(setup, sender).onCommand();

            case "setspawn":

                break;

            case "add":

                return new PlayersManager(setup, sender, args).onAdd();

            case "remove":

                return new PlayersManager(setup, sender, args).onRemove();

            case "warningremove":

                return new PlayersManager(setup, sender, args).onWarningRemove();

            case "setpoint":

                return new PlayersPointsManager(setup, sender, args).onSetPoints();

            case "addpoint":

                return new PlayersPointsManager(setup, sender, args).onAddPoints();

            case "removepoints":

                return new PlayersPointsManager(setup, sender, args).onRemovePoints();

            case "teamadd":

                break;

            case "teamremove":

                break;

            case "teamset":

                break;

            default:
                showHelp(sender, 1);
                break;

        }

            return false;
        }

    /**
     *
     * @param sender
     * the sender is a commandSender, it will be who receive the messages
     * @param pageNumber
     * pageNumber 1: "Start commands"
     * pageNumber 2: "Util commands"
     * pageNumber 3: "PlayerManager commands"
     * pageNumber 4: "TeamManager commands"
     */

    public static void showHelp(CommandSender sender, int pageNumber) {

        switch(pageNumber) {

            case 1:
                sender.sendMessage(ChatColor.RED + "-------------------" + ChatColor.DARK_RED + "Challengers" + "-------------------");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.RED + "-=-=-=-" + ChatColor.DARK_RED + "Start Commands" + "-=-=-=-");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac start 1" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Démarre l'épreuve " + ChatColor.DARK_AQUA + "de l'escorte.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac start 2" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Démarre l'épreuve " + ChatColor.DARK_AQUA + "du labyrinthe.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac start 3" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Démarre l'épreuve " + ChatColor.DARK_AQUA + "de la construction.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac start 4" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Démarre l'épreuve " + ChatColor.DARK_AQUA + "du parcours.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac start 5." + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Démarre l'épreuve the " + ChatColor.DARK_AQUA + "de l'arène horrifique.");
                sender.sendMessage("\n" +
                        "§4Plugin made by §cPaul COMTE §4All rights reserved");
                break;

            case 2:
                sender.sendMessage(ChatColor.RED + "-------------------" + ChatColor.DARK_RED + "Challengers" + "-------------------");
                sender.sendMessage(ChatColor.RED + "-=-=-=-" + ChatColor.DARK_RED + "Util Commands" + "-=-=-=-");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac stop" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Arrête une épreuve en cours.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac pause" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Mets en pause une épreuve en cours.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac setspawn <team> <numberOfCourseIfRequired>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Mets en pause une épreuve en cours.");
                sender.sendMessage("\n" +
                        "§4Plugin made by §cPaul COMTE §4All rights reserved");
                break;

            case 3:
                sender.sendMessage(ChatColor.RED + "-------------------" + ChatColor.DARK_RED + "Challengers" + "-------------------");
                sender.sendMessage(ChatColor.RED + "-=-=-=-" + ChatColor.DARK_RED + "PlayerManager Commands" + "-=-=-=-");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac add <player>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Ajoute un joueur au jeu. " + ChatColor.RED + " PEUT CORROMPRE LE JEU S'IL A DEJA DEMAREE");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac remove <player>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Supprime un joueur du jeu. " + ChatColor.RED + "PEUT CORROMPRE LE JEU S'IL A DEJA DEMAREE");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac setpoint <player>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Définis le nombre de point du joueur.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac addpoint <player>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Ajoute un nombre de point au joueur.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac removepoint <player>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Supprime un nombre de point du joueur. (ne peut être négatif)");
                sender.sendMessage("\n" +
                        "§4Plugin made by §cPaul COMTE §4All rights reserved");
                break;

            case 4:
                sender.sendMessage(ChatColor.RED + "-------------------" + ChatColor.DARK_RED + "Challengers" + "-------------------");
                sender.sendMessage(ChatColor.RED + "-=-=-=-" + ChatColor.DARK_RED + "TeamManager Commands" + "-=-=-=-");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac teamadd <team>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Définis le nombre de point d'une équipe.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac teamremove <team>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Ajoute un nombre de point à une équipe.");
                sender.sendMessage(ChatColor.DARK_GREEN + "/ac teamset <team>" + ChatColor.WHITE + " - " + ChatColor.YELLOW + "Supprime un nombre de point d'une équipe.");
                sender.sendMessage("\n" +
                        "§4Plugin made by §cPaul COMTE §4All rights reserved");
                break;

            default:
                showHelp(sender, 1);
                sender.sendMessage("Veuillez spécifier un nombre correct entre §e1 & 4");
                break;

        }

    }

    private String a(String a) {
        return ChatColor.translateAlternateColorCodes('&', a);
    }


}
