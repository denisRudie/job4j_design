package ru.job4j.concurrent.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];

        for (int y = 0; y < matrix.length; y++) {
            int rowSum = 0;
            int colSum = 0;
            for (int x = 0; x < matrix.length; x++) {
                rowSum += matrix[y][x];
                colSum += matrix[x][y];
            }
            sums[y] = new Sums(rowSum, colSum);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            try {
                sums[i] = getTask(matrix, i).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sums;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            int rowSum = 0;
            int colSum = 0;

            for (int i = 0; i < matrix.length; i++) {
                rowSum += matrix[index][i];
                colSum += matrix[i][index];
            }
            return new Sums(rowSum, colSum);
        });
    }
}
