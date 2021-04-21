### Simulation of Lai Yang Algorithm.

#### Simulation Assumptions:
* Java threads are used to simulate processes on a multi processor environment.
* Example process performed by the processes together is,
* * Incrementing Resource by processes in round robin fashion.
* * Keeping other processes waiting, till its turn comes in the order as defined by Resource itself.

* One Snapshot is taken for every 5 seconds on the system and all snapshots are printed 5 seconds after that.
* All Snapshots are stored on the process on a Hashmap.

* Since we are taking snapshots on a system which updates the resource on circular round robin fashion, the snapshot
should correspond to same circular fashion.

For instance, in a 3 process environment - Sample snapshots will be
[1,2,3], [5,6,4], [9,7,8], etc. Follows the sequence in circular fashion, starting from lowest value on snapshot.

Thus a complete snapshot will correspond to C1 & C2 in this given system.

#### How to execute the Code:
* Coding Language - Java
* Environment - JRE 1.8
* IDE - Intellij Idea
* Build System - Gradle
* OS: MacOS/Linux

#### Instruction to Run Code
* Make sure JAVA_HOME and JAVAC is included on the terminal/shell path.
* Run `./gradlew run` to execute the console application which will run the application.
* Wait 10-15 seconds. System will run the processes in the backend and print once in 5 seconds about Red/White message status and Current list of snapshots taken so far.
* Quit the program by pressing cmd+x or ctrl+x, as it runs an infinite loop, printing snapshots.

EXTENSIVE COMMENTS ARE ADDED ON CODE ABOUT WHAT EACH CLASS/METHOD DOES.

