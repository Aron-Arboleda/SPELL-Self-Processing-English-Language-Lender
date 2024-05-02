package com.spell.GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SPELLInstructionsPage extends SPELLPage implements ActionListener {
    static Image instructionsBackImage = SPELLPage.newScaledImage("InstructionsBackground.jpg", 1210, 680);

    SPELLButton instructionsBackButton;

    public SPELLInstructionsPage(String name, Color background) {
        super(name, background);
        
        this.setImage(instructionsBackImage);

        instructionsBackButton = new SPELLButton("Back", 15, new Color(0x22252A), Color.white, "Return to Previous Page");
        instructionsBackButton.setBounds(20, 20, 100, 50);
        instructionsBackButton.addActionListener(this);

        this.add(instructionsBackButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instructionsBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        } 
    }
    
}
