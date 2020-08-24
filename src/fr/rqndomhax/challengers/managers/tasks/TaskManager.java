/*
 * Copyright (c) 2020.
 * Discord : _Paul#6918
 * Author : RqndomHax
 * Github: https://github.com/RqndomHax
 */

package fr.rqndomhax.challengers.managers.tasks;

import fr.rqndomhax.challengers.core.Setup;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.InvocationTargetException;

public class TaskManager {

    private final Setup setup;
    private BukkitTask runnable;

    public TaskManager(Setup setup) {
        this.setup = setup;
    }

    public void start(Class<? extends BukkitRunnable> runnable) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        this.runnable = ((BukkitRunnable) runnable.getDeclaredConstructors()[0].newInstance(setup)).runTaskTimerAsynchronously(setup.getCore(), 0, 20);
    }

    public BukkitTask getRunnable() {
        return runnable;
    }
}
