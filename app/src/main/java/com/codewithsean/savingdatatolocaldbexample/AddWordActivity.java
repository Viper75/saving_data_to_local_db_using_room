package com.codewithsean.savingdatatolocaldbexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.codewithsean.savingdatatolocaldbexample.databinding.AddWordLayoutBinding;

public class AddWordActivity extends AppCompatActivity {
    AddWordLayoutBinding binding;

    public static final String EXTRA_REPLY = "word";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddWordLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = binding.addWordTv.getText().toString();
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(word)) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}