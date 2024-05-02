package com.spell.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SPELLAutomaticPage extends SPELLPage implements ActionListener {
    // Automatic Page Components
    SPELLAutoIconsPanel grammarAIP, casingAIP, spaceRemoverAIP, alphabetizerAIP;

    SPELLButton automaticBackButton;

    static Image automaticEditorBackImage = (new ImageIcon(SPELLPage.class.getResource("/AutomaticEditorBackground.jpg"))).getImage();

    public SPELLAutomaticPage(String name, Color background) {
        super(name, background);
        
        this.setImage(automaticEditorBackImage);
        
        automaticBackButton = new SPELLButton("Back", 15, new Color(0x22252A), Color.white, "Return to Previous Page");
        automaticBackButton.setBounds(20, 20, 100, 50);
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
