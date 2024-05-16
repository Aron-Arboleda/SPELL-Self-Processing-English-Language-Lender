package com.spell.Logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SpaceAndLineRemover extends SPELLEditor {
    public SpaceAndLineRemover(String textToEdit) {
        super(textToEdit);
    }

    public String removeLineBreaks() {
        super.setText(super.excessSpaceRemover());
        super.setText(super.getText().replaceAll("\r\n", ""));
        super.setText(super.getText().replaceAll("\n", " "));
        return super.getText();
    }

    public String removeSpaces() {
        String text = super.getText();
        //super.setText(text.replaceAll("\n", ""));
        super.setText(text.replaceAll(" ", ""));
        return super.getText();
    }

    public static void callMethod(SpaceAndLineRemover instance, String methodName, String input) {
        try {
            Method method = SpaceAndLineRemover.class.getMethod(methodName);
            method.invoke(instance);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
