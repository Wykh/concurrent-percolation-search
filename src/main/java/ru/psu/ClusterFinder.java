package ru.psu;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClusterFinder {
    private Integer[][] grid;

    public ClusterFinder(Integer[][] grid) {
        this.grid = grid;
    }

    // hoshen-kopelman algorithm
    public Integer[][] find() {
        Integer[][] labels = new Integer[grid.length][grid.length];
        // fill labels with 0
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                labels[i][j] = 0;
            }
        }

        Map<Integer, List<Point>> labelsMap = new HashMap<>();
        Integer currentLabel = 1;
        labelsMap.put(currentLabel, new LinkedList<>());

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 && j == 0) {
                        labels[i][j] = currentLabel;
                        currentLabel++;
                    } else if (i == 0) {
                        if (labels[i][j - 1] != 0) {
                            labels[i][j] = labels[i][j - 1];
                        } else {
                            labels[i][j] = currentLabel;
                            currentLabel++;
                        }
                    } else if (j == 0) {
                        if (labels[i - 1][j] != 0) {
                            labels[i][j] = labels[i - 1][j];
                        } else {
                            labels[i][j] = currentLabel;
                            currentLabel++;
                        }
                    } else {
                        if (labels[i - 1][j] != 0 && labels[i][j - 1] != 0) {
                            labels[i][j] = labels[i - 1][j];
                            if (labels[i - 1][j] != labels[i][j - 1]) {
                                for (int k = 0; k < grid.length; k++) {
                                    for (int l = 0; l < grid.length; l++) {
                                        if (labels[k][l] == labels[i][j - 1]) {
                                            labels[k][l] = labels[i - 1][j];
                                        }
                                    }
                                }
                            }
                        } else if (labels[i - 1][j] != 0) {
                            labels[i][j] = labels[i - 1][j];
                        } else if (labels[i][j - 1] != 0) {
                            labels[i][j] = labels[i][j - 1];
                        } else {
                            labels[i][j] = currentLabel;
                            currentLabel++;
                        }
                    }
                }
            }
        }
        return labels;
    }
}
