package com.ecourse.database;

import android.content.ContentValues;

import com.ecourse.structure.Entry;
import com.ecourse.util.Constants;

public interface OfflineDao extends Constants {

    long addEntry(String table, Entry newEntry);
    long addEntry(String table, ContentValues newEntryCV);
    int updateEntries(String table, ContentValues filter, Entry newEntry);
    int updateEntries(String table, ContentValues filter, ContentValues newEntryCV);
    int deleteEntries(String table, ContentValues filter);
    Entry[] getEntries(String table);
    Entry[] getEntries(String table, ContentValues filter);

    void closeDB();
}
