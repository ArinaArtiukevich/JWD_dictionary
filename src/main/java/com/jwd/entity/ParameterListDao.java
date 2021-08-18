package com.jwd.entity;

import java.util.ArrayList;

public class ParameterListDao {
    private ArrayList<Pair> values;

    public ParameterListDao() {
         values = new ArrayList<>();
         initialization();
    }
    private void initialization(){
        values.add(new Pair("I","я"));
        values.add(new Pair("am","есть"));
        values.add(new Pair("go","идти"));
        values.add(new Pair("buy","купить"));
        values.add(new Pair("have","иметь"));
        values.add(new Pair("computer","компьютер"));
        values.add(new Pair("gloves","перчатки"));
        values.add(new Pair("water","вода"));
        values.add(new Pair("drink","пить"));
        values.add(new Pair("school","школа"));
    }
    public ArrayList<Pair> getValues() {
        return values;
    }

    public void setValues(ArrayList<Pair> values) {
        this.values = values;
    }

}
