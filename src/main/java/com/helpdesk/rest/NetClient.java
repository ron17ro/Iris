package com.helpdesk.rest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class NetClient{
//	public static void main(String[] args) {
//		netClientGet();
//	}
	 private static String dictionaryEntries(String yourWord) {
         final String language = "en";
         final String word = yourWord;
        // final String word = "Ace";
         final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
         return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
     }
	 
	public static String netClientGet(String yourWord) {
	   
	  try {

		  final String app_id = "c04a1ded";
          final String app_key = "347eadee413ea41c7c6b82c3340fc58b";
          try {
          URL url = new URL(dictionaryEntries(yourWord));
          HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
          urlConnection.setRequestProperty("Accept","application/json");
          urlConnection.setRequestProperty("app_id",app_id);
          urlConnection.setRequestProperty("app_key",app_key);
          urlConnection.setRequestMethod("GET");
          urlConnection.setRequestProperty("Accept", "application/json");

		if (urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ urlConnection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(urlConnection.getInputStream())));
		StringBuilder stringBuilder = new StringBuilder();

        String line = null;
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }

		
		String jsonData = stringBuilder.toString();
		System.out.println(jsonData + "");
		JSONObject obj = new JSONObject(jsonData);
	

          return stringBuilder.toString();
		
		
		
//		
//		JSONArray resultsArr = obj.getJSONArray("results");
//		String test = resultsArr.getJSONObject(0).toString();
		
//
//		JSONArray lexicalEntriesArr = resultsArr.getJSONObject(0).getJSONArray("lexicalEntries");
//		JSONArray entriesArr = lexicalEntriesArr.getJSONObject(0).getJSONArray("entries");
//		
//		JSONArray sensesArr = entriesArr.getJSONObject(0).getJSONArray("senses");
//		System.out.println(sensesArr.length());
//		
//		JSONArray definitionsArr=new JSONArray();
//		List<String> listdata = new ArrayList<String>();     
//		for (int i = 0; i < sensesArr.length(); i++) {
//			System.out.println(sensesArr.getJSONObject(i).getJSONArray("definitions"));
//			definitionsArr.put(sensesArr.getJSONObject(i).getJSONArray("definitions"));
//			listdata.add(sensesArr.getJSONObject(i).getJSONArray("definitions").getString(0));
//		}
//		urlConnection.disconnect(); 
//		
//		return listdata;
//
	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	  }catch(Exception e) {
		  	 e.printStackTrace();
			 System.out.print("test finally");
		  }
	  return null;

	}
}
	