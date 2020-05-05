package com.example.wordlist1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolde> {

    private static List<Words> mWordList = new ArrayList<>();

    private static ClickListener clickListener;

    @NonNull
    @Override
    public WordViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent, false);
        return new WordViewHolde(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolde holder, int position) {
        Words currentWord = mWordList.get(position);
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewMeaning.setText(currentWord.getWordMeaning());
        holder.textViewType.setText(currentWord.getWordType());
    }

    public void setWords(List<Words> words) {
        mWordList = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public static class WordViewHolde extends RecyclerView.ViewHolder {

        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;

        public WordViewHolde(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.word_text_view);
            textViewMeaning = itemView.findViewById(R.id.meaning_text_view);
            textViewType = itemView.findViewById(R.id.type_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    if (clickListener!= null && index != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(mWordList.get(index));
                    }
                }
            });
        }
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        WordAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onItemClick(Words words);
    }
    public Words getWordAt(int pos)
    {
       return mWordList.get(pos);
    }

}
