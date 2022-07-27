package com.androdev.azadproject.Model;

public class TData{
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
