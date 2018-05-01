/*
 * Copyright (c) 2018.
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.json;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class JsonParser {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String rrr = "{\"RESPONSE_MESSAGE\":\"%ED%95%B4%EB%8B%B9+CI%EB%A1%9C+ID%EA%B0%80+%EC%97%86%EC%8A%B5%EB%8B%88%EB%8B%A4.\",\"RESPONSE_CODE\":\"1001\"}";

        System.out.println(URLDecoder.decode(rrr, "UTF-8"));
        System.out.println(rrr);
    }
}
