package com.spell.Logic;

import java.util.regex.*;

public class CaseConverter extends SPELLEditor {
    public CaseConverter(String textToEdit) {
        super(textToEdit);
    }

    public String upperCase() {
        String text = super.excessSpaceRemover().toUpperCase();
        setText(text);
        return text;
    }

    public String lowerCase() {
        String text = super.excessSpaceRemover().toLowerCase();
        setText(text);
        return text;
    }

    public String camelCasing() {
        String text = super.excessSpaceRemover();
        sbEditor.append(text);

        sbEditor.setCharAt(0, Character.toLowerCase(sbEditor.charAt(0)));

        for(int i = 0; i < sbEditor.length(); i++){
            if(Character.isWhitespace(sbEditor.charAt(i))){
                sbEditor.deleteCharAt(i);
                sbEditor.setCharAt(i, Character.toUpperCase(sbEditor.charAt(i)));
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        setText(text);
        return text;
    }

    public String sentenceCase() {
        String text = super.excessSpaceRemover();
        sbEditor.append(text);

        sbEditor.setCharAt(0, Character.toUpperCase(sbEditor.charAt(0)));
        for(int i = 0; i < sbEditor.length(); i++) {
            if ((i != sbEditor.length() - 1) && (sbEditor.charAt(i) == '.' || sbEditor.charAt(i) == '?' || sbEditor.charAt(i) == '!')) {
                if(Character.isWhitespace(sbEditor.charAt(i + 1))) {
                    i++;
                }
                sbEditor.setCharAt(i + 1, Character.toUpperCase(sbEditor.charAt(i + 1)));
            }
        }
            text = sbEditor.toString();
            sbEditor.delete(0, sbEditor.length());
            setText(text);
            return text;
    }

    public String capitalizedCase() {
        String text = super.excessSpaceRemover();
        sbEditor.append(text);

        sbEditor.setCharAt(0, Character.toUpperCase(sbEditor.charAt(0)));
        for(int i = 0; i < sbEditor.length(); i++){
            if(Character.isWhitespace(sbEditor.charAt(i))){
                sbEditor.setCharAt(i + 1, Character.toUpperCase(sbEditor.charAt(i + 1)));
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        setText(text);
        return text;
    }
}
