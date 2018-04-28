package com.udacity.sandwichclub.utils;

/*
Used Android Docs for JSONObject as a reference
https://developer.android.com/reference/org/json/JSONObject
 */

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        //Sandwich variables
        Sandwich sandwich = new Sandwich();
        String mainName;
        List<String> alsoKnownAs = null;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = null;

        //create a jsonObject to use for data population of Sandwich
        try {
            JSONObject data = new JSONObject(json);


            mainName = new JSONObject(data.getString("name")).getString("mainName");
//            Log.i(" data: ", data.toString());
            alsoKnownAs = getList(new JSONObject(data.getString("name"))
                    .getJSONArray("alsoKnownAs"));
            placeOfOrigin = isStringNull(data.getString("placeOfOrigin"));
            description = data.getString("description");
            image = data.getString("image");
            ingredients = getList(data.getJSONArray("ingredients"));
            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }

    //method takes a JSONArray and converts it to a List
    public static List<String> getList(JSONArray array){
        List<String> list = new ArrayList<>();

        if(array.length() != 0 && array != null){
            for(int i = 0;i<array.length();i++){
                try {
                    list.add(array.getString(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    //checks if string is null to assign NA as value
    public static String isStringNull(String s){
        if (s == "" || s == null || s.length() == 0){
            s = "Not available";
        }
        return s;
    }

}
