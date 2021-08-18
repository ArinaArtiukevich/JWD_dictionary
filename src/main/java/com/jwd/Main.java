package com.jwd;

import com.jwd.console.DictionaryConsoleApplication;
import com.jwd.console.InputProcessor;
import com.jwd.exception.DictionaryConsoleApplicationException;
import com.jwd.exception.ValidatorException;
import com.jwd.service.Dictionary;
import com.jwd.service.impl.DictionaryImpl;

public class Main {
    public static void main(String[] args) {
        final Dictionary dictionary = new DictionaryImpl();
        final InputProcessor inputProcessor = new InputProcessor();
        final DictionaryConsoleApplication app = new DictionaryConsoleApplication(dictionary,inputProcessor);
        app.start();

    }
}
