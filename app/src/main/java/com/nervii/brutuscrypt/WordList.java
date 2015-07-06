package com.nervii.brutuscrypt;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class WordList  {

    private static WordList instance;
    private HashSet<String> words;
    public static WordList getInstance() {
        if (instance == null) {
            instance = new WordList();
        }
        return instance;
    }

    public boolean IsWordInList(String word) {
        return words.contains(word.toLowerCase());
    }

    private WordList() {
        words = new HashSet<String>();

        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream("res/raw/words.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.toLowerCase());
            }
        } catch(IOException e) {
            Log.e("BRUTUSCRYPT", e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("BRUTUSCRYPT", e.getMessage());
                }

            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("BRUTUSCRYPT", e.getMessage());
                }

            }
        }
    }
}
