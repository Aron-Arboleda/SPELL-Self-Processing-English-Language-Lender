package com.spell.Logic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.CategoryId;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.tools.Tools;




public class GrammarAndSpellingFixer extends SPELLEditor {
    Language language = new AmericanEnglish();
    JLanguageTool langTool = new JLanguageTool(language);
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
            matches = langTool.check(textToCheck);
            for (RuleMatch match : matches) {
                super.sbEditor.append("\nPotential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage() + "\n\nSuggested correction(s): " + match.getSuggestedReplacements() + "\n Rule: " + match.getRule().getCategory().getId());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(super.sbEditor.toString());

        super.setText(textToCheck);
        return textToCheck;
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

