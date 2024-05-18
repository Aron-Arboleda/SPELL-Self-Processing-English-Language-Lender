package com.spell.Logic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.languagetool.rules.RuleMatch;

import com.spell.GUI.SPELLFrame;
import com.spell.GUI.SPELLHighlightIndicators;




public class GrammarAndSpellingFixer extends SPELLEditor {
    //Language language = new AmericanEnglish();
    //JLanguageTool langTool = new JLanguageTool(language);
    public List<RuleMatch> matches;
    public GrammarAndSpellingFixer(String textToEdit) {
        super(textToEdit);
    }

    public String buildGrammarAndSpellingChecker() {
        String textToCheck = super.getText();
        try {
            //Set<CategoryId> categorySet = new LinkedHashSet<>();
            //categorySet.add(new CategoryId("CASING"));
            //langTool.disableRule("CASING");
            matches = SPELLFrame.langTool.check(textToCheck);
            for (RuleMatch match : matches) {
                super.sbEditor.append("\nPotential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage() + "\n\nSuggested correction(s): " + match.getSuggestedReplacements() + "\n Rule: " + match.getRule().getCategory().getId());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(super.sbEditor.toString());

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

class MatchedErrors {
    RuleMatch match;
    ArrayList<Integer> indexEndPoints;

    public MatchedErrors(RuleMatch match, ArrayList<Integer> indexEndPoints) {
        this.match = match;
        this.indexEndPoints = indexEndPoints;
    }
}

