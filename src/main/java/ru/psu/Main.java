package ru.psu;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {

//        GridGenerator gridGenerator = new GridGenerator(100, 0.59);
//        Integer[][] grid = gridGenerator.generate();
//
//        // find count of ones
//        int count = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid.length; j++) {
//                if (grid[i][j] == 1)
//                    count++;
//            }
//        }
//        System.out.println("Count of ones: " + count);
//
//        ClusterFinder clusterFinder = new ClusterFinder(grid);
//        Integer[][] labels = clusterFinder.find();
//
//        PercolationFinder percolationFinder = new PercolationFinder(grid, labels);
//        Boolean isPercolation = percolationFinder.find();
//        System.out.println(isPercolation);
//
//        if (isPercolation) {
//            System.out.println("Percolation cluster: " + percolationFinder.getClusterNumber());
//        }
//
//        FileGridReaderWriter fileGridReaderWriter = new FileGridReaderWriter("grid.txt", grid);
//        fileGridReaderWriter.write();

        int repeatCount = 500;
        int size = 50;

        // create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // time start using ExecutorService
        double startTotalTimeConcurrent = System.nanoTime();
        for (double concentration = 0.59; concentration <= 0.61; concentration += 0.0005) {

            double finalConcentration = concentration;
            Runnable task = () -> {
                doWork(repeatCount, size, finalConcentration);
//                System.out.println("Concentration: " + finalConcentration + " Probability: " + probability + " count: " + count);
            };
            executor.execute(task);
        }
        // print time after all tasks are completed
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        double totalTimeConcurrent = System.nanoTime() - startTotalTimeConcurrent;
        System.out.println("Total time: " + totalTimeConcurrent / 1_000_000_000 + " s");

        // time start without ExecutorService
        double startTotalTime = System.nanoTime();

        for (double concentration = 0.59; concentration <= 0.61; concentration += 0.0005) {
            doWork(repeatCount, size, concentration);
//            System.out.println("Concentration: " + concentration + " Probability: " + probability + " count: " + count);
        }

        double totalTime = System.nanoTime() - startTotalTime;
        System.out.println("Total time: " + totalTime / 1_000_000_000 + " s");

        // using threads
        int threadCount = 4;
        double onePartDelta = (0.61 - 0.59) / threadCount;

        // thread pool
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            double finalConcentration = 0.59 + i * onePartDelta;
            Thread thread = new Thread(() -> {
                double startThreadTime = System.nanoTime();
                for (double concentration = finalConcentration; concentration <= finalConcentration + onePartDelta; concentration += 0.0005) {
                    doWork(repeatCount, size, concentration);
                }
                double totalThreadTime = System.nanoTime() - startThreadTime;
                System.out.println(Thread.currentThread().getName() +" thread time: " + totalThreadTime / 1_000_000_000 + " s");
            });
            threads[i] = thread;
        }
        double startTotalTimeThreads = System.nanoTime();
        for (Thread thread : threads) {
            thread.start();
        }

        //wait all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double totalTimeThreads = System.nanoTime() - startTotalTimeThreads;
        System.out.println("Total time: " + totalTimeThreads / 1_000_000_000 + " s");




//        for (double concentration = 0.59; concentration <= 0.61; concentration += 0.0005) {
//            int count = 0;
//            double averageGridGenerateTimeNs = 0;
//            double averageClusterFindTimeNs = 0;
//            double averagePercolationFindTimeNs = 0;
//
//            double startTotalTime = System.nanoTime();
//            for (int repeat = 0; repeat < repeatCount; repeat++) {
//                double startGenerateGridTime = System.nanoTime();
//                GridGenerator gridGenerator = new GridGenerator(size, concentration);
//                Integer[][] grid = gridGenerator.generate();
//                averageGridGenerateTimeNs += (System.nanoTime() - startGenerateGridTime);
//
//                double startClusterFindTime = System.nanoTime();
//                ClusterFinder clusterFinder = new ClusterFinder(grid);
//                Integer[][] labels = clusterFinder.find();
//                averageClusterFindTimeNs += (System.nanoTime() - startClusterFindTime);
//
//                double startPercolationFindTime = System.nanoTime();
//                PercolationFinder percolationFinder = new PercolationFinder(grid, labels);
//                boolean isPercolation = percolationFinder.find();
//                averagePercolationFindTimeNs += (System.nanoTime() - startPercolationFindTime);
//                if (isPercolation)
//                    count++;
//            }
//            double probability = (double) count / repeatCount;
//            double oneRepeatTime = System.nanoTime() - startTotalTime;
//            averageGridGenerateTimeNs /= repeatCount;
//            averageClusterFindTimeNs /= repeatCount;
//            averagePercolationFindTimeNs /= repeatCount;
//            double averageTimeNs = averageGridGenerateTimeNs + averageClusterFindTimeNs + averagePercolationFindTimeNs;
//
//            double averageGridGenerateTimeMs = averageGridGenerateTimeNs / 1000000;
//            double averageClusterFindTimeMs = averageClusterFindTimeNs / 1000000;
//            double averagePercolationFindTimeMs = averagePercolationFindTimeNs / 1000000;
//            double averageTimeMs = averageTimeNs / 1000000;
//            double oneRepeatTimeMs = oneRepeatTime / 1000000;
//
//            System.out.println("Average grid generate time: " + averageGridGenerateTimeMs + " ms");
//            System.out.println("Average cluster find time: " + averageClusterFindTimeMs + " ms");
//            System.out.println("Average percolation find time: " + averagePercolationFindTimeMs + " ms");
//            System.out.println("Average time: " + averageTimeMs + " ms");
//            System.out.println("One repeat time1: " + averageTimeMs * repeatCount + " ms");
//            System.out.println("One repeat time2: " + oneRepeatTimeMs + " ms");
//            System.out.println("Concentration: " + concentration + " Probability: " + probability + " count: " + count);
//        }
    }

    private static void doWork(int repeatCount, int size, double finalConcentration) {
        int count = 0;
        for (int repeat = 0; repeat < repeatCount; repeat++) {
            GridGenerator gridGenerator = new GridGenerator(size, finalConcentration);
            Integer[][] grid = gridGenerator.generate();

            ClusterFinder clusterFinder = new ClusterFinder(grid);
            Integer[][] labels = clusterFinder.find();

            PercolationFinder percolationFinder = new PercolationFinder(grid, labels);
            boolean isPercolation = percolationFinder.find();

            if (isPercolation)
                count++;
        }
        double probability = (double) count / repeatCount;
//        System.out.println("Concentration: " + finalConcentration + " Probability: " + probability + " count: " + count);
    }
}