package com.ecourse.database;

import android.content.ContentValues;

import com.ecourse.structure.SQLEntry;

public interface OfflineDao {

    long addEntry(String table, SQLEntry newEntry);
    long addEntry(String table, ContentValues newEntryCV);
    int updateEntries(String table, ContentValues filter, SQLEntry newEntry);
    int updateEntries(String table, ContentValues filter, ContentValues newEntryCV);
    int deleteEntries(String table, ContentValues filter);
    SQLEntry[] getEntries(String table);
    SQLEntry[] getEntries(String table, ContentValues filter);

    void closeDB();
}
