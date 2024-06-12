package com.spell.Logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CaseConverter extends SPELLEditor {
    public CaseConverter(String textToEdit) {
        super(textToEdit);
    }

    public String upperCase() {
        String text = excessSpaceRemover(sbEditor, super.getText()).toUpperCase();
        setText(text);
        return text;
    }

    public String lowerCase() {
        String text = excessSpaceRemover(sbEditor, super.getText()).toLowerCase();
        setText(text);
        return text;
    }

    public String camelCasing() {
        String text = excessSpaceRemover(sbEditor, super.getText()).toLowerCase();
        sbEditor.append(text);

        int total = sbEditor.length();
        for(int i = 0; i < total; i++){
            if(sbEditor.charAt(i) == ' '){
                sbEditor.deleteCharAt(i);
                sbEditor.setCharAt(i, Character.toUpperCase(sbEditor.charAt(i)));
                total--;
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        setText(text);
        return text;
    }

    public String sentenceCase() {
        String text = excessSpaceRemover(sbEditor, super.getText()).toLowerCase();
        sbEditor.append(text);

        sbEditor.setCharAt(0, Character.toUpperCase(sbEditor.charAt(0)));
        for(int i = 0; i < sbEditor.length(); i++) {
            if ((i != sbEditor.length() - 1) && (sbEditor.charAt(i) == '.' || sbEditor.charAt(i) == '?' || sbEditor.charAt(i) == '!')) {
                sbEditor.setCharAt(i + 2, Character.toUpperCase(sbEditor.charAt(i + 2)));
                i = i + 3;
            }
        }
            text = sbEditor.toString();
            sbEditor.delete(0, sbEditor.length());
            setText(text);
            return text;
    }

    public String capitalizedCase() {
        String text = excessSpaceRemover(sbEditor, super.getText()).toLowerCase();
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

    public static void callMethod(CaseConverter instance, String methodName, String input) {
        try {
            Method method = CaseConverter.class.getMethod(methodName);
            method.invoke(instance);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
