package com.androdev.azadproject.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    String mid;
    ArrayList<TData> data;

    public Data(String mid, ArrayList<TData> data) {
        this.mid = mid;
        this.data = data;
    }

    public String getMid() {
        return mid;
    }

    public ArrayList<TData> getData() {
        return data;
    }

}
