package com.inzenjer.forthepeople.support_classes;

import java.util.Random;

/**
 * Created by SUDHEESH on 11/17/2017.
 */

public class idgen {
    public static  String idgenerator(String s)
    {
        Random r = new Random();
        int i1 = r.nextInt(9999 - 1000) + 9999;
        String st = s+i1;
        return st;
    }
}
