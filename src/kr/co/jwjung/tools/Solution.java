package kr.co.jwjung.tools;
//주어진 가격정보의 배열에서 찾고자 하는 가격의 배열인덱스를 반환하는 함수를 구현하세요.
//        시간복잡도가 최상인 방법 (실행시간이 가장 낮은 방법) 으로 구현하세요.
//
//        단 아래의 조건을 가정합니다.
//        숫자배열은 오름차순으로 정렬되어 있음
//
//        ex> {1000,2000,3000,4000,....4000000}

public class Solution {
    public static void main(String[] args) {
        int priceCount = 4000;

        // 가격정보 배열
        int[] priceArray = new int[priceCount];
        for (int priceArrayIdx = 1; priceArrayIdx < priceCount; priceArrayIdx ++) {
            priceArray[priceArrayIdx - 1] = priceArrayIdx * 1000;
        }
        System.out.println("가격의 배열인덱스 : " + getSearchPriceArrayIntex(priceArray, 690000));
        System.out.println(reverse("I am a boy."));

    }

    public static int getSearchPriceArrayIntex(int[] priceArray, int searchPrice) {

        int first = 0;
        int last = priceArray.length;
        int mid;

        while (first <= last) {
            mid = (first + last) / 2;
            if (searchPrice == priceArray[mid]) {
                return mid;
            }
            else {
                if (searchPrice < priceArray[mid])
                    last = mid - 1;
                else
                    first = mid + 1;
            }
        }

        return -1;
    }

    public static String reverse(String origin) {
        String ret = "";
        byte[] tmp = origin.getBytes();
        StringBuffer buf = new StringBuffer();
        for (int i = tmp.length-1; i >= 0 ; i -- ) {
            char s = (char)tmp[i];
            System.out.println(s);
            buf.append(s);
        }
        System.out.println(buf);
        return buf.toString();
    }
}