package com.github.githubrepo.examplemvvm.utils;

import java.util.HashMap;

public class Constants {
    public static String gitHuKey="Bearer b50b0f88db146725d86d853e76afe80a9074fa2a";
    public static String gitHubAgent="PostmanRuntime/7.26.1";
    public static String gitHubAccept="*/*";
    public static String authorization="Authorization";
    public static String userAgent="User-Agent";
    public static String accept="Accept";

    public static HashMap<String,String>getMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put(authorization, gitHuKey);
        map.put(userAgent, gitHubAgent);
        map.put(accept, gitHubAccept);
        return map;
    }
}
