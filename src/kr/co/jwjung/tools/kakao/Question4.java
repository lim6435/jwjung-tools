/*
 * Copyright (c) 2018.
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.kakao;

import java.util.Scanner;

public class Question4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int r = programmerStrings(input);
        System.out.println(r);
    }

    static int programmerStrings(String s) {
        String pivot = "programmer";
        int res = 0;
        byte[] r = pivot.getBytes();
        boolean[] flags = new boolean[r.length + 1];
        for(int i =0 ; i<flags.length; i++) {
            flags[i] = false;
        }

        byte[] sBytes = s.getBytes();
        for(int i = 0; i<sBytes.length; i++) {
            int a = pivot.indexOf(sBytes[i]);
            if(a != -1 && flags[a] == false) {
                flags[a] = true;
            }

            if(pivot.length() < i) {
                boolean rrr = true;
                for(int j =0; j<flags.length; j++) {
                    if(!flags[j])
                        rrr = false;
                    else {
                        res ++;
                    }
                }
            }
        }


        return res;
    }
}
