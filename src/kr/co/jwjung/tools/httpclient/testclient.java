/*
 * Copyright (c) 2018.
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.httpclient;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

public class testclient {
    public static void main(String[] args){

        try {
            HashMap<String, String> reqMap = new HashMap<String, String>();
            reqMap.put("urlType", "recvJoinSync");
            reqMap.put("CI", URLEncoder.encode("tv06dd7uvghEOiuOeQMniful1vnd06vHZ/X6Bed3FnePe7MjePfcCnMTnPFtZ/T2jzobB6AJzrMTP6cxrD2N8Q==", "UTF-8"));
            reqMap.put("ID", URLEncoder.encode("c@c.com","UTF-8"));


//            reqMap.put("custCi", "tv06dd7uvghEOiuOeQMniful1vnd06vHZ/X6Bed3FnePe7MjePfcCnMTnPFtZ/T2jzobB6AJzrMTP6cxrD2N8Q==");
//            reqMap.put("memId", "c@c.com");
            reqMap.put("HP_NO", "010-9775-1963");
            reqMap.put("REG_DATE", "20180501");
            reqMap.put("CSTMR_NM", URLEncoder.encode("정주원", "UTF-8"));
//            reqMap.put("SCSS_ATON", URLEncoder.encode("20180502", "UTF-8"));
            HttpClientWrapper wrapper = new HttpClientWrapper();
//            String resp = HttpClientWrapper.getInstance().sendPostQryStrBase64("https://poc-test.vpay.co.kr/jsp/Time_test.jsp", reqMap);
            String resp = wrapper.sendPostQryStr("https://poc.vpay.co.kr/poc/jsp/bridge/playbooc/playRecvBridge.jsp", reqMap);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
