package com.uipl.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.ObservableSource;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        String st ="UI555".substring(2);
        System.out.println("%%%%%%%%% "+ st);
    }

    @Test
    public void test1() {
        Observable<String> stream = Observable.fromIterable(SUPER_HEROES);
        stream.subscribe(
                name -> System.out.println(name)
        );
    }
    @Test
    public void test2() {
        Observable<String> stream = Observable.fromIterable(SUPER_HEROES);

        stream.fromIterable(SUPER_HEROES)
                .map(n -> n.toUpperCase())
                .filter(name -> name.startsWith("A"))
                .subscribe(
                        name -> System.out.println(name)
                );
    }
    @Test
    public void test3() {
        Observable<String> stream = Observable.fromIterable(SUPER_HEROES);

        stream.fromIterable(SUPER_HEROES)
                .doOnNext(s -> System.out.println("Next >> " + s))
                .doOnComplete(() -> System.out.println("Completion"))
                .subscribe();
    }

    @Test
    public void test4() {
        Observable.fromIterable(SUPER_HEROES)
                .map(name -> {
                    if (name.endsWith("x")) {
                        throw new RuntimeException("What a terrible failure!");
                    }
                    return name.toUpperCase();
                })
                // Use doOnNext, doOnComplete and doOnError to print messages
                // on each item, when the stream complete, and when an error occurs
                .doOnNext(s -> System.out.println(">> " + s))
                .doOnComplete(() -> System.out.println("Completion... not called"))
                .doOnError(err -> System.out.println("Oh no! " + err.getMessage()))
                .subscribe();
    }
    @Test
    public void test5() {
        Observable.just("Black Canary", "Catwoman", "Elektra")
                .subscribe(
                        name -> System.out.println(">> " + name),
                        Throwable::printStackTrace,
                        () -> System.out.println("Completion")
                );
    }
    @Test
    public void test6() {
        Observable<String> stream = Observable.create(subscriber -> {
            // Emit items
            subscriber.onNext("Black Canary");
            subscriber.onNext("Catwoman");
            subscriber.onNext("Elektra");
            // Notify the completion
            subscriber.onComplete();
        });

        stream
                .subscribe(
                        i -> System.out.println("Received: " + i),
                        err -> System.out.println("BOOM"),
                        () -> System.out.println("Completion")
                );

    }



    private static List<String> SUPER_HEROES = Arrays.asList(
            "Superman",
            "Batman",
            "Aquaman",
            "Asterix",
            "Captain America"
    );

    @Test
    public void test7(){
        Single.just("Superman")
                .doOnSuccess(s -> System.out.println("Hello " + s))
                .subscribe();
    }
}