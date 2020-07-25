package com.codewithsean.savingdatatolocaldbexample.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithsean.savingdatatolocaldbexample.databinding.WordListItemBinding;
import com.codewithsean.savingdatatolocaldbexample.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    public class WordViewHolder extends RecyclerView.ViewHolder {
        private WordListItemBinding binding;

        public WordViewHolder(final WordListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    private LayoutInflater mInflater;
    private List<Word> mWords;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WordViewHolder(WordListItemBinding.inflate(mInflater));
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if (mWords != null) {
            Word word = mWords.get(position);
            holder.binding.wordTv.setText(word.getWord());
        } else {
            holder.binding.wordTv.setText("No Words");
        }
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();

        return 0;
    }

    public void setWords(List<Word> words) {
        this.mWords = words;
        notifyDataSetChanged();
    }
}
