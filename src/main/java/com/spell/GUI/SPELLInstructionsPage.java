package com.spell.GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class SPELLInstructionsPage extends SPELLPage implements ActionListener {
    static Image instructionsBackImage = SPELLPage.newScaledImage("InstructionsBackground.jpg", 1210, 680).getImage();

    SPELLButton instructionsBackButton;

    public SPELLInstructionsPage(String name, Color background) {
        super(name, background);

        this.setImage(instructionsBackImage);

        ImageIcon backButtonIcon = SPELLPage.newScaledImage("InkBackButtonIcon.png", 40, 25);

        instructionsBackButton = new SPELLButton("", 15, new Color(0xF9F8F6), Color.white, "Return to Previous Page");
        instructionsBackButton.setBounds(67, 44, 60, 28);
        instructionsBackButton.setIcon(backButtonIcon);
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
