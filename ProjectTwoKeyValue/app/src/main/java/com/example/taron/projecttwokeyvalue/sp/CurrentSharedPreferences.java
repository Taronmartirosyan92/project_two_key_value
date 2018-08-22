package com.example.taron.projecttwokeyvalue.sp;

import android.content.SharedPreferences;

import com.example.taron.projecttwokeyvalue.model.ModelForKeyValue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class CurrentSharedPreferences {
    private static final String JSON_OBJ = "JsonObj";
    public void saveArrList(ArrayList<ModelForKeyValue> list, SharedPreferences sp) {
        final SharedPreferences.Editor editor = sp.edit();
        final Gson gson = new Gson();
        final String json = gson.toJson(list);
        editor.putString(JSON_OBJ, json);
        editor.apply();
    }

    public ArrayList<ModelForKeyValue> getArrList(SharedPreferences sp) {
        final Gson gson = new Gson();
        final String json = sp.getString(JSON_OBJ, null);
        final Type type = new TypeToken<ArrayList<ModelForKeyValue>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
