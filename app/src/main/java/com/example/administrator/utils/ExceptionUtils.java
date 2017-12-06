package com.example.administrator.utils;

/**
 * Created by zhy on 15/12/14.
 */
public class ExceptionUtils
{
    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }


}
