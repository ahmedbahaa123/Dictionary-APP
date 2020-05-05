package com.example.wordlist1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ViewModel
    private WordViewModel mWordViewModel;

    //RecyclerView
    private RecyclerView mRecyclerView;
    private WordAdapter mWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //floating button
        FloatingActionButton floatingActionButton = findViewById(R.id.button_add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to Add Activity
                Intent i = new Intent(MainActivity.this , AddNewWordActivity.class);
                startActivityForResult(i, 1);
            }
        });

        //recycler View
        mRecyclerView = findViewById(R.id.words_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        //connect RecyclerView with adapter
        mWordAdapter = new WordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);

        //View Model
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged(List<Words> words) {
                //Update UI
                //RecyclerView
                mWordAdapter.setWords(words);
                //Toast.makeText(MainActivity.this , "hellooo" , Toast.LENGTH_LONG).show();

            }
        });

        mWordAdapter.setOnItemClickListener(new WordAdapter.ClickListener() {
            @Override
            public void onItemClick(Words words) {
                Intent i = new Intent(MainActivity.this , AddNewWordActivity.class);
                i.putExtra(AddNewWordActivity.EXTRA_ID, words.getId());
                i.putExtra(AddNewWordActivity.EXTRA_WORD, words.getWordName());
                i.putExtra(AddNewWordActivity.EXTRA_TYPE, words.getWordType());
                i.putExtra(AddNewWordActivity.EXTRA_MEANING, words.getWordMeaning());
                startActivity(i);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //Delete Item
                int position = viewHolder.getAdapterPosition();
                mWordViewModel.delete(mWordAdapter.getWordAt(position));
            }
        }).attachToRecyclerView(mRecyclerView);
    }
}
