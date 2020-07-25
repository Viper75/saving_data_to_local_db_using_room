package com.codewithsean.savingdatatolocaldbexample.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codewithsean.savingdatatolocaldbexample.model.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWord(Word word);

    @Query("DELETE FROM words_table")
    void deleteAllWords();

    @Query("SELECT * FROM words_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWordsInAsc();
}
