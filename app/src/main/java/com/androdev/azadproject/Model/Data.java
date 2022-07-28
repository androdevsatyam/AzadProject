package com.androdev.azadproject.Model;

import java.util.ArrayList;

public class Data {
    String mid;
    ArrayList<TData> data=new ArrayList<>();

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

    public static class TData{
            String tid,amount,narration;

            public TData(String tid, String amount, String narration) {
                this.tid = tid;
                this.amount = amount;
                this.narration = narration;
            }

            public String getTid() {
                return tid;
            }

            public String getAmount() {
                return amount;
            }

            public String getNarration() {
                return narration;
            }
        }
}
