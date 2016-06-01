package fr.norsys.tests;

import fr.norsys.tests.exceptions.DiceException;
import fr.norsys.tests.exceptions.RandomException;

public class MethodsThrowingExceptions {

    private int failures;

    public MethodsThrowingExceptions(final int failures){
        this.failures = failures;
    }

    public void thisMethodWillFail(){
        throw new RandomException(failures+++ " failures yet");
    }

    public void thisMethodMayFail(final boolean fail){
        if (fail) {
            thisMethodWillFail();
        }
    }

    public void rollDice(){
        try {
            thisMethodWillFail();
        } catch (Exception e){
            failures++;
            throw new DiceException(e);
        }
    }
}
