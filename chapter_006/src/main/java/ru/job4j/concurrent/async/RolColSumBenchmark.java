package ru.job4j.concurrent.async;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Fork(value = 1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class RolColSumBenchmark {

    private static int[][] matrix = new int[100][100];

    @Setup
    public void initMatrix() {
        int counter = 1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = counter++;
            }
        }
    }

    @Benchmark
    public Sums[] simpleSum() {
        return RolColSum.sum(matrix);
    }

    @Benchmark
    public Sums[] asyncSum() {
        return RolColSum.asyncSum(matrix);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RolColSumBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
