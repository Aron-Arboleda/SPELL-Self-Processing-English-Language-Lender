package com.spell.Logic;

import java.util.regex.*;

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
        String text = super.getText().toLowerCase();
        setText(text);
        return text;
    }

    public String camelCasing() {
        String text = super.getText();
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
        String text = super.getText();
        sbEditor.append(text);

        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == '.'){
                i++;
                /* while(text.charAt(i) == ' '){
                    i++;
                } */
                sbEditor.setCharAt(i, Character.toUpperCase(sbEditor.charAt(i)));
            } else if (text.charAt(i) == '?'){
                i++;
               /* while(text.charAt(i) == ' '){
                    i++;
                } */
                sbEditor.setCharAt(i, Character.toUpperCase(sbEditor.charAt(i)));
            } else if (text.charAt(i) == '!'){
                i++;
                /* while(text.charAt(i) == ' '){
                    i++;
                } */
                sbEditor.setCharAt(i, Character.toUpperCase(sbEditor.charAt(i)));
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }

    public String capitalizedCase() {
        String text = super.getText();
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
