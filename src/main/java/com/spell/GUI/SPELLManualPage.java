package com.spell.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.spell.Logic.*;

public class SPELLManualPage extends SPELLPage implements ActionListener{
    // Manual Page Components
    SPELLTextArea inputTextArea, outputTextArea;
    SPELLComboBox grammarComboBox, casingComboBox, spaceRemoverComboBox, alphabetizerComboBox;
    ArrayList<SPELLComboBox> comboBoxes = new ArrayList<SPELLComboBox>();
    SPELLButton clearButton, copyButton;

    SPELLButton manualButton, manualBackButton;

    public SPELLManualPage(String name, Color background){
        super(name, background);
        // #region[rgba(80, 87, 75, 0.15)] Manual Page
        
        manualBackButton = new SPELLButton("Back", 15, new Color(0x002E2C), Color.white, "Return to Previous Page");
        manualBackButton.setBounds(20, 20, 100, 50);
        manualBackButton.addActionListener(this);

        inputTextArea = new SPELLTextArea(new Font("Segoe UI", Font.PLAIN, 15), new Insets(10, 10, 10, 10),
                Color.white);
        inputTextArea.setBounds(340, 100, 370, 500);
        outputTextArea = new SPELLTextArea(new Font("Segoe UI", Font.PLAIN, 15), new Insets(10, 10, 10, 10),
                Color.white);
        outputTextArea.setBounds(750, 100, 370, 500);

        SPELLLabel inputLabel = new SPELLLabel("Input", 17, new Color(0x050F0F));
        inputLabel.setBounds(480, 70, 100, 30);
        SPELLLabel outputLabel = new SPELLLabel("Output", 17, new Color(0x050F0F));
        outputLabel.setBounds(920, 70, 100, 30);

        JPanel comboBoxesContainer = new JPanel();
        comboBoxesContainer.setLayout(new BoxLayout(comboBoxesContainer, BoxLayout.Y_AXIS));
        comboBoxesContainer.setAlignmentY(RIGHT_ALIGNMENT);
        comboBoxesContainer.setBounds(20, 100, 270, 330);
        comboBoxesContainer.setBackground(new Color(0x035E7B));

        
        String[] grammarCBOptions = { "Fix Grammar", "Fix Spelling" };
        grammarComboBox = new SPELLComboBox(grammarCBOptions, "Grammar & Spelling Editor");
        comboBoxes.add(grammarComboBox);
        String[] caseCBOptions = { "UPPER CASE", "lower case", "camelCasing", "Sentence case", "Capitalized Case"};
        casingComboBox = new SPELLComboBox(caseCBOptions, "Case Editor");
        comboBoxes.add(casingComboBox);
        String[] spaceRemoverCBOptions = { "Remove Line Breaks", "Remove Spaces"};
        spaceRemoverComboBox = new SPELLComboBox(spaceRemoverCBOptions, "Whitespace Remover");
        comboBoxes.add(spaceRemoverComboBox);
        String[] alphabetizerCBOptions = { "Sort Alphabetically", "Reverse Alphabetical" };
        alphabetizerComboBox = new SPELLComboBox(alphabetizerCBOptions, "Alphabetical Sorter");
        comboBoxes.add(alphabetizerComboBox);

        for (SPELLComboBox comboBox : comboBoxes) {
            comboBox.addActionListener(this);
        }
        
        clearButton = new SPELLButton("Clear", 15, new Color(0x002E2C), Color.white, "Clear Input");
        clearButton.setBounds(340, 600, 70, 40);
        clearButton.addActionListener(this);

        copyButton = new SPELLButton("Copy", 15, new Color(0x002E2C), Color.white, "Copy Output");
        copyButton.setBounds(750, 600, 70, 40);
        copyButton.addActionListener(this);

        comboBoxesContainer.add(grammarComboBox); comboBoxesContainer.add(casingComboBox); comboBoxesContainer.add(spaceRemoverComboBox); comboBoxesContainer.add(alphabetizerComboBox); 

        this.add(manualBackButton);
        this.add(inputTextArea);
        this.add(outputTextArea);
        this.add(inputLabel);
        this.add(outputLabel);
        this.add(comboBoxesContainer);
        this.add(clearButton);
        this.add(copyButton);

        // #endregion
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manualBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        } else if (e.getSource() == clearButton) {
            inputTextArea.setText("");
        } else if (e.getSource() == copyButton) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(outputTextArea.getText());
            clipboard.setContents(selection, null);
            JOptionPane.showMessageDialog(null, "Text have been copied to clipboard.");
        }else if (comboBoxes.contains(e.getSource())) {
            if (e.getSource() == grammarComboBox) {
                String inputText = inputTextArea.getText();
                GrammarAndSpellingFixer edit = new GrammarAndSpellingFixer(inputText);
                outputTextArea.setText(edit.fixGrammarAndSpelling());
            } else if (e.getSource() == casingComboBox) {
                String inputText = inputTextArea.getText();
                CaseConverter edit = new CaseConverter(inputText);
                outputTextArea.setText(edit.camelCasing());
        } else if (e.getSource() == spaceRemoverComboBox) {
                // TODO
            } else if (e.getSource() == alphabetizerComboBox) {
                // TODO
            }
        }
    }
}
