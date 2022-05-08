package com.example.medicalhelp.dbs;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDao {
    @Query("SELECT * FROM helpEntries")
    List<Entry> getAllEntries();
    @Query("SELECT * FROM helpEntries WHERE title LIKE :title")
    Entry getEntryByTitle(String title);
    @Query("SELECT * FROM helpEntries WHERE id LIKE :id")
    Entry getEntryById(int id);
    @Insert
    void insertAll(Entry... entries);
    @Delete
    void delete(Entry entry);
    @Update
    void update(Entry entry);
}
