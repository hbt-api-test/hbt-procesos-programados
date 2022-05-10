package com.hbt.process.configuration.model;

import java.util.HashMap;
import java.util.Map;

public enum FrecuencyScheduledProcess {
    CRON(1),
    PERIODICA(2),
    UNICA(3);


    private int value;
    private static Map map = new HashMap<>();

    FrecuencyScheduledProcess(int value){
        this.value = value;
    }

    static {
        for(FrecuencyScheduledProcess frecuencyScheduledProcess : FrecuencyScheduledProcess.values()){
            map.put(frecuencyScheduledProcess.value, frecuencyScheduledProcess);
        }
    }

    public Map frecuencyDataMap(){
        return map;
    }

    public static FrecuencyScheduledProcess valueOf(int frecuencyValue){
        return (FrecuencyScheduledProcess) map.get(frecuencyValue);
    }

    public int getValue(){
        return value;
    }
}
