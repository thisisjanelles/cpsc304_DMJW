package com.company;

import java.util.Date;

/**
 * Created by Minjia_Zhan on 11/8/2016.
 */
public class Route {
    static String dep_airport;
    static String arr_airport;
    static Date dep_date;


    public Route(String dep_airport,String arr_airport, Date dep_date) {
        this.dep_airport = dep_airport;
        this.arr_airport = arr_airport;
        this.dep_date = dep_date;
    }

    public String getDep_airport(){
        return this.dep_airport;
    }

    public String getArr_airport(){
        return this.arr_airport;
    }

    public Date getDep_date(){
        return this.dep_date;
    }

}
