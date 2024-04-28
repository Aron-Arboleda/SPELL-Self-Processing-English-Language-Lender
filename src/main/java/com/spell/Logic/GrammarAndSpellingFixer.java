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
            // Create a JLanguageTool object for English (British)
            Language language = new AmericanEnglish();
            JLanguageTool langTool = new JLanguageTool(language);

            // Uncomment the line below to activate statistical n-gram data
            // langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));

            // Text to check
            String textToCheck = super.getText();

            // Check the text
            
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
