package ru.psu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileGridWriter {
    private String fileName;
    private Integer[][] grid;

    public FileGridWriter(String fileName, Integer[][] grid) {
        this.fileName = fileName;
        this.grid = grid;
    }

    // write grid to file
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                // write element in 4 positions
                writer.write(String.format("%4s", grid[i][j]));
            }
            writer.newLine();
        }
        writer.close();
    }
}
