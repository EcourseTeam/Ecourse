package com.ecourse.structure;

import android.content.ContentValues;

import java.util.Map;

public abstract class SQLEntry {

    ContentValues cv;

    public ContentValues getContentValues() {
        return cv;
    }
    public String toString() {
        String str = "";
        for (Map.Entry<String, Object> item : cv.valueSet()) {
            str += item.getKey() + ": " + item.getValue().toString() + ",\n";
        }
        return str;
    }
}
