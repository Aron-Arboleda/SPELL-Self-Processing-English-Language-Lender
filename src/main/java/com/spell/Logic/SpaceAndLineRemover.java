package com.spell.Logic;

public class SpaceAndLineRemover extends SPELLEditor {
    public SpaceAndLineRemover(String textToEdit) {
        super(textToEdit);
    }

    public String removeLineBreaks() {
        String text = super.excessSpaceRemover();
        text = text.replaceAll("\n", "");
        return text;
    }

    public String removeSpaces() {
        String text = super.getText();
        text = text.replaceAll(" ", "");
        return text;
    }
}
