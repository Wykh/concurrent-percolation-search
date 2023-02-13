package ru.psu;

public class GridGenerator {
    private Integer size;
    private Double concentration;
    private Integer[][] grid;

    public GridGenerator(Integer size, Double concentration) {
        this.size = size;
        this.concentration = concentration;
        this.grid = new Integer[size][size];
    }

    // generate grid with [0, 1] values using concentration
    public Integer[][] generate() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = 0;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.random() < concentration) {
                    grid[i][j] = 1;
                }
            }
        }

//        int countToFill = (int) (size * size * concentration);
//        int countFilled = 0;
//        while (countFilled < countToFill) {
//            int x = (int) (Math.random() * size);
//            int y = (int) (Math.random() * size);
//            if (grid[x][y] == 0) {
//                grid[x][y] = 1;
//                countFilled++;
//            }
//        }
        return grid;
    }
}
