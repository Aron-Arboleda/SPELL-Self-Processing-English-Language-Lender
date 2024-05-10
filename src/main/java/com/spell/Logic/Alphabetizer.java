package com.spell.Logic;

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
        System.out.println(newList);
        parsedText = newList.toArray(new String[0]);
        super.setText(String.join("\n", parsedText));

    }
}
