package android;

import com.intuit.karate.junit5.Karate;

public class AndroidRunner {

    @Karate.Test
    public Karate test() {
        return Karate.run("classpath:android/android.feature");
    }
}
