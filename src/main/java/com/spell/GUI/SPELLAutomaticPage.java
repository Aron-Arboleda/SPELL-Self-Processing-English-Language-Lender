package com.spell.GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.languagetool.rules.RuleMatch;

import com.spell.Logic.ClipboardListener;
import com.spell.Logic.GrammarAndSpellingFixer;

public class SPELLAutomaticPage extends SPELLPage implements ActionListener {
    // Automatic Page Components
    SPELLAutoIconsPanel alphabetizerPanel, reverseAlphabetizerPanel, upperCasePanel, lowerCasePanel, camelCasingPanel,
            sentenceCasePanel, capitalizedCasePanel, removeLineBreaksPanel, removeSpacesPanel, bulletAdderPanel,
            bulletRemoverPanel;

    SPELLButton automaticBackButton, grammarCheckButton, copyButton, resetButton, instructionButton;
    JComboBox<String> bulletDesignToAddCB, bulletDesignToRemoveCB;

    SPELLTextArea grammarAndSpellingArea;
    static GrammarAndSpellingFixer checker;

    static Image automaticEditorBackImage = (new ImageIcon(
            SPELLPage.class.getResource("/images/AutomaticEditorBackground.jpg"))).getImage();

    public SPELLAutomaticPage(String name, Color background) {
        super(name, background);

        this.setImage(automaticEditorBackImage);

        ImageIcon backButtonIcon = SPELLPage.newScaledImage("InkBackButtonIcon.png", 40, 25);

        automaticBackButton = new SPELLButton("", 15, new Color(0xF3F3F3), Color.white, "Return to Previous Page");
        automaticBackButton.removeBorder();
        automaticBackButton.setBounds(50, 35, 60, 30);
        automaticBackButton.setIcon(backButtonIcon);
        automaticBackButton.addActionListener(this);

        alphabetizerPanel = new SPELLAutoIconsPanel("Sort Alphabetically", 150, 150, "sortAlphabeticalIcon.png");
        alphabetizerPanel.setBounds(60, 120, 150, 150);
        alphabetizerPanel.iconToggleButton.addActionListener(this);
        reverseAlphabetizerPanel = new SPELLAutoIconsPanel("Reverse Alphabet", 150, 150, "reverseAlphabeticalIcon.png");
        reverseAlphabetizerPanel.setBounds(230, 120, 150, 150);
        reverseAlphabetizerPanel.iconToggleButton.addActionListener(this);

        upperCasePanel = new SPELLAutoIconsPanel("Upper Case", 90, 90, "upperCaseIcon.png");
        upperCasePanel.setBounds(820, 100, 90, 90);
        upperCasePanel.iconToggleButton.addActionListener(this);
        lowerCasePanel = new SPELLAutoIconsPanel("Lower Case", 90, 90, "lowerCaseIcon.png");
        lowerCasePanel.setBounds(930, 100, 90, 90);
        lowerCasePanel.iconToggleButton.addActionListener(this);
        camelCasingPanel = new SPELLAutoIconsPanel("Camel Casing", 90, 90, "camelCasingIcon.png");
        camelCasingPanel.setBounds(1040, 100, 90, 90);
        camelCasingPanel.iconToggleButton.addActionListener(this);
        sentenceCasePanel = new SPELLAutoIconsPanel("Sentence Case", 90, 90, "sentenceCaseIcon.png");
        sentenceCasePanel.setBounds(870, 210, 90, 90);
        sentenceCasePanel.iconToggleButton.addActionListener(this);
        capitalizedCasePanel = new SPELLAutoIconsPanel("Capitalized Case", 90, 90, "capitalizedCaseIcon.png");
        capitalizedCasePanel.setBounds(980, 210, 90, 90);
        capitalizedCasePanel.iconToggleButton.addActionListener(this);

        removeLineBreaksPanel = new SPELLAutoIconsPanel("Remove Line Breaks", 140, 140, "lineBreaksRemoverIcon.png");
        removeLineBreaksPanel.setBounds(830, 430, 140, 140);
        removeLineBreaksPanel.iconToggleButton.addActionListener(this);
        removeSpacesPanel = new SPELLAutoIconsPanel("Remove Spaces", 140, 140, "spaceRemoverIcon.png");
        removeSpacesPanel.setBounds(1000, 430, 140, 140);
        removeSpacesPanel.iconToggleButton.addActionListener(this);

        bulletAdderPanel = new SPELLAutoIconsPanel("Add Bullets", 150, 150, "bulletAdderIcon.png");
        bulletAdderPanel.setBounds(60, 430, 150, 150);
        bulletAdderPanel.iconToggleButton.addActionListener(this);

        String[] bulletCBOptions = { "a.)", "1.", "•", "-", "▪", "▫", "◦", "◆", "◇", "◈", "✓" };
        bulletDesignToAddCB = new JComboBox<String>(bulletCBOptions);
        bulletDesignToAddCB.setPreferredSize(new Dimension(50, 25));
        bulletDesignToAddCB.setFocusable(false);
        bulletDesignToAddCB.setBackground(Color.white);

        bulletAdderPanel.taskBar.remove(bulletAdderPanel.iconToggleButton);
        bulletAdderPanel.taskBar.add(bulletDesignToAddCB);
        bulletAdderPanel.taskBar.add(bulletAdderPanel.iconToggleButton);

        bulletRemoverPanel = new SPELLAutoIconsPanel("Remove Bullets", 150, 150, "bulletRemoverIcon.png");
        bulletRemoverPanel.setBounds(230, 430, 150, 150);
        bulletRemoverPanel.iconToggleButton.addActionListener(this);

        bulletDesignToRemoveCB = new JComboBox<String>(bulletCBOptions);
        bulletDesignToRemoveCB.setPreferredSize(new Dimension(50, 25));
        bulletDesignToRemoveCB.setFocusable(false);
        bulletDesignToRemoveCB.setBackground(Color.white);
        bulletRemoverPanel.taskBar.remove(bulletRemoverPanel.iconToggleButton);
        bulletRemoverPanel.taskBar.add(bulletDesignToRemoveCB);
        bulletRemoverPanel.taskBar.add(bulletRemoverPanel.iconToggleButton);

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
                                            SPELLTextArea.createCustomContextMenu(grammarAndSpellingArea, newMatch,
                                                    "automatic"));
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
        resetButton = new SPELLButton("Reset", 12, Color.white, Color.black, "Reset Text Area");
        resetButton.setBounds(615, 495, 50, 30);
        resetButton.addActionListener(this);
        copyButton = new SPELLButton("Copy", 12, Color.white, Color.black, "Copy");
        copyButton.setBounds(675, 495, 50, 30);
        copyButton.addActionListener(this);

        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(new Color(0xF7DBA7));
        instructionPanel.setBounds(60, 500, 560, 100);
        instructionPanel.setVisible(false);

        instructionButton = new SPELLButton("i", 15, Color.WHITE, Color.BLACK, "Instructions");
        instructionButton.setFont(new Font("Georgia", Font.PLAIN, 15));
        instructionButton.setBounds(60, 600, 30, 30);
        // instructionButton.addActionListener(this);
        instructionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                instructionPanel.setVisible(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                instructionPanel.setVisible(false);
            }
        });

        String instructionsString = "<html>Users can only choose one automatic editing feature to toggle on. When the panel is on,<br> all the future text that will be copied will be edited based on the activated editing feature.<br> The output will be added to the clipboard so when you paste after copying the text, the<br> edited text will be pasted.</html>";
        SPELLLabel exampleLabel = new SPELLLabel(instructionsString, 5, Color.WHITE);
        exampleLabel.convertToParag();
        exampleLabel.setForeground(Color.BLACK);
        instructionPanel.add(exampleLabel);

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
        this.add(resetButton);
        this.add(copyButton);

        this.add(instructionButton);
        this.add(instructionPanel, JLayeredPane.POPUP_LAYER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == automaticBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        } else if (e.getSource() == copyButton) {
            ClipboardListener.copyFromTextArea(grammarAndSpellingArea);
        } else if (e.getSource() == resetButton) {
            grammarAndSpellingArea.setText("");
            grammarAndSpellingArea.setEditable(true);
        } else if (e.getSource() == grammarCheckButton) {
            grammarAndSpellingArea.setEditable(false);
            SPELLManualPage.refreshLanguageToolChecker(grammarAndSpellingArea.getText(), grammarAndSpellingArea,
                    "automatic");
        } else if (e.getSource() instanceof JToggleButton) {
            ClipboardListener clip = null;
            if (e.getSource() == upperCasePanel.iconToggleButton) {
                clip = new ClipboardListener("upperCase", upperCasePanel.iconToggleButton);
            } else if (e.getSource() == lowerCasePanel.iconToggleButton) {
                clip = new ClipboardListener("lowerCase", lowerCasePanel.iconToggleButton);
            } else if (e.getSource() == camelCasingPanel.iconToggleButton) {
                clip = new ClipboardListener("camelCasing", camelCasingPanel.iconToggleButton);
            } else if (e.getSource() == sentenceCasePanel.iconToggleButton) {
                clip = new ClipboardListener("sentenceCase", sentenceCasePanel.iconToggleButton);
            } else if (e.getSource() == capitalizedCasePanel.iconToggleButton) {
                clip = new ClipboardListener("capitalizedCase", capitalizedCasePanel.iconToggleButton);
            } else if (e.getSource() == removeLineBreaksPanel.iconToggleButton) {
                clip = new ClipboardListener("removeLineBreaks", removeLineBreaksPanel.iconToggleButton);
            } else if (e.getSource() == removeSpacesPanel.iconToggleButton) {
                clip = new ClipboardListener("removeSpaces", removeSpacesPanel.iconToggleButton);
            } else if (e.getSource() == alphabetizerPanel.iconToggleButton) {
                clip = new ClipboardListener("sortAlphabetically", alphabetizerPanel.iconToggleButton);
            } else if (e.getSource() == reverseAlphabetizerPanel.iconToggleButton) {
                clip = new ClipboardListener("sortReverseAlphabetically", reverseAlphabetizerPanel.iconToggleButton);
            } else if (e.getSource() == bulletAdderPanel.iconToggleButton) {
                clip = new ClipboardListener("addBullets", bulletAdderPanel.iconToggleButton,
                        bulletDesignToAddCB.getSelectedItem().toString());
            } else if (e.getSource() == bulletRemoverPanel.iconToggleButton) {
                clip = new ClipboardListener("removeBullets", bulletRemoverPanel.iconToggleButton,
                        bulletDesignToRemoveCB.getSelectedItem().toString());
            } else {
                System.out.println("JToggleButton Doesn't exist.");
                return;
            }
            clip.start();
        }
    }
}
