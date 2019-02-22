package com.example.mjb.maktabshopping.utils;

import java.text.DecimalFormat;

public class numbers {

    private static final char[] FARSI_DIGITS = {'\u06f0', '\u06f1', '\u06f2', '\u06f3', '\u06f4', '\u06f5', '\u06f6', '\u06f7', '\u06f8', '\u06f9'};
    public static String convertNumber(String text) {
        char[] chars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int index = Character.getNumericValue((int) ch);
            if (index >= 0 && index < 10) {
                ch = FARSI_DIGITS[index];
            }
            chars[i] = ch;}return new String(chars);
    }
    public static String splitDigits(int  number) {
        return new DecimalFormat("###,###,###").format(number);
    }
    static  char[]  arabicChars = {'٠','١','٢','٣','٤','٥','٦','٧','٨','٩'};
    public static String convertNuیmber(String text){
    StringBuilder builder = new StringBuilder();
for(int i =0;i<text.length();i++)
        {
        if(Character.isDigit(text.charAt(i)))
        {
        builder.append(arabicChars[(int)(text.charAt(i))-48]);
        }
        else
        {
        builder.append(text.charAt(i));
        }
        }
        return builder.toString();
    }

}
