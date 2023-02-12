package ru.psu;

import java.awt.*;
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
                        updateLabelsMap(labelsMap, currentLabel, i, j);
                        currentLabel++;

                    } else if (i == 0) {
                        if (labels[i][j - 1] != 0) {
                            labels[i][j] = labels[i][j - 1];
                            labelsMap.get(labels[i][j]).add(new Point(i, j));

                        } else {
                            labels[i][j] = currentLabel;
                            updateLabelsMap(labelsMap, currentLabel, i, j);
                            currentLabel++;

                        }
                    } else if (j == 0) {
                        if (labels[i - 1][j] != 0) {
                            labels[i][j] = labels[i - 1][j];
                            labelsMap.get(labels[i][j]).add(new Point(i, j));

                        } else {
                            labels[i][j] = currentLabel;
                            updateLabelsMap(labelsMap, currentLabel, i, j);
                            currentLabel++;
                        }
                    } else {
                        if (labels[i - 1][j] != 0 && labels[i][j - 1] != 0) {
                            int minLabel = labels[i - 1][j] < labels[i][j - 1] ? labels[i - 1][j] : labels[i][j - 1];
                            int maxLabel = labels[i - 1][j] > labels[i][j - 1] ? labels[i - 1][j] : labels[i][j - 1];
                            labels[i][j] = minLabel;
                            labelsMap.get(maxLabel).add(new Point(i, j));

                            if (minLabel != maxLabel) {
                                List<Point> maxLabelListItem = labelsMap.get(maxLabel);
                                List<Point> minLabelListItem = labelsMap.get(minLabel);

                                for (Point p : maxLabelListItem) {
                                    labels[p.x][p.y] = minLabel;
                                }

                                minLabelListItem.addAll(maxLabelListItem);
                                labelsMap.remove(maxLabel);
                            }

                        } else if (labels[i - 1][j] != 0) {
                            labels[i][j] = labels[i - 1][j];
                            labelsMap.get(labels[i][j]).add(new Point(i, j));

                        } else if (labels[i][j - 1] != 0) {
                            labels[i][j] = labels[i][j - 1];
                            labelsMap.get(labels[i][j]).add(new Point(i, j));

                        } else {
                            labels[i][j] = currentLabel;
                            updateLabelsMap(labelsMap, currentLabel, i, j);
                            currentLabel++;
                        }
                    }
                }
            }
        }
        return labels;
    }

    private void updateLabelsMap(Map<Integer, List<Point>> labelsMap, Integer currentLabel, int i, int j) {
        if (labelsMap.containsKey(currentLabel)) {
            labelsMap.get(currentLabel).add(new Point(i, j));
        } else {
            labelsMap.put(currentLabel, new LinkedList<>());
            labelsMap.get(currentLabel).add(new Point(i, j));
        }
    }
}
