package com.spell.Logic;

public class SpaceAndLineRemover extends SPELLEditor {
    public SpaceAndLineRemover(String textToEdit) {
        super(textToEdit);
    }

    public String removeLineBreaks() {
        super.setText(super.excessSpaceRemover());
        super.setText(super.getText().replaceAll("\n", " "));
        return super.getText();
    }

    public String removeSpaces() {
        String text = super.getText();
        super.setText(text.replaceAll("\n", ""));
        super.setText(text.replaceAll(" ", ""));
        return text;
    }
}
