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

        if (matcher.find()) {
            sbEditor.setCharAt(matcher.end(), Character.toUpperCase(text.charAt(matcher.end())));
        }

        do {
            if (matcher.find(matcher.end())) {
                sbEditor.setCharAt(matcher.end(), Character.toUpperCase(text.charAt(matcher.end())));
            }
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
