package com.spell.Logic;

import java.util.regex.*;

public class CaseConverter extends SPELLEditor {
    public CaseConverter(String textToEdit) {
        super(textToEdit);
    }

    public String upperCase() {
        String text = super.multipleSpaceRemover().toUpperCase();
        setText(text);
        return text;
    }

    public String lowerCase() {
        String text = super.multipleSpaceRemover().toLowerCase();
        setText(text);
        return text;
    }

    public String camelCasing() {
        String text = super.multipleSpaceRemover();
        sbEditor.append(text);

        /*Pattern pattern = Pattern.compile("\\w+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        int begin = 0;
        while (matcher.find(begin) == true) {
            if(matcher.end() + 1 < sbEditor.length()) {
                sbEditor.setCharAt(matcher.end() + 1, Character.toUpperCase(text.charAt(matcher.end() + 1)));
                begin = matcher.end();
            } else {
                break;
            }
        }*/

        for(int i = 0; i < sbEditor.length(); i++){
            if(Character.isWhitespace(sbEditor.charAt(i))){
                sbEditor.deleteCharAt(i);
                sbEditor.setCharAt(i, Character.toUpperCase(sbEditor.charAt(i)));
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }

    public String sentenceCase() {
        String text = super.multipleSpaceRemover();
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
            return text;
    }

    public String capitalizedCase() {
        String text = super.multipleSpaceRemover();
        sbEditor.append(text);

        sbEditor.setCharAt(0, Character.toUpperCase(sbEditor.charAt(0)));
        for(int i = 0; i < sbEditor.length(); i++){
            if(Character.isWhitespace(sbEditor.charAt(i))){
                sbEditor.setCharAt(i + 1, Character.toUpperCase(sbEditor.charAt(i + 1)));
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }
}
