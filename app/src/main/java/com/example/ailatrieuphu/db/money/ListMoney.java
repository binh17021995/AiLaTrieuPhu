package com.example.ailatrieuphu.db.money;

import android.widget.TextView;

import com.example.ailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;

public class ListMoney {
    private ArrayList<String> moneyList;

    public ListMoney(){
        moneyList = new ArrayList<String>();
        moneyList.add("200,000");
        moneyList.add("400,000");
        moneyList.add("600,000");
        moneyList.add("1,000,000");
        moneyList.add("2,000,000");
        moneyList.add("3,000,000");
        moneyList.add("6,000,000");
        moneyList.add("10,000,000");
        moneyList.add("14,000,000");
        moneyList.add("20,000,000");
        moneyList.add("30,000,000");
        moneyList.add("40,000,000");
        moneyList.add("60,0000,000");
        moneyList.add("85,000,000");
        moneyList.add("150,000,000");
    }

    public ArrayList<String> getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(ArrayList<String> moneyList) {
        this.moneyList = moneyList;
    }
}
