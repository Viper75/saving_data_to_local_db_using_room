package com.codewithsean.savingdatatolocaldbexample.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codewithsean.savingdatatolocaldbexample.dao.WordDao;
import com.codewithsean.savingdatatolocaldbexample.database.WordRoomDatabase;
import com.codewithsean.savingdatatolocaldbexample.model.Word;
import com.codewithsean.savingdatatolocaldbexample.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository repository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);

        repository = new WordRepository(application);
        mAllWords = repository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insertWord(Word word) {
        repository.insert(word);
    }
}
