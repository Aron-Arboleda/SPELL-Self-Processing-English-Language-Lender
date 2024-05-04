package com.spell.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import com.spell.Logic.*;

public class SPELLManualPage extends SPELLPage implements ActionListener {
    // Manual Page Components
    SPELLTextArea inputTextArea, outputTextArea;
    SPELLComboBox grammarComboBox, casingComboBox, spaceRemoverComboBox, alphabetizerComboBox;
    ArrayList<SPELLComboBox> comboBoxes = new ArrayList<SPELLComboBox>();
    SPELLButton clearButton, copyButton;

    static Image manualEditorBackImage = (new ImageIcon(SPELLPage.class.getResource("/ManualEditorBackground.jpg")))
            .getImage();

    SPELLButton manualButton, manualBackButton;

    public SPELLManualPage(String name, Color background) {
        super(name, background);

        this.setImage(manualEditorBackImage);

        manualBackButton = new SPELLButton("Back", 15, new Color(0x22252A), Color.white, "Return to Previous Page");
        manualBackButton.setBounds(20, 20, 100, 50);
        manualBackButton.addActionListener(this);

        inputTextArea = new SPELLTextArea(new Font("Segoe UI", Font.PLAIN, 15), new Insets(5, 5, 5, 5),
                new Color(0x22252A), Color.WHITE);

        JScrollPane inputTextAreaPane = ScrollPaneFactory.newScrollPane(inputTextArea);
        inputTextAreaPane.setBounds(410, 105, 340, 460);
        inputTextAreaPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        outputTextArea = new SPELLTextArea(new Font("Segoe UI", Font.PLAIN, 15), new Insets(5, 5, 5, 5),
                new Color(0x22252A), Color.WHITE);

        JScrollPane outputTextAreaPane = ScrollPaneFactory.newScrollPane(outputTextArea);
        outputTextAreaPane.setBounds(805, 105, 340, 460);
        outputTextAreaPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel comboBoxesContainer = new JPanel();
        comboBoxesContainer.setLayout(new BoxLayout(comboBoxesContainer, BoxLayout.Y_AXIS));
        comboBoxesContainer.setAlignmentY(RIGHT_ALIGNMENT);
        comboBoxesContainer.setBounds(50, 100, 270, 330);
        comboBoxesContainer.setBackground(new Color(0x22252A));

        String[] grammarCBOptions = { "Fix Grammar", "Fix Spelling" };
        grammarComboBox = new SPELLComboBox(grammarCBOptions, "Grammar & Spelling Editor");
        comboBoxes.add(grammarComboBox);
        String[] caseCBOptions = { "UPPER CASE", "lower case", "camelCasing", "Sentence case", "Capitalized Case" };
        casingComboBox = new SPELLComboBox(caseCBOptions, "Case Editor");
        comboBoxes.add(casingComboBox);
        String[] spaceRemoverCBOptions = { "Remove Line Breaks", "Remove Spaces" };
        spaceRemoverComboBox = new SPELLComboBox(spaceRemoverCBOptions, "Whitespace Remover");
        comboBoxes.add(spaceRemoverComboBox);
        String[] alphabetizerCBOptions = { "Sort Alphabetically", "Reverse Alphabetical" };
        alphabetizerComboBox = new SPELLComboBox(alphabetizerCBOptions, "Alphabetical Sorter");
        comboBoxes.add(alphabetizerComboBox);

        for (SPELLComboBox comboBox : comboBoxes) {
            comboBox.addActionListener(this);
        }

        clearButton = new SPELLButton("Clear", 15, new Color(0x22252A), Color.white, "Clear Input");
        clearButton.setBounds(400, 580, 70, 40);
        clearButton.addActionListener(this);

        copyButton = new SPELLButton("Copy", 15, new Color(0x22252A), Color.white, "Copy Output");
        copyButton.setBounds(800, 580, 70, 40);
        copyButton.addActionListener(this);

        comboBoxesContainer.add(grammarComboBox);
        comboBoxesContainer.add(casingComboBox);
        comboBoxesContainer.add(spaceRemoverComboBox);
        comboBoxesContainer.add(alphabetizerComboBox);

        this.add(manualBackButton);
        this.add(inputTextAreaPane);
        this.add(outputTextAreaPane);
        this.add(comboBoxesContainer);
        this.add(clearButton);
        this.add(copyButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = inputTextArea.getText();
        if (e.getSource() == manualBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        } else if (e.getSource() == clearButton) {
            inputTextArea.setText("");
        } else if (e.getSource() == copyButton) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(outputTextArea.getText());
            clipboard.setContents(selection, null);
            JOptionPane.showMessageDialog(null, "Text have been copied to clipboard.");
        } else if (comboBoxes.contains(e.getSource())) {
            if (e.getSource() == grammarComboBox) {
                if (grammarComboBox.getSelectedIndex() == 0) {
                    GrammarAndSpellingFixer edit = new GrammarAndSpellingFixer(inputText);
                    edit.buildGrammarAndSpellingChecker();
                    SPELLLineIndicators highlightErrors = new SPELLLineIndicators(inputText, outputTextArea, edit);
                    outputTextArea.setText(inputText);
                    highlightErrors.showHighlights();
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
                // TODO
            } else if (e.getSource() == alphabetizerComboBox) {
                // TODO
            }
        }
    }
}
