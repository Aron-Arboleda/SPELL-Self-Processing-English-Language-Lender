package com.spell.GUI;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;

import org.languagetool.rules.RuleMatch;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SPELLLineIndicators {
    Highlighter highlighter;
    DefaultHighlightPainter painter;
    List<RuleMatch> matches;
    JTextArea textArea;

    public SPELLLineIndicators(JTextArea textArea, List<RuleMatch> matches) {
        this.textArea = textArea; 
        highlighter = textArea.getHighlighter();
        this.matches = matches;
    }

    public void showHighlights() throws BadLocationException {
        for (RuleMatch match : matches) {
            int start = textArea.getLineStartOffset(match.getFromPos()); 
            int end = textArea.getLineEndOffset(match.getToPos());
            painter = new DefaultHighlightPainter(Color.RED);
            highlighter.addHighlight(start, end, painter);
        }
    }
}
