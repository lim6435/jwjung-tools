/*
 * Copyright (c) 2018.
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.kakao;

import java.util.ArrayList;
import java.util.Scanner;

public class Question3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizes = scanner.nextInt();

        int[] collections = new int[sizes];
        for (int i = 0; i < sizes; i++) {
            collections[i] = scanner.nextInt();
        }
        int d = scanner.nextInt();
        int[] res = hackerCards(collections, d);
        for (int i : res) {
            System.out.println(i);
        }
    }

    static int[] hackerCards(int[] collection, int d) {
        //d는 총 가진 금액
        ArrayList<Integer> result = new ArrayList<Integer>();
        int pos, pivot;
        // e는 최대 맥스(collection에서 한 index갖고 온거)
        for (int i = 0; i < collection.length; i++) {
            if (i == 0) {
                pos = 1;
            } else {
                pos = collection[i - 1] + 1;
            }

            if (i < collection.length) {
                pivot = collection[i];
            } else {
                pivot = -1;
            }
            if (d < pos)
                break;

            for (int j = pos; j < pivot; j++) {
                if (j <= d) {
                    result.add(j);
                    d -= j;
                } else {
                    break;
                }
            }
        }
        int [] arr = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }
}
