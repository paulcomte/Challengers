package fr.rqndomhax.challengers.activites.firstactivity;

import fr.rqndomhax.challengers.managers.PlayerData;

import java.util.HashSet;

public class Bodyguard {

    private final HashSet<PlayerData> bodyguards = new HashSet<>();
    private final HashSet<PlayerData> votes = new HashSet<>();

    public HashSet<PlayerData> getBodyguards() {
        return bodyguards;
    }

    public HashSet<PlayerData> getVotes() {
        return votes;
    }
}
