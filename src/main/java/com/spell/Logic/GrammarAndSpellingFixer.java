package com.spell.Logic;
import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;



public class GrammarAndSpellingFixer extends SPELLEditor {
    
    public GrammarAndSpellingFixer(String textToEdit) {
        super(textToEdit);
    }

    public String fixGrammarAndSpelling() {
        StringBuilder sb = new StringBuilder();
        try {

            Language language = new AmericanEnglish();
            JLanguageTool langTool = new JLanguageTool(language);

            String textToCheck = super.getText();

              
            
            List<RuleMatch> matches = langTool.check(textToCheck);
            for (RuleMatch match : matches) {
                sb.append("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage() + "\n\nSuggested correction(s): " + match.getSuggestedReplacements());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setText(sb.toString());
        return sb.toString();
    }
}
