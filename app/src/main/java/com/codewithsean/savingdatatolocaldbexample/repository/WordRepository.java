package com.codewithsean.savingdatatolocaldbexample.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.codewithsean.savingdatatolocaldbexample.database.WordRoomDatabase;
import com.codewithsean.savingdatatolocaldbexample.dao.WordDao;
import com.codewithsean.savingdatatolocaldbexample.model.Word;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application.getApplicationContext());
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWordsInAsc();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(final Word word) {
        WordRoomDatabase.databaseWriterExcecutor.execute(new Runnable() {
            @Override
            public void run() {
                mWordDao.insertWord(word);
            }
        });
    }
}
