package com.action;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.model.Shop;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.unitil.HttpRequest;

public class GetDataAction {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, SQLException {
		//发送 GET 请求
        String s=HttpRequest.sendGet("http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=60d9430f7a6d5fc7&service_area=SA81&count=5"," ");
        System.out.println(s);
        ArrayList<Shop> shoplist = new ArrayList<Shop>();
        
        Document doc = null;
		try {
			doc = DocumentHelper.parseText(s);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Element root = doc.getRootElement();
        System.out.println(root.getName());
        Iterator it = root.elementIterator("shop");
        while(it.hasNext()){
        	Shop shop = new Shop();
        	Element tmp = (Element) it.next();
        	shop.setName(tmp.elementText("name"));
        	shop.setAccess(tmp.elementText("access"));
        	shop.setLogo_image(tmp.elementText("logo_image"));
        	shop.setName_kana(tmp.elementText("name_kana"));
        	shop.setAddress(tmp.elementText("address"));
        	shop.setStation_name(tmp.elementText("station_name"));
        	shop.setKtai_coupon(tmp.elementText("ktai_coupon"));
        	
        	Element sub = tmp.element("large_service_area");
        	shop.setLarge_service_area(sub.elementText("name"));
        	sub = tmp.element("service_area");
        	shop.setService_area(sub.elementText("name"));
        	sub = tmp.element("large_area");
        	shop.setLarge_service_area(sub.elementText("name"));
        	sub = tmp.element("middle_area");
        	shop.setMiddle_area(sub.elementText("name"));
        	sub = tmp.element("small_area");
        	shop.setSmall_area(sub.elementText("name"));
        	
        	shop.setLat(tmp.elementText("lat"));
        	shop.setLng(tmp.elementText("lng"));
        	shop.setGenre(tmp.elementText("genre"));
        	shop.setSub_genre(tmp.elementText("sub_genre"));
        	shop.setFood(tmp.elementText("food"));
        	
        	sub = tmp.element("budget");
        	shop.setBudget(sub.elementText("name"));
        	
        	shop.setCatch_word(tmp.elementText("catch"));
        	shop.setCapacity(tmp.elementText("capacity"));
        	shop.setOpen(tmp.elementText("open"));
        	shop.setClose(tmp.elementText("close"));
        	shop.setWifi(tmp.elementText("wifi"));
        	shop.setFree_drink(tmp.elementText("free_drink"));
        	shop.setFree_food(tmp.elementText("free_food"));
        	shop.setPrivate_room(tmp.elementText("private_room"));
        	shop.setCard(tmp.elementText("card"));
        	shop.setParking(tmp.elementText("parking"));
        	shop.setShow(tmp.elementText("show"));
        	shop.setBand(tmp.elementText("band"));
        	shop.setTv(tmp.elementText("tv"));
        	shop.setEnglish(tmp.elementText("english"));
        	
        	sub = tmp.element("coupon_urls");
        	shop.setCoupon_url(sub.elementText("mobile"));
        	shop.setCoupon_qr(sub.elementText("qr"));
        	
        	shop.setCourse(tmp.elementText("course"));
        	
        	sub = tmp.element("photo");
        	Element ssub = sub.element("pc");
        	shop.setPhoto_l(ssub.elementText("l"));
        	shop.setPhoto_s(ssub.elementText("s"));
        	
        	shop.setLunch(tmp.elementText("lunch"));
        	shop.setMidnight(tmp.elementText("midnight"));
        	shoplist.add(shop);
        }
        
        
        
        String msg = "";
        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          msg = "ドライバのロードに成功しました";
          
       
          Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/shopict", "root", "1234");


          String sqlStr = "insert into hotpepperdata(name, logo_image, name_kana, address, station_name, ktai_coupon, large_service_area, service_area, large_area, middle_area, small_area, lat, lng, genre, catch_word, sub_genre, " +
          		"food, sub_food, budget, capacity, access, open, close, wifi, free_food, free_drink, private_room, card, parking, karaoke, band, live_show, tv, english, coupon_url, coupon_qr, course, photo_l, photo_s, lunch, midnight) values (" +
          		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
          PreparedStatement ps = (PreparedStatement) con.prepareStatement(sqlStr);
          
          for(int i = 0;i<shoplist.size();i++){
        	  ps.setString(1, shoplist.get(i).getName());
        	  ps.setString(2, shoplist.get(i).getLogo_image());
        	  ps.setString(3, shoplist.get(i).getName_kana());
        	  ps.setString(4, shoplist.get(i).getAddress());
        	  ps.setString(5, shoplist.get(i).getStation_name());
        	  ps.setString(6, shoplist.get(i).getKtai_coupon());
        	  ps.setString(7, shoplist.get(i).getLarge_service_area());
        	  ps.setString(8, shoplist.get(i).getService_area());
        	  ps.setString(9, shoplist.get(i).getLarge_area());
        	  ps.setString(10, shoplist.get(i).getMiddle_area());
        	  ps.setString(11, shoplist.get(i).getSmall_area());
        	  ps.setString(12, shoplist.get(i).getLat());
        	  ps.setString(13, shoplist.get(i).getLng());
        	  ps.setString(14, shoplist.get(i).getGenre());
        	  ps.setString(15, shoplist.get(i).getCatch_word());
        	  ps.setString(16, shoplist.get(i).getSub_genre());
        	  ps.setString(17, shoplist.get(i).getFood());
        	  ps.setString(18, shoplist.get(i).getSub_food());
        	  ps.setString(19, shoplist.get(i).getBudget());
        	  ps.setString(20, shoplist.get(i).getCapacity());
        	  ps.setString(21, shoplist.get(i).getAccess());
        	  ps.setString(22, shoplist.get(i).getOpen());
        	  ps.setString(23, shoplist.get(i).getClose());
        	  ps.setString(24, shoplist.get(i).getWifi());
        	  ps.setString(25, shoplist.get(i).getFree_food());
        	  ps.setString(26, shoplist.get(i).getFree_drink());
        	  ps.setString(27, shoplist.get(i).getPrivate_room());
        	  ps.setString(28, shoplist.get(i).getCard());
        	  ps.setString(29, shoplist.get(i).getParking());
        	  ps.setString(30, shoplist.get(i).getKaraoke());
        	  ps.setString(31, shoplist.get(i).getBand());
        	  ps.setString(32, shoplist.get(i).getShow());
        	  ps.setString(33, shoplist.get(i).getTv());
        	  ps.setString(34, shoplist.get(i).getEnglish());
        	  ps.setString(35, shoplist.get(i).getCoupon_url());
        	  ps.setString(36, shoplist.get(i).getCoupon_qr());
        	  ps.setString(37, shoplist.get(i).getCourse());
        	  ps.setString(38, shoplist.get(i).getPhoto_l());
        	  ps.setString(39, shoplist.get(i).getPhoto_s());
        	  ps.setString(40, shoplist.get(i).getLunch());
        	  ps.setString(41, shoplist.get(i).getMidnight());
        	  
        	  ps.addBatch();
          }
          
          ps.executeBatch();
          
          con.close();
      }catch (ClassNotFoundException e){
          msg = "ドライバのロードに失敗しました";
          System.out.println(msg);
      }
        
        
        
	}
	
	

}
