package com.kudlay.douparser.service;

public class ParamsService {
    public static String parseParams(String category, String experience, String city,String keywords,String status){
        String params = "";

        if(category!=null && !category.isEmpty()){
            params += "&category=" + category;
        }
        if(experience!=null && !experience.isEmpty()){
            params += "&exp=" + experience;
        }
        if(city!=null && !city.isEmpty()){
            params += "&city=" + city;
        }
        if(keywords!=null && !keywords.isEmpty()){
            params += "&search=" + keywords;
        }
        if(status!=null && !status.isEmpty()){
            params += "&descr=" + status;
        }

        if(!params.isEmpty()){
            params = params.substring(1);
        }

        return params;
    }
}
