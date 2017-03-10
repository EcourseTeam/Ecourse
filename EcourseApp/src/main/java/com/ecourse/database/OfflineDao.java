package com.ecourse.database;

import android.content.ContentValues;

import com.ecourse.structure.Entry;
import com.ecourse.util.Constants;

public interface OfflineDao extends Constants {

    long addEntry(int table, Entry newEntry);
    long addEntry(int table, ContentValues newEntryCV);
    int updateEntries(int table, ContentValues filter, Entry newEntry);
    int updateEntries(int table, ContentValues filter, ContentValues newEntryCV);
    int deleteEntries(int table, ContentValues filter);
    Entry[] getEntries(int table);
    Entry[] getEntries(int table, ContentValues filter);

    void closeDB();
}
