package com.example.mydemo.core.commndline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialRunner implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("##### InitialRunner..." + strings.toString());
    }
}
