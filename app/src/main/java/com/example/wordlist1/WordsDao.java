package com.example.wordlist1;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WordsDao {

    @Insert
    void insert(Words words);

    @Update
    void update(Words words);

    @Delete
    void delete(Words words);

    @Query("DELETE From wordTable")
    void deleteAllWords();

    @Query("SELECT * From wordTable")
    LiveData<List<Words>> getAllWords();
}
