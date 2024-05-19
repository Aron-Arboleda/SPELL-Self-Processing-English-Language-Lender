package com.spell.Logic;

import java.util.List;
import javax.swing.JTextArea;

import org.languagetool.rules.RuleMatch;

import com.spell.GUI.SPELLFrame;
import com.spell.GUI.SPELLHighlightIndicators;

public class GrammarAndSpellingFixer extends SPELLEditor {
    public List<RuleMatch> matches;
    public GrammarAndSpellingFixer(String textToEdit) {
        super(textToEdit);
    }

    public String buildGrammarAndSpellingChecker() {
        String textToCheck = super.getText();
        try {
            matches = SPELLFrame.langTool.check(textToCheck);
            for (RuleMatch match : matches) {
                super.sbEditor.append("\nPotential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage() + "\n\nSuggested correction(s): " + match.getSuggestedReplacements() + "\n Rule: " + match.getRule().getCategory().getId());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.setText(textToCheck);
        return textToCheck;
    }

    public void refreshLanguageToolChecker(String inputText, JTextArea outputTextArea) {
        GrammarAndSpellingFixer checker = new GrammarAndSpellingFixer(inputText);
        checker.buildGrammarAndSpellingChecker();
        SPELLHighlightIndicators highlightErrors = new SPELLHighlightIndicators(inputText, outputTextArea, this);
        outputTextArea.setText(inputText);
        highlightErrors.showHighlights();
    }
}