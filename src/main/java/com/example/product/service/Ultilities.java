package com.example.product.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Ultilities {
    public String convertDateFormat(java.sql.Date date){
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        return fm.format(date);
    }

    public Date convertDateFormat2(String date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date tem = fm.parse(date);
        return new java.sql.Date(tem.getTime());
    }

    public String convertPriceFormat(int price){
        StringBuilder tem = new StringBuilder(Integer.toString(price));
        int count = tem.length();
        int add = 0;
        while(count>3) {
            System.out.println(count);
            if (count % 3 == 0) {
                tem.insert((3 + add), ".");
            } else if (count % 3 == 1) {
                tem.insert((1 + add), ".");
            } else if (count % 3 == 2) {
                tem.insert((2 + add), ".");
            }
            count -= 3;
            add += 4;
        }
        return tem.toString();
    }

    public int convertPriceFormat(String price){
        String[] tem = price.split(".");
        return Integer.parseInt(tem.toString());
    }
}
