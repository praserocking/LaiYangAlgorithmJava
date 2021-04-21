package me.shenbagaprasanna.code;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Driver class for simulation of Lai Yang Algorithm.
 */
public class Main {
    public static void main(String [] args) throws Throwable {
        // Threadpool executor to execute the threads.
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);

        // declare 5 processes.
        NumberIncrementingProcess a,b,c,d,e;

        // initialize processes with Name & ID
        a = new NumberIncrementingProcess("A", 0);
        b = new NumberIncrementingProcess("B", 1);
        c = new NumberIncrementingProcess("C", 2);
        d = new NumberIncrementingProcess("D", 3);
        e = new NumberIncrementingProcess("E", 4);

        // Adding references about other processes on a given process.
        a.addSibling(b);
        a.addSibling(c);
        a.addSibling(d);
        a.addSibling(e);

        b.addSibling(a);
        b.addSibling(c);
        b.addSibling(d);
        b.addSibling(e);

        c.addSibling(a);
        c.addSibling(b);
        c.addSibling(d);
        c.addSibling(e);

        d.addSibling(a);
        d.addSibling(b);
        d.addSibling(c);
        d.addSibling(e);

        e.addSibling(a);
        e.addSibling(b);
        e.addSibling(c);
        e.addSibling(d);

        // start executing all processes.
        executor.execute(a);
        executor.execute(b);
        executor.execute(c);
        executor.execute(d);
        executor.execute(e);

        // For every 5 seconds - Trigger a snapshot from process 'E' and wait for 5 secs and print all taken snapshots.
        // this goes on infinitely, till the program is quit.
        int i=0;
        while (true) {
            sleep5Secs();

            final Snapshot snapshot = new Snapshot("S" + i++);
            e.triggerSnapshot(snapshot);

            sleep5Secs();

            e.printSnapShot();
        }
    }

    private static void sleep5Secs() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            // do nothing. let's not worry about this exception on simulation.
        }
    }
}
