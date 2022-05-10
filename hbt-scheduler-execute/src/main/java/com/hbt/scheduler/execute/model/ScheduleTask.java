package com.hbt.scheduler.execute.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleTask {

    private int day;
    private int month;
    private int hour;
    private int minutes;
    private ArrayList<Integer> daysOfWeek;
    private int dayOfMonth;

}
