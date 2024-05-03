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

    public String multipleSpaceRemover() {
        sbEditor.append(text);

        while (Character.isWhitespace(sbEditor.charAt(0))){
            sbEditor.deleteCharAt(0);
        }

        for(int i = 0; i < sbEditor.length(); i++) {
            if (Character.isWhitespace(sbEditor.charAt(i))) {
                while (Character.isWhitespace(sbEditor.charAt(i + 1))) {
                    sbEditor.deleteCharAt(i + 1);
                }
            }
        }

        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());
        return text;
    }
}







