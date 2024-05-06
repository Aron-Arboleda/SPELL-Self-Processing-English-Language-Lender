package com.spell.GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class SPELLAutomaticPage extends SPELLPage implements ActionListener {
    // Automatic Page Components
    SPELLAutoIconsPanel alphabetizerPanel, reverseAlphabetizerPanel, upperCasePanel, lowerCasePanel, camelCasingPanel,
            sentenceCasePanel, capitalizedCasePanel, removeLineBreaksPanel, removeSpacesPanel;

    SPELLButton automaticBackButton;

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

        ButtonGroup alphabetizerButtonGroup = new ButtonGroup();
        alphabetizerButtonGroup.add(alphabetizerPanel.iconToggleButton);
        alphabetizerButtonGroup.add(reverseAlphabetizerPanel.iconToggleButton);
        alphabetizerButtonGroup.getSelection();

        this.add(automaticBackButton);

        this.add(alphabetizerPanel);
        this.add(reverseAlphabetizerPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == automaticBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        }
    }
}
