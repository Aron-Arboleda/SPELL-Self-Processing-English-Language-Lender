package com.spell.Logic;

interface TextEdit {
    public void clear();

    public void setText(String text);

    public String getText();
}

public class SPELLEditor implements TextEdit {
    private String text;
    public StringBuilder sbEditor;

    public SPELLEditor(String textToEdit) {
        this.text = textToEdit;
        sbEditor = new StringBuilder();
    }

    public void clear() {
        this.text = "";
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static String excessSpaceRemover(StringBuilder sbEditor, String text) {
        sbEditor.append(text);

        int total = sbEditor.length();
        for (int i = 0; i < total; i++) {
            if (!(i == total - 1) && Character.isWhitespace(sbEditor.charAt(i))) {
                while (!(i == total - 1) && sbEditor.charAt(i) == '\n' && sbEditor.charAt(i + 1) == '\n') {
                    sbEditor.deleteCharAt(i + 1);
                    total--;
                }

                while (!(i == total - 1) && Character.isWhitespace(sbEditor.charAt(i + 1)) && sbEditor.charAt(i + 1) != '\n') {
                    sbEditor.deleteCharAt(i + 1);
                    total--;
                }

                if (!(i == total - 1) && (sbEditor.charAt(i + 1) == '.' || sbEditor.charAt(i + 1) == '?' ||
                    sbEditor.charAt(i + 1) == '!' || sbEditor.charAt(i + 1) == ',' ||
                    sbEditor.charAt(i + 1) == '"')) {
                    sbEditor.deleteCharAt(i);
                    total--;
                }
            }
        }

        if (Character.isWhitespace(sbEditor.charAt(0))) {
            sbEditor.deleteCharAt(0);
        }
        
        if (Character.isWhitespace(sbEditor.charAt(sbEditor.length() - 1))){
        sbEditor.deleteCharAt(sbEditor.length() - 1);
        }
        
        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }
}
