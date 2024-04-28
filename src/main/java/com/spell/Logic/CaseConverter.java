package com.spell.Logic;

public class CaseConverter extends SPELLEditor {
    public CaseConverter(String textToEdit) {
        super(textToEdit);
    }

    public String upperCase() {
        String text = super.getText().toUpperCase();
        setText(text);
        return text;
    }

    public String lowerCase() {
        // ...
        return "";
    }

    public String camelCasing() {
        // ...
        return "";
    }

    public String sentenceCase() {
        // ...
        return "";
    }

    public String capitalizedCase() {
        // ...
        return "";
    }
}
