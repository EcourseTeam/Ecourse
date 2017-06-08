package com.ecourse.database;

import android.content.ContentValues;

import com.ecourse.structure.SQLEntry;

public interface OnlineDao {

    long addEntry(String table, SQLEntry newEntry, OnlineDaoListener listener);
    long addEntry(String table, ContentValues newEntryCV, OnlineDaoListener listener);
    long addEntries(String table, SQLEntry[] newEntries, OnlineDaoListener listener);
    long addEntries(String table, ContentValues[] newEntriesCV, OnlineDaoListener listener);
    //int updateEntries(String table, ContentValues filter, SQLEntry newEntry);
    //int updateEntries(String table, ContentValues filter, ContentValues newEntryCV);
    //int deleteEntries(String table, ContentValues filter);
    long getEntries(String table, OnlineDaoListener listener);
    long getEntries(String table, ContentValues filter, OnlineDaoListener listener);

    //void closeDB();
}
