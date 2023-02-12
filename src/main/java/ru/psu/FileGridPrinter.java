package ru.psu;

public class FileGridPrinter {
    private Integer[][] grid;

    public FileGridPrinter(Integer[][] grid) {
        this.grid = grid;
    }

    // print grid to file
    public void print() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
//                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
