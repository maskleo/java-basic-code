package com.maskleo.basic.collection;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Test01 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
           Method method = ArrayList.class.getMethod("add",Object.class);
           method.invoke(list,"a");
           System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
