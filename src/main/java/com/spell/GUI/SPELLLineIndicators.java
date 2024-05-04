package com.spell.GUI;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;

import org.languagetool.rules.RuleMatch;

import com.spell.Logic.GrammarAndSpellingFixer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SPELLLineIndicators {
    Highlighter highlighter;
    ArrayList<DefaultHighlightPainter> painterList;
    GrammarAndSpellingFixer checker;
    JTextArea outputtextArea;
    String inputText;

    public SPELLLineIndicators(String inputText, JTextArea outputtextArea, GrammarAndSpellingFixer checker) {
        this.inputText = inputText;
        this.outputtextArea = outputtextArea;
        highlighter = outputtextArea.getHighlighter();
        this.checker = checker;
        painterList = new ArrayList<DefaultHighlightPainter>();
        painterList.add(new DefaultHighlightPainter(Color.RED));
        painterList.add(new DefaultHighlightPainter(Color.BLUE));
    }

    public void showHighlights() {
        for (RuleMatch match : checker.matches) {
            try {
                DefaultHighlightPainter painter;
                if (match.getRule().getCategory().getId().toString().equals("TYPOS")) {
                    painter = painterList.get(0);
                } else {
                    painter = painterList.get(1);
                }
                highlighter.addHighlight(match.getFromPos(), match.getToPos(), painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
}
