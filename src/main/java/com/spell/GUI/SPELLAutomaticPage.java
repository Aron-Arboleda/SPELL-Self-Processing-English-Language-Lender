package com.spell.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.languagetool.rules.RuleMatch;

import com.spell.Logic.GrammarAndSpellingFixer;
import com.spell.Logic.SPELLBasicLogics;

public class SPELLAutomaticPage extends SPELLPage implements ActionListener {
    // Automatic Page Components
    SPELLAutoIconsPanel alphabetizerPanel, reverseAlphabetizerPanel, upperCasePanel, lowerCasePanel, camelCasingPanel,
            sentenceCasePanel, capitalizedCasePanel, removeLineBreaksPanel, removeSpacesPanel, bulletAdderPanel,
            bulletRemoverPanel;

    SPELLButton automaticBackButton, grammarCheckButton, copyButton;

    SPELLTextArea grammarAndSpellingArea;
    static GrammarAndSpellingFixer checker;

    static Image automaticEditorBackImage = (new ImageIcon(
            SPELLPage.class.getResource("/AutomaticEditorBackground.jpg"))).getImage();

    public SPELLAutomaticPage(String name, Color background) {
        super(name, background);

        this.setImage(automaticEditorBackImage);

        ImageIcon backButtonIcon = SPELLPage.newScaledImage("InkBackButtonIcon.png", 40, 25);

        automaticBackButton = new SPELLButton("", 15, new Color(0xF3F3F3), Color.white, "Return to Previous Page");
        automaticBackButton.removeBorder();
        automaticBackButton.setBounds(50, 35, 60, 30);
        automaticBackButton.setIcon(backButtonIcon);
        automaticBackButton.addActionListener(this);

        alphabetizerPanel = new SPELLAutoIconsPanel("Sort Alphabetically");
        alphabetizerPanel.setBounds(60, 120, 150, 150);
        reverseAlphabetizerPanel = new SPELLAutoIconsPanel("Reverse Alphabet");
        reverseAlphabetizerPanel.setBounds(230, 120, 150, 150);

        upperCasePanel = new SPELLAutoIconsPanel("Upper Case");
        upperCasePanel.setBounds(820, 100, 90, 90);
        lowerCasePanel = new SPELLAutoIconsPanel("Lower Case");
        lowerCasePanel.setBounds(930, 100, 90, 90);
        camelCasingPanel = new SPELLAutoIconsPanel("Camel Casing");
        camelCasingPanel.setBounds(1040, 100, 90, 90);
        sentenceCasePanel = new SPELLAutoIconsPanel("Sentence Case");
        sentenceCasePanel.setBounds(870, 210, 90, 90);
        capitalizedCasePanel = new SPELLAutoIconsPanel("Capitalized Case");
        capitalizedCasePanel.setBounds(980, 210, 90, 90);

        removeLineBreaksPanel = new SPELLAutoIconsPanel("Remove Line Breaks");
        removeLineBreaksPanel.setBounds(830, 430, 140, 140);
        removeSpacesPanel = new SPELLAutoIconsPanel("Remove Spaces");
        removeSpacesPanel.setBounds(1000, 430, 140, 140);

        bulletAdderPanel = new SPELLAutoIconsPanel("Add Bullets");
        bulletAdderPanel.setBounds(60, 430, 150, 150);
        bulletRemoverPanel = new SPELLAutoIconsPanel("Remove Bullets");
        bulletRemoverPanel.setBounds(230, 430, 150, 150);

        grammarAndSpellingArea = new SPELLTextArea(new Font("Segoe UI", Font.PLAIN, 15), new Insets(5, 5, 5, 5),
                Color.WHITE, Color.BLACK);
        grammarAndSpellingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (checker != null) {
                    int position = grammarAndSpellingArea.viewToModel2D(e.getPoint());
                    for (int i = 0; i < checker.matches.size(); i++) {
                        RuleMatch newMatch = checker.matches.get(i);
                        if (position >= newMatch.getFromPos() && position <= newMatch.getToPos()) {
                            grammarAndSpellingArea
                                    .setComponentPopupMenu(
                                            SPELLTextArea.createCustomContextMenu(grammarAndSpellingArea, newMatch, "automatic"));
                            grammarAndSpellingArea.select(newMatch.getFromPos(), newMatch.getToPos());
                            grammarAndSpellingArea.getComponentPopupMenu().show(grammarAndSpellingArea, e.getX(),
                                    e.getY());
                            break;
                        }
                    }
                }
            }
        });

        JScrollPane grammarAndSpellingAreaPane = ScrollPaneFactory.newScrollPane(grammarAndSpellingArea);
        grammarAndSpellingAreaPane.setBounds(485, 155, 240, 340);
        grammarAndSpellingAreaPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        grammarAndSpellingAreaPane.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 4, 5, 2, true));

        grammarCheckButton = new SPELLButton("Check Grammar", 12, Color.white, Color.black, "Check Grammar");
        grammarCheckButton.setBounds(485, 495, 100, 30);
        grammarCheckButton.addActionListener(this);
        copyButton = new SPELLButton("Copy", 12, Color.white, Color.black, "Copy");
        copyButton.setBounds(675, 495, 50, 30);
        copyButton.addActionListener(this);

        this.add(automaticBackButton);

        this.add(alphabetizerPanel);
        this.add(reverseAlphabetizerPanel);

        this.add(upperCasePanel);
        this.add(lowerCasePanel);
        this.add(camelCasingPanel);
        this.add(sentenceCasePanel);
        this.add(capitalizedCasePanel);

        this.add(removeLineBreaksPanel);
        this.add(removeSpacesPanel);

        this.add(bulletAdderPanel);
        this.add(bulletRemoverPanel);

        this.add(grammarAndSpellingAreaPane);
        this.add(grammarCheckButton);
        this.add(copyButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == automaticBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        } else if (e.getSource() == copyButton) {
            SPELLBasicLogics.copyFromTextArea(grammarAndSpellingArea);
        } else if (e.getSource() == grammarCheckButton) {
            SPELLManualPage.refreshLanguageToolChecker(grammarAndSpellingArea.getText(), grammarAndSpellingArea, "automatic");
        }
    }
}
