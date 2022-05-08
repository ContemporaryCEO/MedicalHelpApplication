package com.example.medicalhelp.dbs;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Entry.class}, version = 2)
public abstract class ApplicationDatabase extends RoomDatabase {
    public abstract EntryDao getEntryDao();
}
