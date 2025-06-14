package com.spell.GUI;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;

import org.languagetool.rules.RuleMatch;

import com.spell.Logic.GrammarAndSpellingFixer;

import java.awt.Color;
import java.util.ArrayList;

public class SPELLHighlightIndicators {
    Highlighter highlighter;
    ArrayList<DefaultHighlightPainter> painterList;
    GrammarAndSpellingFixer checker;
    JTextArea outputTextArea;
    String inputText;

    public SPELLHighlightIndicators(String inputText, JTextArea outputTextArea, GrammarAndSpellingFixer checker) {
        this.inputText = inputText;
        this.outputTextArea = outputTextArea;
        highlighter = outputTextArea.getHighlighter();
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
                int start = match.getFromPos();
                int end = match.getToPos();
                highlighter.addHighlight(start, end, painter);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
}
