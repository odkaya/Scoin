package _Scoin;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class _Scoin {

	public static void main(String[] args) {
		
		JSONParser parser = new JSONParser();
		
		try {         
            URL url = new URL("https://devakademi.sahibinden.com/history"); // URL to Parse
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) {               
                JSONArray a = (JSONArray) parser.parse(inputLine);
                Map<Date,Double> maps=new HashMap<Date,Double>();
               
                Double max=0.0;
                int count=0;
                // Loop through each item
                for (Object o : a) {
                	count++;
                    JSONObject tutorials = (JSONObject) o;
                    
                    Long date = (Long) tutorials.get("date");
                    //Long to Date format
                    
                    Date d=new Date(date);
                   // System.out.println("Date : " + d);
                    Double value=(Double)tutorials.get("value");
                   // System.out.println("Value : " + value);
                    
                    //put key value pair in a map
                    maps.put(d, value);
                    if(count==30) {
                    	count=0;
                    	max=(Collections.max(maps.values()));
                    	System.out.println(date);
                        
                    	System.out.println(max);
                    }
                }
               
            }
            
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }   

	}

}
