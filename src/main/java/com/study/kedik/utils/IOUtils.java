package com.study.kedik.utils;

import java.util.Scanner;

public class IOUtils {

    public static int getUserInt(String message){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
