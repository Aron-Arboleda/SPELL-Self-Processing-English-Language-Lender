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
}







