package ru.psu;

public class PercolationFinder {
    private Integer[][] grid;
    private Integer[][] labels;
    private Integer clusterNumber;

    public PercolationFinder(Integer[][] grid, Integer[][] labels) {
        this.grid = grid;
        this.labels = labels;
    }

    // find percolation
    public boolean find() {
        boolean percolation = false;
        for (int i = 0; i < grid.length; i++) {
            if (labels[0][i] != 0) {
                for (int j = 0; j < grid.length; j++) {
                    if (labels[grid.length - 1][j] == labels[0][i]) {
                        percolation = true;
                        clusterNumber = labels[0][i];
                        break;
                    }
                }
            }
        }
        return percolation;
    }

    public Integer getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(Integer clusterNumber) {
        this.clusterNumber = clusterNumber;
    }
}
