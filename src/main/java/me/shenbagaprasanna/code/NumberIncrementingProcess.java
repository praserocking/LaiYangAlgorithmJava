package me.shenbagaprasanna.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Number incrementing process - to display the working of Lai Yang Algorithm.
 *
 * The process accesses a {@link Resource} class and if the next process to update is self, then increments the value
 * on the {@link Resource} and copies the value after increment, as its state.
 *
 * Implements Runnable - as we simulate each process as a separate Java Thread.
 * Implements LaiYangProcess - to simulate Lai-Yang Algorithm.
 */
public class NumberIncrementingProcess implements Runnable, LaiYangProcess {

    private final String name;
    private final int id;
    private final List<NumberIncrementingProcess> siblingProcesses;
    private final Map<String, Snapshot> recordedSnapshots;

    public NumberIncrementingProcess(final String name, final int id) {
        this.name = name;
        this.id = id;
        this.recordedSnapshots = new HashMap<>();
        this.siblingProcesses = new ArrayList<>();
    }
    private int state = -1;

    /**
     * Run method of Runnable. We will sleep between 1-5 seconds and
     * get the next process Id from Resource. If that's self process id,
     * then we will increment Resource and update self's state value.
     */
    @Override
    public void run() {
        while (true) {
            sleepRandom();
            int id = Resource.getNextProcess();
            if (id == this.id) {
                state = Resource.increment();
            }
        }
    }

    private int getRandom() {
        return ThreadLocalRandom.current().nextInt(1,5);
    }

    private void sleepRandom() {
        try {
            Thread.sleep(getRandom() * 1000);
        } catch (InterruptedException e) {
            // let's skip this and do nothing as we don't anticipate Interrupted Exception on test bed.
        }
    }

    /**
     * Snapshots are stored on each process in a Map which stores the {@link Snapshot} object against its Snapshot ID.
     */
    @Override
    public void printSnapShot() {
        for (Map.Entry<String, Snapshot> entry : this.recordedSnapshots.entrySet()) {
            System.out.println("For SnapshotID: " + entry.getKey());
            entry.getValue().printSnapShot();
            System.out.println("**********************************");
        }
    }

    @Override
    public void addSibling(final NumberIncrementingProcess process) {
        this.siblingProcesses.add(process);
    }

    @Override
    public void snapshot(final Snapshot s) {
        // If the received snapshot message is already present on list of snapshots taken on this process
        // This is a red message and our process state is red - hence just copy state - not recording snapshot.
        if (this.recordedSnapshots.containsKey(s.getSnapshotId())) {
            print("State: Red - Received a snapshot message with updated states");
            this.recordedSnapshots.put(s.getSnapshotId(), s);
            return;
        }
        // Else, we received a Red message and process state is white.
        // Make the state as red by making an entry on snapshot storing hashmap and send our red messages to
        // all other processes on the system.
        print("State: White - Received Snapshot message to add state");
        s.recordState(this.state, this.name);
        this.recordedSnapshots.put(s.getSnapshotId(), s);
        this.siblingProcesses.forEach(siblingProcess -> siblingProcess.snapshot(s));
    }

    /**
     * Method to trigger initial snapshot.
     *
     * @param init Initial state of snapshot - must be empty.
     */
    @Override
    public void triggerSnapshot(Snapshot init) {
        print("Init Trigger for Snapshot");
        init.recordState(this.state, this.name);
        this.recordedSnapshots.put(init.getSnapshotId(), init);
        this.siblingProcesses.forEach(siblingProcess -> siblingProcess.snapshot(init));
    }

    private void print(String msg) {
        System.out.println(this.name + ": " + msg);
    }
}
