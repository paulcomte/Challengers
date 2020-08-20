package fr.rqndomhax.challengers.scoreboard;

import fr.rqndomhax.challengers.core.Core;
import fr.rqndomhax.challengers.core.Setup;
import fr.rqndomhax.challengers.managers.team.TeamData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamScoreboard {

    private final Map<UUID, FastBoard> boards = new HashMap<>();
    private final Core core;
    private final Setup setup;

    public TeamScoreboard(Core core, Setup setup) {
        this.core = core;
        this.setup = setup;
    }


    public void newTeamScoreboard(Player p) {
        FastBoard fb = new FastBoard(p);
        fb.updateTitle(ChatColor.RED + "Challengers");

        boards.put(p.getUniqueId(), fb);

    }

    public void removeTeamScoreboard(Player p) {
        FastBoard fb = boards.remove(p.getUniqueId());
    }

    private void updateBoard(FastBoard board) {

        // Set title according to the gamestate

        switch(setup.getGm().getGame().getGameState()) {

            case WAITING:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Phase d'attente");
                break;
            case FIRSTAC:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Epreuve 1: Escorte");
                break;
            case SECONDAC:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Epreuve 2: Labyrinthe");
                break;
            case THIRDAC:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Epreuve 3: Construction");
                break;
            case FOURTHAC:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Epreuve 4: Parcours");
                break;
            case FIFTHAC:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Epreuve 5: Arène monstrueuse");
                break;
            default:
                board.updateTitle(ChatColor.RED + "Challengers " + ChatColor.GOLD + "- " + ChatColor.GREEN + "Erreure Interne");
                break;
        }

        TeamData teamData = setup.getTm().getPlayerTeam(board.getPlayer().getUniqueId());

        board.updateLines(
                "",
                "Equipe : " + teamData.getTeam().getName(),
                "",
                "Point d'équipe : " + teamData.getTeamPoints(),
                "",
                "Joueurs : " + Bukkit.getServer().getOnlinePlayers().size(),
                "Nombre de vos points : " + setup.getGm().getPlayerData(board.getPlayer().getUniqueId()).getPlayerPoints(),
                "",
                "Kills: " + board.getPlayer().getStatistic(Statistic.PLAYER_KILLS),
                ""
        );
    }


    public void runBoard() {
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(this.core, () -> {
            for (FastBoard board : boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);
    }

}
