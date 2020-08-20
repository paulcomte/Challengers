package fr.rqndomhax.challengers.core;

import fr.rqndomhax.challengers.activites.ActivityCommands;
import fr.rqndomhax.challengers.activites.firstactivity.VipSelect;
import fr.rqndomhax.challengers.activites.firstactivity.FirstAL;
import fr.rqndomhax.challengers.activites.firstactivity.FirstM;
import fr.rqndomhax.challengers.listeners.TeamListener;
import fr.rqndomhax.challengers.managers.MessageManagers;
import fr.rqndomhax.challengers.managers.game.GameManager;
import fr.rqndomhax.challengers.managers.game.GameState;
import fr.rqndomhax.challengers.managers.tasks.TaskManager;
import fr.rqndomhax.challengers.managers.team.TeamData;
import fr.rqndomhax.challengers.managers.team.TeamList;
import fr.rqndomhax.challengers.managers.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Setup {
    private final Core core;
    private final MessageManagers mm;
    private FirstM fm;
    private TeamManager tm;
    private GameManager gm;
    private TaskManager taskM;

    public Setup(Core core) {
        this.core = core;
        mm = new MessageManagers(core);
    }

    private void registerVars() {
        tm = new TeamManager(this);
        fm = new FirstM(this);
        gm = new GameManager(this);
        taskM = new TaskManager(this);
    }

    // Register all plugin events
    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new TeamListener(this), this.core);
        pm.registerEvents(new FirstAL(this), this.core);
    }

    // Register all plugin commands
    private void registerCommands() {

        this.core.getCommand("start").setExecutor(new ActivityCommands(this));
        this.core.getCommand("vipselect").setExecutor(new VipSelect(this));

    }

    // Create teams

    private void createTeams() {

        tm.getTeam().getTeams().add(new TeamData(TeamList.RED, 4));
        tm.getTeam().getTeams().add(new TeamData(TeamList.CYAN, 4));
        tm.getTeam().getTeams().add(new TeamData(TeamList.GREEN, 4));
        tm.getTeam().getTeams().add(new TeamData(TeamList.YELLOW, 4));

    }

    public void setup() {

        // Save plugin's config
        this.core.saveDefaultConfig();

        registerVars();

        // Set game state to waiting
        gm.getGame().setGameState(GameState.WAITING);

        createTeams();

        // Register plugin's events and plugin's commands
        registerEvents();
        registerCommands();

    }

    public Core getCore() {
        return core;
    }

    public FirstM getFm() {
        return fm;
    }

    public MessageManagers getMm() {
        return mm;
    }

    public GameManager getGm() {
        return gm;
    }

    public TaskManager getTaskM() {
        return taskM;
    }

    public TeamManager getTm() {
        return tm;
    }
}
