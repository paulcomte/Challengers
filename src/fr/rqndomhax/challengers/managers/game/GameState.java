package fr.rqndomhax.challengers.managers.game;

import fr.rqndomhax.challengers.managers.tasks.*;
import org.bukkit.scheduler.BukkitRunnable;

public enum GameState {
    WAITING(),
    FIRSTAC("Messages.FirstAC.Start", FirstT.class, 0),
    SECONDAC("Messages.SecondAC.Start", SecondT.class, 0),
    THIRDAC("Messages.ThirdAC.Start", ThirdT.class, 0),
    FOURTHAC("Messages.FourthAC.Start", FourthT.class, 0),
    FIFTHAC("Messages.FifthAC.Start", FifthT.class, 0);

    private int gameState;
    private String path;
    private Class<? extends BukkitRunnable> runnable;


    GameState() {
    }

    GameState(String path, Class<? extends BukkitRunnable> runnable, int gameState) {
        this.path = path;
        this.runnable = runnable;
        this.gameState = gameState;
    }

    public String getPath() {
        return path;
    }

    public Class<? extends BukkitRunnable> getEnum() {
        return runnable;
    }

    public int getGameState() {
        return gameState;
    }

    public GameState withGameState(GameState gameState, int gameState1) {
        this.gameState = gameState1;
        return gameState;
    }
}
