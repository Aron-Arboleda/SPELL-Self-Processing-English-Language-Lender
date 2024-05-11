package com.spell.Logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BulletsEditor extends PerLineEditor {
    public String bulletDesign;
    public BulletsEditor(String textToEdit, String bulletDesign) {
        super(textToEdit);
        this.bulletDesign = bulletDesign;
    }

    public String addBullets() {
        String text = super.excessSpaceRemover();
        text = text.replaceAll("\n", "\n" + "## ");

        if (bulletDesign.equals("a.)")) {
            int i = 98;
            int y = 0;
            char letter, letter2;

            while (text.contains("##")) {
                if (y == 0) {
                    letter = (char) i;
                    text = text.replaceFirst("##", letter + ".)");
                    i++;

                    if (i == 123){
                        y = 65;
                        i = 97;
                    }
                } else {
                    letter2 = (char) y;
                    letter = (char) i;
                    text = text.replaceFirst("##", letter2 + "" + letter + ".)");
                    i++;

                    if (i == 123) {
                        y++;
                        i = 97;
                    }

                    if (y == 91) {
                        i = 97;
                        y = 0;
                    }
                }
            }
        }

        sbEditor.append(text);
        sbEditor.insert(0, bulletDesign + " ");
        super.setText(sbEditor.toString());
        sbEditor.delete(0, sbEditor.length());
        return super.getText();
    }

    public String removeBullets() {
        super.setText(super.excessSpaceRemover());
        if (!(super.getText().contains(bulletDesign + " "))){
            return super.getText();
        }
        super.setText(super.getText().replaceAll(bulletDesign + " ", ""));
        return super.getText();
    }

    public static void callMethod(BulletsEditor instance, String methodName, String input) {
        try {
            Method method = BulletsEditor.class.getMethod(methodName);
            method.invoke(instance);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
