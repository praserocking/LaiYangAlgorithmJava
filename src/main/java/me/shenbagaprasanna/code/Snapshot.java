package me.shenbagaprasanna.code;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class which encapsulates a Snapshot.
 * Every snapshot has a unique ID and a map which stores the state of each process against its process id.
 */
public class Snapshot {
    private final String id;
    private final Map<String, Integer> states;

    public Snapshot (final String id) {
        this.id = id;
        this.states = new HashMap<>();
    }

    public void recordState(int state, String processId) {
        states.put(processId, state);
    }

    public String getSnapshotId() {
        return this.id;
    }

    public void printSnapShot() {
        for (Map.Entry<String, Integer> entry : this.states.entrySet()) {
            System.out.println("Process " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
