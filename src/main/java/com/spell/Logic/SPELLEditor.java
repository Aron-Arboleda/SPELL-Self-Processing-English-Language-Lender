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

    public String excessSpaceRemover() {
        sbEditor.append(text);

        for(int i = 0; i < sbEditor.length(); i++) {
            if (Character.isWhitespace(sbEditor.charAt(i))) {
                while (!(i == sbEditor.length() - 1) && Character.isWhitespace(sbEditor.charAt(i + 1))) {
                    sbEditor.deleteCharAt(i + 1);
                }

                if (!(i == sbEditor.length() - 1) && (sbEditor.charAt(i + 1) == '.' || sbEditor.charAt(i + 1) == '?' || sbEditor.charAt(i + 1) == '!' || sbEditor.charAt(i + 1) == ',' || sbEditor.charAt(i + 1) == '"')) {
                    sbEditor.deleteCharAt(i);
                }
            }
        }

        if (Character.isWhitespace(sbEditor.charAt(0))){
            sbEditor.deleteCharAt(0);
        }
        if (Character.isWhitespace(sbEditor.charAt(sbEditor.length() - 1))){
            sbEditor.deleteCharAt(sbEditor.length() - 1);
        }

        /* while (Character.isWhitespace(sbEditor.charAt(sbEditor.length() - 1))){
            sbEditor.deleteCharAt(sbEditor.length() - 1);
        } */

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }
}







