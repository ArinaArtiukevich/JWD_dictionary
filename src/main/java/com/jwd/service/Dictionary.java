package com.jwd.service;

import com.jwd.entity.Pair;
import com.jwd.entity.ParameterListDao;
import com.jwd.exception.DictionaryConsoleApplicationException;
import com.jwd.exception.ValidatorException;

import java.util.ArrayList;

public interface Dictionary {

    void addPair(final Pair englishRussianWord) throws ValidatorException;
    ArrayList<String> findEnglishByRussianWord(final String russianWord) throws ValidatorException, DictionaryConsoleApplicationException;
    String findRussianByEnglishWord(final String englishWord) throws ValidatorException, DictionaryConsoleApplicationException;
    void printAllWords() throws ValidatorException;
    void printNumberOfPairs() throws ValidatorException;
    int randomNumber() throws ValidatorException;
    ParameterListDao getPairsList();
    int getNumberOfPairs();

}
