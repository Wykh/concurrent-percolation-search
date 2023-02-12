package ru.psu;

import java.io.IOException;

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

        int repeatCount = 100;
        int size = 1000;

        for (double concentration = 0.5; concentration <= 0.61; concentration += 0.01) {
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