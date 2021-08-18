package com.jwd.console;

import com.jwd.exception.DictionaryConsoleApplicationException;

import java.util.ArrayList;
import java.util.Scanner;

public class InputProcessor {
    private final Scanner scanner;
    {
        scanner = new Scanner(System.in);
    }

    public int readChoice() {
        String flagString = scanner.next();
        int flag = -1;
        try {
            flag = Integer.parseInt(flagString);
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a number");
            return flag;
        }
        return flag;
    }

    public String readEnglishWord() throws DictionaryConsoleApplicationException {
        System.out.println(("Enter an english word"));
        if(!scanner.hasNext()){
            throw new DictionaryConsoleApplicationException("Enter an english word");
        }
        String result = scanner.next();
        checkEnglishWord(result);
        return result;
    }

    public void checkEnglishWord(String englishWord) throws DictionaryConsoleApplicationException {
        if (!englishWord.matches("^[a-zA-Z][a-zA-Z\\s]+$")) {
            throw new DictionaryConsoleApplicationException("You should enter an english word");
        }
    }

    public void checkRussianWord(String russianWord) throws DictionaryConsoleApplicationException {
        Boolean isRussianWord = false;
        for(int i = 0; i < russianWord.length(); i++) {
            if(Character.UnicodeBlock.of(russianWord.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                isRussianWord = true;
            }
        }
        if(!isRussianWord){
            throw new DictionaryConsoleApplicationException("You should enter a russian word");
        }

    }

    public String readRussianWord() throws DictionaryConsoleApplicationException {
        System.out.println(("Enter a russian word"));
        if(!scanner.hasNext()){
            throw new DictionaryConsoleApplicationException("Enter a russian word");
        }
        String result = scanner.next();
        checkRussianWord(result);
        return result;
    }

    public ArrayList<String> userAnswersQuiz(){
        ArrayList <String> userAnswers = new ArrayList<>();
        do{
            userAnswers.add(scanner.next());
        }while (userAnswers.size() != 5);
        return userAnswers;
    }

    public void cleanUpCloseables() {
        try {
            scanner.close();
            System.out.println("Resource is closed, " + scanner.getClass());

        } catch (final Exception e) {
            System.out.println("Something went wrong during closing " + scanner.getClass());
            e.printStackTrace();
        }
    }
}
