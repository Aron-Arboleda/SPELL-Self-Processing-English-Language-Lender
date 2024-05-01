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

        Pattern pattern = Pattern.compile("\\\\b\\\\w+\\\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        int begin = 0;
        do {
            if (matcher.find(begin)) {
                sbEditor.setCharAt(matcher.end() + 1, Character.toUpperCase(text.charAt(matcher.end() + 1)));
            }
            begin = matcher.end() + 1;
        } while(matcher.hitEnd());

        text = sbEditor.toString();
        return text;
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
