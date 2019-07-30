package org.gx.chronorun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChronorunApplication {

    public static void main(String[] args) {
        // Set system property to handle jdk8 issues with LittleCMS (https://bugs.openjdk.java.net/browse/JDK-8041125)
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        SpringApplication.run(ChronorunApplication.class, args);
    }

}
