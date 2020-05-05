package com.example.wordlist1;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AddNewWordViewModel extends AndroidViewModel {

    private WordsRepository mRepository;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordsRepository(application);
    }

    public void insert(Words word) {
        mRepository.insert(word);
    }

    public void update(Words word) {
        mRepository.update(word);
    }

}
