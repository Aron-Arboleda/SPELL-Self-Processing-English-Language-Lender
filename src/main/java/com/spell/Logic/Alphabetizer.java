package com.spell.Logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Alphabetizer extends PerLineEditor {
    public Alphabetizer(String textToEdit) {
        super(textToEdit);
    }

    public void sortAlphabetically() {
        Arrays.sort(parsedText);
        super.setText(String.join("\n", parsedText));
    }

    public void sortReverseAlphabetically() {
        Arrays.sort(parsedText);
        List<String> newList = Arrays.asList(parsedText);
        Collections.reverse(newList);
        parsedText = newList.toArray(new String[0]);
        super.setText(String.join("\n", parsedText));

    }
    public static void callMethod(Alphabetizer instance, String methodName, String input) {
        try {
            Method method = Alphabetizer.class.getMethod(methodName);
            method.invoke(instance);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
