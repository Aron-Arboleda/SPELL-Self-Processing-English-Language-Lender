package com.spell.Logic;

public class PerLineEditor extends SPELLEditor {
    public String[] splitedText;
    public PerLineEditor(String textToEdit) {
        super(textToEdit);
        splitedText = textToEdit.split("\\R");
    }
}

class BulletsEditor extends PerLineEditor {

    public BulletsEditor(String textToEdit) {
        super(textToEdit);
    }

    public void addBullets() {
        // ... \n
    }

    public void removeBullets() {
        // ...
    }
}

class Alphabetizer extends PerLineEditor {
    public Alphabetizer(String textToEdit) {
        super(textToEdit);
    }

    public void sortAlphabetically() {
        // ...
    }

    public void sortReverseAlphabetically() {
        // ...
    }
}

