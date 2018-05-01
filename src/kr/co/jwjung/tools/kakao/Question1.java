/*
 * Copyright (c) 2018.
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.kakao;

import java.util.Scanner;
import java.util.Stack;

public class Question1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        StringBuffer buffer = new StringBuffer();
        int pos = 0;
        int strLen = inputStr.length();
        Stack<String> charStack = new Stack<String>();
        for(int i=0; i<inputStr.length(); i++) {
            charStack.push(String.valueOf(inputStr.charAt(i)));
        }

        inputStr = new String();
        for(int i = 0; i<strLen; i++) {
            String temp = charStack.pop();
            System.out.println(temp);
            inputStr += temp;
        }

        while(pos < strLen) {
            String tempStr = new String();
            if(pos + 2 < strLen) {
                tempStr = inputStr.substring(pos, pos+2);
                if(tempStr.startsWith("1")) {
                    tempStr = inputStr.substring(pos, pos+3);
                    pos += 3;
                } else {
                    pos += 2;
                }
            } else {
                tempStr = inputStr.substring(pos);
                pos = strLen;
            }

            buffer.append((char)Integer.parseInt(tempStr));
        }
        System.out.println(buffer.toString());
    }
}
