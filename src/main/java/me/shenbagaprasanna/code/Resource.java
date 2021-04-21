package me.shenbagaprasanna.code;

/**
 * Common resource on which the simulation system works on.
 *
 * Explanation of simulation:
 * We have 5 threads which are given the task of incrementing RESOURCE variable in round robin fashion.
 *
 * We have two methods
 * * getNextProcess - gives the process id which can increment the resource next.
 * * increment - increments the resource and returns the value after increment.
 *
 * Say 3 processes, A, B, C are working on incrementing the resource. They will do in following fashion.
 *
 * Process Execution Cycle: A -> B -> C -> A -> B -> C -> A -> ... and so on.
 * State value of Cycle   : 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> ... and goes on.
 *
 */
public final class Resource {
    private static Integer RESOURCE = 0;

    /**
     * Returns the next process which can increment
     * @return process id which can increment next.
     */
    public static synchronized int getNextProcess() {
        return RESOURCE % 5;
    }

    /**
     * Increments resource and returns the same value.
     *
     * @return Resource Value after incrementing.
     */
    public static synchronized int increment() {
        synchronized (RESOURCE) {
            RESOURCE++;
            return RESOURCE;
        }
    }
}
