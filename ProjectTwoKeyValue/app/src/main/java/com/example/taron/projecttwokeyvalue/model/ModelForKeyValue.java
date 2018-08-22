package com.example.taron.projecttwokeyvalue.model;

import android.content.Context;
import android.content.SharedPreferences;

public class ModelForKeyValue {
    private String key;
    private String value;

    public ModelForKeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
