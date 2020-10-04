package ru.job4j.post;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@Fork(value = 1)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class PostBenchmark {

    private Post post;

    @Setup
    public void createTestingData() {
        post = new Post();

        User user1 = new User("Mike");
        user1.addEmail("123@mail.ru");
        user1.addEmail("321@yandex.ru");
        user1.addEmail("test.ru");
        post.addUser(user1);

        User user2 = new User("Tom");
        user2.addEmail("test.com");
        post.addUser(user2);

        User user3 = new User("John");
        user3.addEmail("666@gmail.ru");
        user3.addEmail("000@yandex.ru");
        user3.addEmail("test.ru");
        user3.addEmail("test.com");
        post.addUser(user3);
    }

    @Benchmark
    public List<User> union() {
        return post.union();
    }

    @Benchmark
    public List<User> bubbleUnion() {
        return post.bubbleUnion();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PostBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
