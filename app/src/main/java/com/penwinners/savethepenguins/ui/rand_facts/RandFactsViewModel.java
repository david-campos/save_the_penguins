package com.penwinners.savethepenguins.ui.rand_facts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;


public class RandFactsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private String[] mFactsList = null;
    private int mFactIndex = 0;

    // Implementing Fisher–Yates shuffle
    static void sShuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String aux = ar[index];
            ar[index] = ar[i];
            ar[i] = aux;
        }
    }

    public RandFactsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fact");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setFacts(String[] facts) {
        mFactsList = facts.clone();
        sShuffleArray(mFactsList);
        nextRandomFact();
    }

    public void nextRandomFact() {
        mText.setValue(mFactsList[mFactIndex++ % mFactsList.length]);
    }
}