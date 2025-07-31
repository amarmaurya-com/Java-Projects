import java.util.*;

public class CpuSchedulingSimulator {

    static final String TRACE = "trace";
    static final String SHOW_STATISTICS = "stats";
    static int processCount;
    static int lastInstant = 20;
    static List<Process> processes = new ArrayList<>();
    static char[][] timeline;
    static int[] finishTime;
    static int[] turnAroundTime;
    static double[] normTurn;
    static List<Algorithm> algorithms = new ArrayList<>();
    static String operation;

    static class Process {
        String name;
        int arrival;
        int service;
        int priority;

        Process(String name, int arrival, int service, int priority) {
            this.name = name;
            this.arrival = arrival;
            this.service = service;
            this.priority = priority;
        }
    }

    static class Algorithm {
        char id;
        int quantum;

        Algorithm(char id, int quantum) {
            this.id = id;
            this.quantum = quantum;
        }
    }

    static void initializeStats() {
        timeline = new char[lastInstant][processCount];
        finishTime = new int[processCount];
        turnAroundTime = new int[processCount];
        normTurn = new double[processCount];
    }

    static void clearTimeline() {
        for (int i = 0; i < lastInstant; i++) {
            Arrays.fill(timeline[i], ' ');
        }
    }

    static void fillInWaitTime() {
        for (int i = 0; i < processCount; i++) {
            int arrival = processes.get(i).arrival;
            for (int t = arrival; t < finishTime[i]; t++) {
                if (timeline[t][i] != '*') {
                    timeline[t][i] = '.';
                }
            }
        }
    }

    static void runFCFS() {
        int time = processes.get(0).arrival;
        for (int i = 0; i < processCount; i++) {
            Process p = processes.get(i);
            int start = Math.max(time, p.arrival);
            int end = start + p.service;

            finishTime[i] = end;
            turnAroundTime[i] = end - p.arrival;
            normTurn[i] = (double) turnAroundTime[i] / p.service;

            for (int t = start; t < end; t++) timeline[t][i] = '*';
            for (int t = p.arrival; t < start; t++) timeline[t][i] = '.';

            time = end;
        }
    }

    static void runRoundRobin(int quantum) {
        Deque<Integer> readyQueue = new ArrayDeque<>();
        int[] remaining = new int[processCount];
        boolean[] finished = new boolean[processCount];
        int time = 0, j = 0;

        for (int i = 0; i < processCount; i++) remaining[i] = processes.get(i).service;

        while (time < lastInstant) {
            while (j < processCount && processes.get(j).arrival <= time) {
                readyQueue.addLast(j);
                j++;
            }

            if (!readyQueue.isEmpty()) {
                int pid = readyQueue.pollFirst();
                Process p = processes.get(pid);
                int slice = Math.min(quantum, remaining[pid]);

                for (int s = 0; s < slice && time < lastInstant; s++) {
                    timeline[time][pid] = '*';
                    time++;
                    while (j < processCount && processes.get(j).arrival <= time) {
                        readyQueue.addLast(j);
                        j++;
                    }
                }

                remaining[pid] -= slice;

                if (remaining[pid] == 0 && !finished[pid]) {
                    finishTime[pid] = time;
                    turnAroundTime[pid] = time - p.arrival;
                    normTurn[pid] = (double) turnAroundTime[pid] / p.service;
                    finished[pid] = true;
                } else {
                    readyQueue.addLast(pid);
                }
            } else {
                time++; // idle cycle
            }
        }

        fillInWaitTime();
    }

    static void execute(Algorithm algo) {
        if (operation.equals(TRACE)) {
            System.out.println("\nRunning " + (algo.id == '1' ? "FCFS" : "RR-" + algo.quantum));
        }

        switch (algo.id) {
            case '1':
                runFCFS();
                break;
            case '2':
                runRoundRobin(algo.quantum);
                break;
        }

        printTimeline();
        printStatistics();
    }

    static void printTimeline() {
        System.out.println("\nTimeline:");
        for (int t = 0; t < lastInstant; t++) System.out.print(t % 10 + " ");
        System.out.println("\n--------------------------------------------");

        for (int i = 0; i < processCount; i++) {
            System.out.print(processes.get(i).name + "     |");
            for (int t = 0; t < lastInstant; t++) {
                System.out.print(timeline[t][i] + "|");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------");
    }

    static void printStatistics() {
        System.out.println("\nStatistics:");
        for (int i = 0; i < processCount; i++) {
            System.out.printf("Process %s - Finish: %d, Turnaround: %d, Normalized: %.2f\n",
                    processes.get(i).name, finishTime[i], turnAroundTime[i], normTurn[i]);
        }
    }

    public static void main(String[] args) {
        operation = TRACE;

        processes.add(new Process("P1", 0, 5, 1));
        processes.add(new Process("P2", 1, 3, 2));
        processes.add(new Process("P3", 2, 8, 1));

        processCount = processes.size();
        initializeStats();

        algorithms.add(new Algorithm('1', 0));  // FCFS
        algorithms.add(new Algorithm('2', 2));  // Round Robin with quantum 2

        for (Algorithm algo : algorithms) {
            clearTimeline();
            execute(algo);
        }
    }
}
