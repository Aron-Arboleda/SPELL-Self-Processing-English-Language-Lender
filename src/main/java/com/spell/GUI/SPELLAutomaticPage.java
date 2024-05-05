package com.spell.GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class SPELLAutomaticPage extends SPELLPage implements ActionListener {
    // Automatic Page Components
    SPELLAutoIconsPanel grammarAIP, casingAIP, spaceRemoverAIP, alphabetizerAIP;

    SPELLButton automaticBackButton;

    static Image automaticEditorBackImage = (new ImageIcon(SPELLPage.class.getResource("/AutomaticEditorBackground.jpg"))).getImage();

    public SPELLAutomaticPage(String name, Color background) {
        super(name, background);
        
        this.setImage(automaticEditorBackImage);

        ImageIcon backButtonIcon = SPELLPage.newScaledImage("InkBackButtonIcon.png", 40, 25);
        
        automaticBackButton = new SPELLButton("", 15, new Color(0xF3F3F3), Color.white, "Return to Previous Page");
        automaticBackButton.setBounds(50, 35, 60, 30);
        automaticBackButton.setIcon(backButtonIcon);
        automaticBackButton.addActionListener(this);

        

        this.add(automaticBackButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == automaticBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        }    
    }
}
