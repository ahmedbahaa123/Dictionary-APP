package com.example.wordlist1;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordsRepository {

    private WordsDao mWordDao;

    private LiveData<List<Words>> gettAllWords;

    public WordsRepository(Application app) {
        WordRoomDb db = WordRoomDb.getInstance(app);
        mWordDao = db.wordsDao();
        gettAllWords = mWordDao.getAllWords();
    }
    //Operation

    //insert
    public void insert(Words word) {
        new InsertAsyncTast(mWordDao).execute(word);
    }

    //delete
    public void delete(Words word) {
        new DeleteAsyncTast(mWordDao).execute(word);
    }

    //update
    public void update(Words word) {
        new UpdateAsyncTast(mWordDao).execute(word);
    }

    //getallwords
    public LiveData<List<Words>> getAllWords() {
        return gettAllWords;
    }

    //delete all words
    public void deleteAllWords() {
        new DeleteAllWordsAsyncTast(mWordDao).execute();
    }


    private static class InsertAsyncTast extends AsyncTask<Words, Void, Void> {

        private WordsDao mWordsDao;

        public InsertAsyncTast(WordsDao wordsDao) {
            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.insert(words[0]);
            return null;
        }
    }

    private static class DeleteAsyncTast extends AsyncTask<Words, Void, Void> {

        private WordsDao mWordsDao;

        public DeleteAsyncTast(WordsDao wordsDao) {
            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    }

    private static class UpdateAsyncTast extends AsyncTask<Words, Void, Void> {

        private WordsDao mWordsDao;

        public UpdateAsyncTast(WordsDao wordsDao) {
            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.update(words[0]);
            return null;
        }
    }

    private static class DeleteAllWordsAsyncTast extends AsyncTask<Void, Void, Void> {

        private WordsDao mWordsDao;

        public DeleteAllWordsAsyncTast(WordsDao wordsDao) {
            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    }

}
