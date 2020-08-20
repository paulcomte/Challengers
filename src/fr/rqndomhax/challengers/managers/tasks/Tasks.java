package fr.rqndomhax.challengers.managers.tasks;

import org.bukkit.scheduler.BukkitRunnable;

public enum Tasks {

    FIRSTTASK(FirstT.class),
    SECONDTASK(SecondT.class),
    THIRDTASK(ThirdT.class),
    FOURTHTASK(FourthT.class),
    FIFHTASK(FifthT.class);

    private final Class<? extends BukkitRunnable> runnable;

    Tasks(Class<? extends BukkitRunnable> runnable) {
        this.runnable = runnable;
    }

    public Class<? extends BukkitRunnable> getRunnable() {
        return runnable;
    }
}
