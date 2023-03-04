package com.zyc.component;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

@ApplicationScoped
public class MyComponent {
    public final static String CLASS_NAME = "MyComponent";
    public String method1() {
        return CLASS_NAME + " revised111111";
    }
}
