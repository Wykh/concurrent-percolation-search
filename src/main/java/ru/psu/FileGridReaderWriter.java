package ru.psu;

import java.io.*;

public class FileGridReaderWriter {
    private String fileName;
    private Integer[][] grid;

    public FileGridReaderWriter(String fileName, Integer[][] grid) {
        this.fileName = fileName;
        this.grid = grid;
    }

    public FileGridReaderWriter(String fileName) {
        this.fileName = fileName;
    }

    public Integer[][] read(Integer size) throws IOException {
        grid = new Integer[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = 0;
            }
        }

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        int i = 0;

        while ((line = reader.readLine()) != null) {
            String[] elements = line.split(" ");
            for (int j = 0; j < elements.length; j++) {
                grid[i][j] = Integer.parseInt(elements[j]);
            }
            i++;
        }

        reader.close();
        return grid;
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
