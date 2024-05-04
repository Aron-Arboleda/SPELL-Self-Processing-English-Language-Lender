package com.spell.Logic;

public class SpaceAndLineRemover extends SPELLEditor {
    public SpaceAndLineRemover(String textToEdit) {
        super(textToEdit);
    }

    public String removeLineBreaks() {
        String text = super.excessSpaceRemover();
        String substring = text;
        sbEditor.append(text);


        while (substring.contains("\n")) {
            sbEditor.deleteCharAt(text.indexOf("\n"));
            substring = sbEditor.toString();
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }

    public String removeSpaces() {
        String text = super.getText();
        sbEditor.append(text);

        for(int i = 0; i < sbEditor.length(); i++) {
            if (Character.isWhitespace(sbEditor.charAt(i))) {
                sbEditor.deleteCharAt(i);
                i--;
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }
}
