package com.spell.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.spell.Logic.*;
import org.languagetool.rules.RuleMatch;

public class SPELLManualPage extends SPELLPage implements ActionListener {
    // Manual Page Components
    static SPELLTextArea inputTextArea, outputTextArea;
    SPELLComboBox grammarComboBox, casingComboBox, spaceRemoverComboBox, alphabetizerComboBox;
    SPELLComboBox bulletDesignToAddCB, bulletDesignToRemoveCB;
    FakeComboBox bulletEditorComboBox;
    ArrayList<SPELLComboBox> comboBoxes = new ArrayList<SPELLComboBox>();
    SPELLButton clearButton, copyButton, instructionButton;
    SPELLButton bulletAdderEditButton, bulletRemoveEditButton;

    static Image manualEditorBackImage = (new ImageIcon(
            SPELLPage.class.getResource("/images/ManualEditorBackground.jpg")))
            .getImage();

    SPELLButton manualButton, manualBackButton;

    static GrammarAndSpellingFixer checker;

    public SPELLManualPage(String name, Color background) {
        super(name, background);

        this.setImage(manualEditorBackImage);

        ImageIcon backButtonIcon = SPELLPage.newScaledImage("ChalkBackButtonIcon.png", 60, 35);

        manualBackButton = new SPELLButton("", 15, new Color(0x181C20), Color.white, "Return to Previous Page");
        manualBackButton.removeBorder();
        manualBackButton.setBounds(50, 35, 80, 40);
        manualBackButton.setIcon(backButtonIcon);
        manualBackButton.addActionListener(this);

        inputTextArea = new SPELLTextArea(new Font("Segoe UI Emoji", Font.PLAIN, 15), new Insets(5, 5, 5, 5),
                new Color(0x22252A), Color.WHITE);

        JScrollPane inputTextAreaPane = ScrollPaneFactory.newScrollPane(inputTextArea);
        inputTextAreaPane.setBounds(410, 105, 340, 460);
        inputTextAreaPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        outputTextArea = new SPELLTextArea(new Font("Segoe UI Emoji", Font.PLAIN, 15), new Insets(5, 5, 5, 5),
                new Color(0x22252A), Color.WHITE);
        outputTextArea.setEditable(false);
        outputTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (checker != null) {
                    int position = outputTextArea.viewToModel2D(e.getPoint());
                    for (int i = 0; i < checker.matches.size(); i++) {
                        RuleMatch newMatch = checker.matches.get(i);
                        if (position >= newMatch.getFromPos() && position <= newMatch.getToPos()) {
                            outputTextArea
                                    .setComponentPopupMenu(
                                            SPELLTextArea.createCustomContextMenu(outputTextArea, newMatch, "manual"));
                            outputTextArea.select(newMatch.getFromPos(), newMatch.getToPos());
                            outputTextArea.getComponentPopupMenu().show(outputTextArea, e.getX(), e.getY());
                            break;
                        }
                    }
                }
            }
        });

        JScrollPane outputTextAreaPane = ScrollPaneFactory.newScrollPane(outputTextArea);
        outputTextAreaPane.setBounds(805, 105, 340, 460);
        outputTextAreaPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        String[] grammarCBOptions = { "Check Grammar and Spelling" };
        grammarComboBox = new SPELLComboBox(grammarCBOptions, "Grammar & Spelling Editor", 15);
        grammarComboBox.setBounds(80, 100, 210, 45);
        comboBoxes.add(grammarComboBox);
        String[] caseCBOptions = { "UPPER CASE", "lower case", "camelCasing", "Sentence case", "Capitalized Case" };
        casingComboBox = new SPELLComboBox(caseCBOptions, "Case Editor", 15);
        casingComboBox.setBounds(80, 155, 210, 45);
        comboBoxes.add(casingComboBox);
        String[] spaceRemoverCBOptions = { "Remove Line Breaks", "Remove Spaces" };
        spaceRemoverComboBox = new SPELLComboBox(spaceRemoverCBOptions, "Whitespace Remover", 15);
        spaceRemoverComboBox.setBounds(80, 210, 210, 45);
        comboBoxes.add(spaceRemoverComboBox);
        String[] alphabetizerCBOptions = { "Sort Alphabetically", "Reverse Alphabetical" };
        alphabetizerComboBox = new SPELLComboBox(alphabetizerCBOptions, "Alphabetical Sorter", 15);
        alphabetizerComboBox.setBounds(80, 265, 210, 45);
        comboBoxes.add(alphabetizerComboBox);

        CustomizedDropdownPanel dropdownPanel = new CustomizedDropdownPanel(grammarComboBox.getBackground().darker());
        dropdownPanel.setBounds(80, 365, 210, 95);
        bulletEditorComboBox = new FakeComboBox(dropdownPanel);
        bulletEditorComboBox.setBounds(80, 320, 210, 45);

        JPanel bulletAdderPanel = new JPanel();
        bulletAdderPanel.setLayout(null);
        bulletAdderPanel.setBackground(new Color(0x26292E));
        bulletAdderPanel.setBounds(5, 5, 200, 40);
        JPanel bulletRemoverPanel = new JPanel();
        bulletRemoverPanel.setLayout(null);
        bulletRemoverPanel.setBackground(new Color(0x26292E));
        bulletRemoverPanel.setBounds(5, 50, 200, 40);

        SPELLLabel bulletAdderLabel = new SPELLLabel("Bullet Adder", 9, Color.WHITE);
        bulletAdderLabel.setBounds(3, 3, 70, 10);
        SPELLLabel bulletRemoverLabel = new SPELLLabel("Bullet Remover", 9, Color.WHITE);
        bulletRemoverLabel.setBounds(3, 3, 80, 10);

        String[] bulletCBOptions = { "a.)", "1.", "•", "-", "▪", "▫", "◦", "◆", "◇", "◈", "✓" };
        bulletDesignToAddCB = new SPELLComboBox(bulletCBOptions, "Bullet Editor", 12);
        bulletDesignToAddCB.setBounds(70, 10, 55, 25);
        bulletDesignToAddCB
                .setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
                        BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        bulletAdderEditButton = new SPELLButton("Add", 12, new Color(0x1B1E23), Color.white, "Add Bullets");
        bulletAdderEditButton.setBounds(135, 10, 60, 25);
        bulletAdderEditButton.addActionListener(this);

        bulletAdderPanel.add(bulletAdderLabel);
        bulletAdderPanel.add(bulletDesignToAddCB);
        bulletAdderPanel.add(bulletAdderEditButton);

        bulletDesignToRemoveCB = new SPELLComboBox(bulletCBOptions, "Bullet Remover", 12);
        bulletDesignToRemoveCB.setBounds(70, 10, 55, 25);
        bulletDesignToRemoveCB
                .setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
                        BorderFactory.createEmptyBorder(0, 10, 0, 0)));

        bulletRemoveEditButton = new SPELLButton("Remove", 12, new Color(0x1B1E23), Color.white,
                "Remove Bullets");
        bulletRemoveEditButton.addActionListener(this);
        bulletRemoveEditButton.setBounds(135, 10, 60, 25);

        bulletRemoverPanel.add(bulletRemoverLabel);
        bulletRemoverPanel.add(bulletDesignToRemoveCB);
        bulletRemoverPanel.add(bulletRemoveEditButton);

        dropdownPanel.add(bulletAdderPanel);
        dropdownPanel.add(bulletRemoverPanel);

        for (SPELLComboBox comboBox : comboBoxes) {
            comboBox.addActionListener(this);
        }

        clearButton = new SPELLButton("Clear", 15, new Color(0x22252A), Color.white, "Clear Input");
        clearButton.setBounds(400, 580, 70, 40);
        clearButton.addActionListener(this);

        copyButton = new SPELLButton("Copy", 15, new Color(0x22252A), Color.white, "Copy Output");
        copyButton.setBounds(800, 580, 70, 40);
        copyButton.addActionListener(this);

        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(new Color(0xF7DBA7));
        instructionPanel.setBounds(60, 480, 450, 100);
        instructionPanel.setVisible(false);

        instructionButton = new SPELLButton("i", 15, new Color(0x22252A), Color.white, "Instructions");
        instructionButton.setFont(new Font("Georgia", Font.PLAIN, 15));
        instructionButton.setBounds(60, 580, 30, 30);
        //instructionButton.addActionListener(this);
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

        String instructionsString = "<html>Users can use the manual option of the program by inputting text <br>in the input text area and then selecting an edit from the dropdown<br> boxes they want to be executed. The new text will be displayed <br>in the output text area.</html>";
        SPELLLabel exampleLabel = new SPELLLabel(instructionsString, 5, Color.WHITE);
        exampleLabel.convertToParag();
        exampleLabel.setForeground(Color.BLACK);
        instructionPanel.add(exampleLabel);

        this.add(manualBackButton);
        this.add(inputTextAreaPane);
        this.add(outputTextAreaPane);
        this.add(grammarComboBox);
        this.add(casingComboBox);
        this.add(spaceRemoverComboBox);
        this.add(alphabetizerComboBox);
        this.add(bulletEditorComboBox);
        this.add(dropdownPanel);
        this.add(clearButton);
        this.add(copyButton);
        this.add(instructionButton);
        this.add(instructionPanel, JLayeredPane.POPUP_LAYER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = inputTextArea.getText();
        if (e.getSource() == manualBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        } else if (e.getSource() == clearButton) {
            inputTextArea.setText("");
            outputTextArea.setText("");
        } else if (e.getSource() == copyButton) {
            ClipboardListener.copyFromTextArea(outputTextArea);
        } else if (comboBoxes.contains(e.getSource())) {
            if (e.getSource() == grammarComboBox) {
                if (grammarComboBox.getSelectedIndex() == 0) {
                    refreshLanguageToolChecker(inputText, outputTextArea, "manual");
                }
            } else if (e.getSource() == casingComboBox) {
                CaseConverter edit = new CaseConverter(inputText);
                if (casingComboBox.getSelectedIndex() == 0) {
                    outputTextArea.setText(edit.upperCase());
                } else if (casingComboBox.getSelectedIndex() == 1) {
                    outputTextArea.setText(edit.lowerCase());
                } else if (casingComboBox.getSelectedIndex() == 2) {
                    outputTextArea.setText(edit.camelCasing());
                } else if (casingComboBox.getSelectedIndex() == 3) {
                    outputTextArea.setText(edit.sentenceCase());
                } else if (casingComboBox.getSelectedIndex() == 4) {
                    outputTextArea.setText(edit.capitalizedCase());
                }
            } else if (e.getSource() == spaceRemoverComboBox) {
                SpaceAndLineRemover edit = new SpaceAndLineRemover(inputText);
                if (spaceRemoverComboBox.getSelectedIndex() == 0) {
                    outputTextArea.setText(edit.removeLineBreaks());
                } else if (spaceRemoverComboBox.getSelectedIndex() == 1) {
                    outputTextArea.setText(edit.removeSpaces());
                }
            } else if (e.getSource() == alphabetizerComboBox) {
                Alphabetizer edit = new Alphabetizer(inputText);
                if (alphabetizerComboBox.getSelectedIndex() == 0) {
                    edit.sortAlphabetically();
                    outputTextArea.setText(edit.getText());
                } else if (alphabetizerComboBox.getSelectedIndex() == 1) {
                    edit.sortReverseAlphabetically();
                    outputTextArea.setText(edit.getText());
                }
            }
        } else if (e.getSource() == bulletAdderEditButton) {
            BulletsEditor edit = new BulletsEditor(inputText, bulletDesignToAddCB.getSelectedItem().toString());
            outputTextArea.setText(edit.addBullets());
        } else if (e.getSource() == bulletRemoveEditButton) {
            BulletsEditor edit = new BulletsEditor(inputText, bulletDesignToRemoveCB.getSelectedItem().toString());
            outputTextArea.setText(edit.removeBullets());

        }
    }

    public static void refreshLanguageToolChecker(String inputText, JTextArea outputTextArea, String page) {
        if (page.equals("manual")) {
            SPELLManualPage.checker = new GrammarAndSpellingFixer(inputText);
            SPELLManualPage.checker.buildGrammarAndSpellingChecker();
            SPELLHighlightIndicators highlightErrors = new SPELLHighlightIndicators(inputText, outputTextArea,
                    SPELLManualPage.checker);
            outputTextArea.setText(inputText);
            highlightErrors.showHighlights();
        } else if (page.equals("automatic")) {
            SPELLAutomaticPage.checker = new GrammarAndSpellingFixer(inputText);
            SPELLAutomaticPage.checker.buildGrammarAndSpellingChecker();
            SPELLHighlightIndicators highlightErrors = new SPELLHighlightIndicators(inputText, outputTextArea,
                    SPELLAutomaticPage.checker);
            outputTextArea.setText(inputText);
            highlightErrors.showHighlights();
        }

    }
}
