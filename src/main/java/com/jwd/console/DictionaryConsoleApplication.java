package com.jwd.console;

import com.jwd.entity.Pair;
import com.jwd.exception.DictionaryConsoleApplicationException;
import com.jwd.exception.ValidatorException;
import com.jwd.service.Dictionary;
import com.jwd.service.impl.DictionaryImpl;

public class DictionaryConsoleApplication{

    public static final String WELCOME_MESSAGE = "Press:" +
            "\n 0 - Exit" +
            "\n 1 - Find an english word by a russian word" +
            "\n 2 - Find a russian word by an english word" +
            "\n 3 - Add an english and a russian word" +
            "\n 4 - Print all pairs" +
            "\n 5 - Print a number of pairs" +
            "\n 6 - Play quiz";
    public static final int EXIT = 0, FIND_ENGLISH_WORD_BY_RUSSIAN = 1,
            FIND_RUSSIAN_WORD_BY_ENGLISH = 2, ADD_PAIR = 3, PRINT_PAIRS = 4, PRINT_NUMBER_OF_PAIRS = 5, QUIZ = 6;

    private final Dictionary dictionary;
    private final InputProcessor inputProcessor;
    private final Quiz quiz;
    {
        quiz = new Quiz();
    }

    DictionaryConsoleApplication(){
        dictionary = new DictionaryImpl();
        inputProcessor = new InputProcessor();
    }

    public DictionaryConsoleApplication(Dictionary dictionary, InputProcessor inputProcessor) {
        this.dictionary = dictionary;
        this.inputProcessor = inputProcessor;
    }

    public void processMenu(){
        boolean isRunning = true;
        while(isRunning) {
            printConsole(WELCOME_MESSAGE);
            int consoleChoice = inputProcessor.readChoice();
            switch (consoleChoice) {
                case EXIT:
                    isRunning = false;
                    printConsole("App closes.");
                    break;
                case FIND_ENGLISH_WORD_BY_RUSSIAN:
                    printEnglishByRussianWord();
                    break;
                case FIND_RUSSIAN_WORD_BY_ENGLISH:
                    printRussianByEnglishWord();
                    break;
                case ADD_PAIR:
                    addPair();
                    break;
                case PRINT_PAIRS:
                    printPairs();
                    break;
                case PRINT_NUMBER_OF_PAIRS:
                    printNumberOfPairs();
                    break;
                case QUIZ:
                    quiz.quiz(dictionary, inputProcessor);
                    break;
                default:
                    printConsole("Invalid choice. Restarting app.");
            }
        }
    }

    public void start() {
        processMenu();
        inputProcessor.cleanUpCloseables();
    }

    private void printNumberOfPairs() {
        try {
            dictionary.printNumberOfPairs();
        } catch (ValidatorException e) {
            printConsole(e.getMessage());
        }
    }

    private void printPairs() {
        try {
            dictionary.printAllWords();
        } catch (ValidatorException e) {
            printConsole(e.getMessage());
        }
    }

    private void addPair() {
        try {
            Pair pair = new Pair(inputProcessor.readEnglishWord(),inputProcessor.readRussianWord());
            dictionary.addPair(pair);
        } catch (ValidatorException | DictionaryConsoleApplicationException e) {
            printConsole(e.getMessage());
        }
    }

    private void printEnglishByRussianWord() {
        try {
            String russianWord = inputProcessor.readRussianWord();
            printConsole("English translation for: " + russianWord+" is "+dictionary.findEnglishByRussianWord(russianWord));
        } catch (ValidatorException | DictionaryConsoleApplicationException e) {
            printConsole(e.getMessage());
        }
    }

    private void printRussianByEnglishWord() {
        try {
            String englishWord = inputProcessor.readEnglishWord();
            printConsole("Russian translation for: " + englishWord+" is "+ dictionary.findRussianByEnglishWord(englishWord));
        } catch (ValidatorException | DictionaryConsoleApplicationException e) {
            printConsole(e.getMessage());
        }
    }

    private void printConsole(final String message) {
        System.out.println(message);
    }
}
