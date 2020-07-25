package com.codewithsean.savingdatatolocaldbexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codewithsean.savingdatatolocaldbexample.adapater.WordListAdapter;
import com.codewithsean.savingdatatolocaldbexample.databinding.MainLayoutBinding;
import com.codewithsean.savingdatatolocaldbexample.model.Word;
import com.codewithsean.savingdatatolocaldbexample.viewModel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainLayoutBinding binding;
    private WordViewModel mWordViewModel;
    private static final int ADD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final WordListAdapter adapter = new WordListAdapter(this);
        binding.wordListRv.setLayoutManager(new LinearLayoutManager(this));
        binding.wordListRv.setAdapter(adapter);

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
            }
        });

        binding.addWordFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivityForResult(intent, ADD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(AddWordActivity.EXTRA_REPLY));
            mWordViewModel.insertWord(word);
        } else {
            Toast.makeText(this, "Word not saved.", Toast.LENGTH_SHORT).show();
        }
    }
}