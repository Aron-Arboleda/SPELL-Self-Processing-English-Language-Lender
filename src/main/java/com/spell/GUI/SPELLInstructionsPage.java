package com.spell.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SPELLInstructionsPage extends SPELLPage implements ActionListener {
    static Image instructionsBackImage = SPELLPage.newScaledImage("InstructionsBackground.jpg", 1210, 680).getImage();

    SPELLButton instructionsBackButton;

    JPanel featuresPanel = new JPanel();
    JPanel definitionPanel = new JPanel();
    JScrollPane featuresSP;

    public SPELLInstructionsPage(String name, Color background) {
        super(name, background);

        this.setImage(instructionsBackImage);

        ImageIcon backButtonIcon = SPELLPage.newScaledImage("InkBackButtonIcon.png", 40, 25);

        instructionsBackButton = new SPELLButton("", 15, new Color(0xF9F8F6), Color.white, "Return to Previous Page");
        instructionsBackButton.setBounds(67, 44, 60, 28);
        instructionsBackButton.setIcon(backButtonIcon);
        instructionsBackButton.addActionListener(this);

        featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.Y_AXIS));
        featuresPanel.setOpaque(false);
        // featuresPanel.setBackground(new Color(0,0,0,0));
        featuresPanel.setForeground(Color.BLACK);

        String[] headingsArray = { "Check Grammar and Spelling", "UPPERCASE", "lowercase", "Sentence case",
                "camelCasing", "Capitalized Case", "Remove Line Breaks", "Remove Spaces", "Sort Alphabetically",
                "Sort Reverse Alphabetically", "Add Bullets", "Remove Bullets" };
        String[] descriptionsArray = {
                "corrects the grammar and spelling errors of text",
                "capitalizes all characters",
                "all characters are changed into lowercase",
                "capitalizes the first character of every sentence",
                "<html>turns the first character into lowercase and then <br>every succeeding first letter of the words are in uppercase</html>",
                "capitalizes the first character of every word",
                "removes all the new lines or line breaks",
                "removes all spaces",
                "items are sorted alphabetically based on their first character",
                "items are sorted alphabetically from z to a based on their first character",
                "adds a specified bullet design that has a sequence if applicable (e.g. 1., 2., 3. …)",
                "removes the chosen bullet design"
        };
        String[] examplesArray = {
                "Example: Input: He are driving. | Output: He is driving.",
                "Example: Input: hello | Output: HELLO",
                "Example: Input: GOOD MORNING! | Output: good morning!",
                "<html>Example:<br>Input: good day, sir. did I pass my exam? | Output: Good day, sir. Did I pass my exam?</html>",
                "Example: Input: Str name | Output: strName",
                "<html>Example:<br>Input: the quick brown fox jumps over the lazy dog<br>Output: The Quick Brown Fox Jumps Over The Lazy Dog.</html>",
                "<html>Example:<br>Input:<br>Save<br>The<br>Date<br>Output: Save the date</html>",
                "Input: Sam sung | Output: Samsung",
                "<html>Example:<br>Input:<br>Alex<br>Axel<br>Aron<br>Juan<br>Output:<br> Alex<br>Aron<br>Axel<br>Juan</html>",
                "<html>Example:<br>Input:<br>Alex<br>Axel<br>Aron<br>Juan<br>Output:<br> Juan<br>Axel<br>Aron<br>Alex</html>",
                "<html>Example:<br>Input:<br>Alex<br>Aron<br>Axel<br>Juan<br>Output:<br>- Alex <br>- Aron<br>- Axel<br>- Juan</html>",
                "<html>Example:<br>Input:<br>- Alex <br>- Aron<br>- Axel<br>- Juan<br>Output:<br> Alex<br>Aron<br>Axel<br>Juan</html>"
        };
        SPELLLabel featuresLabel = new SPELLLabel("Features:", 5, Color.WHITE);
        featuresLabel.setFont(new Font("Segoe UI", Font.BOLD, 27));
        featuresLabel.setForeground(Color.BLACK);
        featuresPanel.add(featuresLabel);

        for (int i = 0; i < headingsArray.length; i++) {
            SPELLLabel headingLabel = new SPELLLabel(headingsArray[i], 5, Color.WHITE);
            headingLabel.convertToHeading3();
            featuresPanel.add(headingLabel);

            SPELLLabel descriptionLabel = new SPELLLabel(descriptionsArray[i], 5, Color.WHITE);
            descriptionLabel.convertToParag();
            featuresPanel.add(descriptionLabel);

            SPELLLabel exampleLabel = new SPELLLabel(examplesArray[i], 5, Color.WHITE);
            exampleLabel.convertToParag();
            featuresPanel.add(exampleLabel);
            featuresPanel.add(Box.createVerticalStrut(10));
        }

        featuresSP = ScrollPaneFactory.newScrollPane(featuresPanel);
        featuresSP.setBorder(new EmptyBorder(0, 0, 0, 0));
        featuresSP.setBounds(67, 124, 580, 500);

        definitionPanel.setLayout(new BoxLayout(definitionPanel, BoxLayout.Y_AXIS));
        definitionPanel.setOpaque(false);
        definitionPanel.setForeground(Color.BLACK);
        definitionPanel.setBounds(750, 40, 650, 300);

        SPELLLabel whatIsSpell = new SPELLLabel("What Is S.P.E.L.L.?", 5, Color.WHITE);
        whatIsSpell.setFont(new Font("Segoe UI", Font.BOLD, 45));
        whatIsSpell.setForeground(Color.BLACK);
        definitionPanel.add(whatIsSpell);

        String strSpellDefintion = "<html> S.P.E.L.L. helps you with your grammar and<br>spelling. Furthermore, it allows you to change<br>the casing of certain or all characters in your<br>text. It also enables users to add various<br>bullets and remove spaces, line breaks, and<br>bullets. All these features can be done by the<br>program showing the edited inputted text or<br>editing the clipboard text directly.\n<hmtl>";
        SPELLLabel spellDefinition = new SPELLLabel(strSpellDefintion, 5, Color.WHITE);
        spellDefinition.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        spellDefinition.setForeground(Color.BLACK);
        definitionPanel.add(spellDefinition);

        this.add(definitionPanel);
        this.add(featuresSP);
        this.add(instructionsBackButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instructionsBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        }
    }

}
