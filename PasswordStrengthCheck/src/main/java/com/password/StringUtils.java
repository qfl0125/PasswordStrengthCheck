package com.password;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class StringUtils {
    private static final int[] SIZE_TABLE = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};


    public static int sizeOfInt(int x) {
        int i;
        for(i = 0; x > SIZE_TABLE[i]; ++i) {
            ;
        }

        return i + 1;
    }

    public static boolean isCharEqual(String str) {
        return str.replace(str.charAt(0), ' ').trim().length() == 0;
    }

    public static boolean isNumeric(String str) {
        int i = str.length();

        do {
            --i;
            if(i < 0) {
                return true;
            }
        } while(Character.isDigit(str.charAt(i)));

        return false;
    }

    public static boolean equalsNull(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0 && !str.equalsIgnoreCase("null")) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
    public static String[] dictionary(){
        ArrayList<String> list=new ArrayList<String>();
        Properties prop = new Properties();
        try{
            String path=StringUtils.class.getResource("/").getPath();
            InputStream in = new BufferedInputStream(new FileInputStream(path+"dictionary.properties"));
            prop.load(in);
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
               // System.out.println("dictionary--------59Line:"+key);
                list.add(key);
            }
            in.close();
            if(list.size()>0){
                String[] arr=new String[list.size()];
                list.toArray(arr);
                return arr;
            }

        }catch(Exception e){
           System.out.println("StringUtils.dictionary Errer!"+e);
        }
        return null;
    }
}
