package me.shenbagaprasanna.code;

/**
 * Interface to be implemented by Process to support Lai Yank Algorithm.
 */
public interface LaiYangProcess {
    // add a sibling process - reference to the process in the process group.
    void addSibling(NumberIncrementingProcess p);
    // utility to print all snapshots held by the process.
    void printSnapShot();
    // method to send red message - we will implement this in a way which all messages sent are red.
    void snapshot(Snapshot s);
    // source method to trigger the snapshot starting from the implementing process.
    void triggerSnapshot(Snapshot init);
}
