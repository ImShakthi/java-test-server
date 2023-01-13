package com.imshakthi.javatestserver.helper;

public class StringFormatter {

    private static StringFormatter instance = null;

    private StringFormatter(){}

    public static StringFormatter getInstances() {
        if (instance == null){
            instance = new StringFormatter();
        }
        return instance;
    }

    public String sayHello(){return "Hello";}

}
