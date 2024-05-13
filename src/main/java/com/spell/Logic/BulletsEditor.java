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
        text = text.replaceAll("\n", "\n" + bulletDesign + " ");

        if (bulletDesign.equals("a.)")) {
            text = text.replaceAll("a\\.\\)", "##");

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
                        y = 0;
                    }
                }
            }
        } else if (bulletDesign.equals("1.")) {
            text = text.replaceAll("1\\.", "##");
            int i = 2;

            while (text.contains("##")) {
                text = text.replaceFirst("##", i + ".");
                i++;
            }
        }

        sbEditor.append(text);
        sbEditor.insert(0, bulletDesign + " ");
        super.setText(sbEditor.toString());
        sbEditor.delete(0, sbEditor.length());
        return super.getText();
    }

    public String removeBullets() {
        String text = super.excessSpaceRemover();
        /* if (!(text.contains(bulletDesign + " "))){
            super.setText(text);
            return super.getText();
        } */

        if (bulletDesign.equals("a.)")){
            text = text.replaceAll("a\\.\\) ", "");
            text = text.replaceAll("\n" + ".*" + "\\.\\) ", "\n");
        }
        else if (bulletDesign.equals("1.")) {
            for (int i = 1; i < text.split("\\n").length + 1; i++){
                text = text.replaceAll(i + ". ", "");
            }
        } else {
            text = text.replaceAll(bulletDesign, "");
        }

        super.setText(text);
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
