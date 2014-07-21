package com.action;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.model.GooglePlacesModel;
import com.unitil.HttpRequest;

public class GooglePlaceAction {
	public static void main(String[] args){
		//发送 GET 请求
        String s=HttpRequest.sendGet("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=34.077721,134.560615&radius=200&sensor=true&key=AIzaSyAe8XE3B_f6i2Wf8vyg1VVBKumsk5XfBgQ"," ");
        
        Gson gson = new Gson();
        
        GooglePlacesModel gpm = gson.fromJson(s, GooglePlacesModel.class);
        
        System.out.println(gpm.getStatus());
        
        for(int i = 0;i<gpm.getResults().size();i++){
        	System.out.println(gpm.getResults().get(i).getName() + "  " + gpm.getResults().get(i).getIcon());
        }
        
//        Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(s);
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        Element root = doc.getRootElement();
//        System.out.println(root.getName());
	}
}
