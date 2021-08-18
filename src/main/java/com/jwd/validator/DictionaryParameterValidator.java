package com.jwd.validator;

import com.jwd.entity.Pair;
import com.jwd.entity.ParameterListDao;
import com.jwd.exception.ValidatorException;

import java.util.ArrayList;

public interface DictionaryParameterValidator {
     void validatePairOrWordsNull(Pair englishAndRussianWord) throws ValidatorException;
     void validatePairIsNull(Pair englishAndRussianWord) throws ValidatorException;
     void validateEnglishWordIsNull(Pair englishAndRussianWord) throws ValidatorException;
     void validateRussianWordIsNull(Pair englishAndRussianWord) throws ValidatorException;
     void validateListIsNull(ArrayList<Pair> pairsList) throws ValidatorException;
     void validateListIsEmpty(ArrayList<Pair> pairsList ) throws ValidatorException;
     void validateParameterListIsNull(ParameterListDao pairsList) throws ValidatorException;
}
