package com.maskleo.basic.keyword._finally;

public class Main {

    public static int push() {
        int i = 0;
        try {
            i++;
            i = i / 0;
            return i++;
        } catch (Exception e) {
            i++;
            return i++;
        } finally {
            System.out.println(i);
            return ++i;
        }
    }

    public static void main(String[] args) {
        System.out.println(push());
    }

}
