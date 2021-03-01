package com.example.couponsystem.utiles;

import java.util.ArrayList;
import java.util.List;

public class Logger
{
    public void log(String sentence)
    {
        System.out.println(sentence);
    }
    public void log(List arrayList)
    {
        if(arrayList != null)
        {
            arrayList.forEach(element -> System.out.println(element));
        }
    }
}
