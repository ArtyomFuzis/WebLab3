package org.lab3.wweblab3.services;

import jakarta.ejb.Remove;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab3.wweblab3.utilbeans.RegMBeans;

import java.io.Serializable;
import java.util.*;

@Named("c")
@SessionScoped
public class Counter implements Serializable, CounterMBean {
    public Counter(){

    }
    @Inject
    public Counter(RegMBeans reg){
        this.reg = reg;
        reg.registerBean(this);
    }
    RegMBeans reg;
    private final List<String> MissesList = Arrays.asList("Мазила!", "Как можно столько промазывать?", "Более трех неудач!", "Бог любит троицу, но не в твоем случае!");
    private String counterMissesComment;
    private int counterValueMisses = 0;
    private int counterAll = 0;
    private int counterSuccess = 0;
    static Random rand = new Random();
    private String getRndMessage(List<String> values){
        return values.get(rand.nextInt(0,values.size()));
    }
    private void clearMissesCounter(){
        counterMissesComment = "";
        counterValueMisses = 0;
    }
    @Override
    public void updateCounter(boolean success){
        counterAll++;
        if(success){
            counterSuccess++;
            clearMissesCounter();
        }
        else {
            counterValueMisses++;
            if (counterValueMisses >= 3) {
                counterMissesComment = getRndMessage(MissesList);
            }
        }
    }
    @Override
    public void clearCounter(){
        clearMissesCounter();
        counterAll = 0;
        counterSuccess = 0;
    }
    public int getCounterAll() {
        return counterAll;
    }

    public int getCounterSuccess() {
        return counterSuccess;
    }

    public String getCounterMissesComment() {
        return counterMissesComment;
    }
    @Remove
    public void dispose(){
        System.out.println("Some counter bean was removed");
        reg.unregisterBean(this);
    }
}
