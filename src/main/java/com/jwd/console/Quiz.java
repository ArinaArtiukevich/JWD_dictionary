package com.jwd.console;

import com.jwd.exception.DictionaryConsoleApplicationException;
import com.jwd.exception.ValidatorException;
import com.jwd.service.Dictionary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Quiz {
    private Set<Integer> randomNumbers;
    private ArrayList<String> userAnswers;
    private ArrayList<String> correctAnswers;

    {
        randomNumbers = new HashSet<>();
    }

    public void quiz(Dictionary dictionary, InputProcessor inputProcessor) {
        try {
            do{
                randomNumbers.add(dictionary.randomNumber());
            }while (randomNumbers.size() != 5);
            printRandomEnglishWords(dictionary);
            correctAnswers = new ArrayList<>(correctAnswersQuiz(dictionary));
            printConsole("Enter your answers, new answer - new line");
            userAnswers = new ArrayList<>(inputProcessor.userAnswersQuiz());
            compareAnswersQuiz(dictionary);
        } catch (ValidatorException | DictionaryConsoleApplicationException e) {
            printConsole(e.getMessage());
        }
    }

    private void printRandomEnglishWords(Dictionary dictionary) throws DictionaryConsoleApplicationException, ValidatorException {
        if(dictionary.getNumberOfPairs() < 5){
            throw new DictionaryConsoleApplicationException("You should add pairs to do a quiz");
        }
        for(Integer randomNumber: randomNumbers){
            printConsole(dictionary.getPairsList().getValues().get(randomNumber).getEnglishWord());
        }
    }

    private ArrayList<String> correctAnswersQuiz(Dictionary dictionary) throws DictionaryConsoleApplicationException, ValidatorException {
        ArrayList<String> correctAnswers =  new ArrayList<>();
        for(Integer randomNumber: randomNumbers){
            correctAnswers.add(dictionary.getPairsList().getValues().get(randomNumber).getRussianWord());
        }
        return correctAnswers;
    }

    private void compareAnswersQuiz(Dictionary dictionary) throws DictionaryConsoleApplicationException, ValidatorException {
        int different = 0;
        ArrayList <Integer> indexOfIncorrectAnswers = new ArrayList<>();
        for(int i = 0; i < correctAnswers.size(); i++){
            if(!userAnswers.get(i).equals(correctAnswers.get(i))){
                different++;
                indexOfIncorrectAnswers.add(i);
            }
        }
        printResult(different);
        if(!indexOfIncorrectAnswers.isEmpty()) {
            printConsole("Correct answers: ");
            for (Integer index : indexOfIncorrectAnswers) {
                printConsole(dictionary.findEnglishByRussianWord(correctAnswers.get(index)) + " - " +
                        correctAnswers.get(index));
            }
        }
    }

    private void printResult(Integer different){
        double result = -((double) different/5)*100;
        result += 100;
        printConsole("Quiz is completed: "+result+"%");
    }

    private void printConsole(final String message) {
        System.out.println(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (!randomNumbers.equals(quiz.randomNumbers)) return false;
        if (!userAnswers.equals(quiz.userAnswers)) return false;
        return correctAnswers.equals(quiz.correctAnswers);
    }

    @Override
    public int hashCode() {
        int result = randomNumbers.hashCode();
        result = 31 * result + userAnswers.hashCode();
        result = 31 * result + correctAnswers.hashCode();
        return result;
    }
}
