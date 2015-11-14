package com.wilson.data.client;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    private int counter = 0;
    private String prefix = "default";

    public CustomThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    public Thread newThread(Runnable r) {
        return new Thread(r, prefix + "-" + counter++);
    }
}