package com.spell.Logic;

public class PerLineEditor extends SPELLEditor {
    public String[] parsedText;
    public PerLineEditor(String textToEdit) {
        super(textToEdit);
        if (textToEdit.contains("\n")){
            setText(excessSpaceRemover(sbEditor, super.getText()));
            parsedText = getText().split("\n");
            // StringBuilder sbNewString = new StringBuilder();
            // for (int i = 0; i < parsedText.length; i++) {
            //     if (i != parsedText.length - 1) {
            //         sbNewString.append(SPELLEditor.excessSpaceRemover(sbEditor, parsedText[i]) + "\n");
            //     }
            // }
            // setText(textToEdit);
        } else {
            parsedText[0] = textToEdit;
        }
    }
}

