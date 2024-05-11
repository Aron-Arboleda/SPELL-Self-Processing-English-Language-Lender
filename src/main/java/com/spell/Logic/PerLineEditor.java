package com.spell.Logic;

public class PerLineEditor extends SPELLEditor {
    public String[] parsedText;
    public PerLineEditor(String textToEdit) {
        super(textToEdit);
        parsedText = textToEdit.split("\n");
    }
}

