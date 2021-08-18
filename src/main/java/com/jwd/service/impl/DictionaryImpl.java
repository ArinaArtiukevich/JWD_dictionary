package com.jwd.service.impl;

import com.jwd.entity.Pair;
import com.jwd.entity.ParameterListDao;
import com.jwd.exception.DictionaryConsoleApplicationException;
import com.jwd.exception.ValidatorException;
import com.jwd.service.Dictionary;
import com.jwd.validator.DictionaryParameterValidator;
import com.jwd.validator.impl.DictionaryParameterValidatorImpl;

import java.util.ArrayList;

public class DictionaryImpl implements Dictionary {
    private ParameterListDao pairsList = new ParameterListDao();
    private final DictionaryParameterValidator validator = new DictionaryParameterValidatorImpl();

    @Override
    public void addPair(Pair pair) throws ValidatorException {
        validator.validateParameterListIsNull(pairsList);
        validator.validateListIsNull(pairsList.getValues());
        validator.validatePairOrWordsNull(pair);
        Boolean pairChangedFlag = false;
        for(Pair localPair: pairsList.getValues() ){
            if(localPair.getEnglishWord().equalsIgnoreCase(pair.getEnglishWord())){
                localPair.setRussianWord(pair.getRussianWord());
                pairChangedFlag = true;
            }
        }
        if(!pairChangedFlag) {
            pairsList.getValues().add(pair);
        }
    }

    @Override
    public ArrayList<String> findEnglishByRussianWord(String russianWord) throws ValidatorException, DictionaryConsoleApplicationException {
        validator.validateParameterListIsNull(pairsList);
        validator.validateListIsNull(pairsList.getValues());
        validator.validateListIsEmpty(pairsList.getValues());
        ArrayList<String> englishWords = new ArrayList<>();
        Boolean findResult = false;
        for(Pair pair: pairsList.getValues() ){
            if(pair.getRussianWord().equalsIgnoreCase(russianWord)){
                englishWords.add(pair.getEnglishWord());
                findResult = true;
            }
        }
        if(!findResult){
            throw new DictionaryConsoleApplicationException("There is no pair for russian word: "+ russianWord);
        }
        return englishWords;
    }

    @Override
    public String findRussianByEnglishWord(String englishWord) throws ValidatorException, DictionaryConsoleApplicationException {
        validator.validateParameterListIsNull(pairsList);
        validator.validateListIsNull(pairsList.getValues());
        validator.validateListIsEmpty(pairsList.getValues());
        String russianWord = new String();
        Boolean findResult = false;
        for(Pair pair: pairsList.getValues() ){
            if(pair.getEnglishWord().equalsIgnoreCase(englishWord)){
                russianWord = pair.getRussianWord();
                findResult = true;
            }
        }
        if(!findResult){
            throw new DictionaryConsoleApplicationException("There is no pair for english word: "+ englishWord);
        }
        return russianWord;
    }

    @Override
    public void printAllWords() throws ValidatorException {
        validator.validateParameterListIsNull(pairsList);
        validator.validateListIsNull(pairsList.getValues());
        validator.validateListIsEmpty(pairsList.getValues());
        for(Pair pair: pairsList.getValues() ){
            validator.validatePairOrWordsNull(pair);
            System.out.println(pair.toString());
        }
    }
    @Override
    public void printNumberOfPairs() throws ValidatorException {
        validator.validateParameterListIsNull(pairsList);
        validator.validateListIsNull(pairsList.getValues());
        for(Pair pair: pairsList.getValues() ){
            validator.validatePairOrWordsNull(pair);
        }
        System.out.println("Number of pairs: " + pairsList.getValues().size());
    }

    @Override
    public int randomNumber() throws ValidatorException {
        validator.validateParameterListIsNull(pairsList);
        validator.validateListIsNull(pairsList.getValues());
        validator.validateListIsEmpty(pairsList.getValues());
        return (int)(Math.random() * pairsList.getValues().size());
    }

    @Override
    public ParameterListDao getPairsList() {
        return pairsList;
    }
    @Override
    public int getNumberOfPairs() {
        return pairsList.getValues().size();
    }
}
