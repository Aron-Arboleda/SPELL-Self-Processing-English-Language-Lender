package com.spell.Logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PerLineEditor extends SPELLEditor {
    public String[] parsedText;
    public PerLineEditor(String textToEdit) {
        super(textToEdit);
        parsedText = textToEdit.split("\n");
    }
}

