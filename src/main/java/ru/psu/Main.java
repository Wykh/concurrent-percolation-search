package ru.psu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        GridGenerator gridGenerator = new GridGenerator(100, 0.52);
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
//        FileGridWriter fileGridWriter = new FileGridWriter("grid.txt", grid);
//        try {
//            fileGridWriter.write();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int repeatCount = 500;
        int size = 50;

        for (double concentration = 0.5; concentration <= 0.6; concentration += 0.01) {
            int count = 0;
            for (int repeat = 0; repeat < repeatCount; repeat++) {
                GridGenerator gridGenerator = new GridGenerator(size, concentration);
                Integer[][] grid = gridGenerator.generate();

                ClusterFinder clusterFinder = new ClusterFinder(grid);
                Integer[][] labels = clusterFinder.find();

                PercolationFinder percolationFinder = new PercolationFinder(grid, labels);
                boolean isPercolation = percolationFinder.find();
                if (isPercolation)
                    count++;
            }
            double probability = (double) count / repeatCount;
            System.out.println("Concentration: " + concentration + " Probability: " + probability + " count: " + count);
        }
    }
}