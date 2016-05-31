package fr.norsys.tests;

public class ClassWithComplexMethods {

    private int initValue;

    public ClassWithComplexMethods(final int value){
        this.initValue = value;
    }

    public int getValue(){
        return initValue;
    }

    public int incrementAndGet(){
        return ++initValue;
    }

    public int getAndIncrement(){
        return initValue++;
    }

}
