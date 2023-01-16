package com.example.product.service;

import lombok.NoArgsConstructor;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@NoArgsConstructor

public class Ultilities {
    public static String convertDateFormat(java.sql.Date date){
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        return fm.format(date);
    }

    public static Date convertDateFormat2(String date) throws ParseException {
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date tem = fm.parse(date);
        return new java.sql.Date(tem.getTime());
    }

    public static String convertPriceFormat(int price){
        StringBuilder tem = new StringBuilder(Integer.toString(price));
        int count = tem.length();
        int add = 0;
        while(count>3) {
            System.out.println(count);
            if (count % 3 == 0) {
                tem.insert((3 + add), ".");
            } else if (count % 3 == 1) {
                tem.insert((1 + add), ".");
            } else {
                tem.insert((2 + add), ".");
            }
            count -= 3;
            add += 4;
        }
        return tem.toString();
    }

    public static int convertPriceFormat(String price){
        String[] tem = price.split("\\.");
        StringBuilder sb = new StringBuilder();
        for(String s : tem){
            sb.append(s);
        }
        return Integer.parseInt(sb.toString());
    }
}
