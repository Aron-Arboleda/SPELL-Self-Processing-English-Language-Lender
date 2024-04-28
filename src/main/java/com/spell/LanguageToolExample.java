package com.spell;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.Languages;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

public class LanguageToolExample {

    public void fixGrammar() {
        try {
            // Create a JLanguageTool object for English (British)
            Language language = new AmericanEnglish();
            JLanguageTool langTool = new JLanguageTool(language);

            // Uncomment the line below to activate statistical n-gram data
            // langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));

            // Text to check
            String textToCheck = "A sentence with a error in the Hitchhiker's Guide to the Galaxy";
            System.out.println("Text to Check: " + textToCheck);

            // Check the text
        
            List<RuleMatch> matches = langTool.check(textToCheck);
            for (RuleMatch match : matches) {
                System.out.println("Potential error at characters " + match.getFromPos() + "-" + match.getToPos() + ": " + match.getMessage());
                System.out.println("Suggested correction(s): " + match.getSuggestedReplacements());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
