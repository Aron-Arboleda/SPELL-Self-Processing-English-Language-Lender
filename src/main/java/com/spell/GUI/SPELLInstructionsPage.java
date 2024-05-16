package com.spell.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SPELLInstructionsPage extends SPELLPage implements ActionListener {
    static Image instructionsBackImage = SPELLPage.newScaledImage("InstructionsBackground.jpg", 1210, 680).getImage();

    SPELLButton instructionsBackButton;

    JPanel manualPanel = new JPanel();
    JLabel[] manualLBLFeatures = new JLabel[10];
    JScrollPane manualSP;

    public SPELLInstructionsPage(String name, Color background) {
        super(name, background);

        this.setImage(instructionsBackImage);

        ImageIcon backButtonIcon = SPELLPage.newScaledImage("InkBackButtonIcon.png", 40, 25);

        instructionsBackButton = new SPELLButton("", 15, new Color(0xF9F8F6), Color.white, "Return to Previous Page");
        instructionsBackButton.setBounds(67, 44, 60, 28);
        instructionsBackButton.setIcon(backButtonIcon);
        instructionsBackButton.addActionListener(this);

        manualPanel.setBounds(67, 124, 605, 250);
        manualPanel.setLayout(new BoxLayout(manualPanel, BoxLayout.Y_AXIS));

        // manualPanel.setBackground(new Color(0, 0 , 0, 0));

        // manualSP.setBounds(67, 124, 605, 250);
        // manualSP.setBorder(new EmptyBorder(0, 0, 0, 0));

        for(int i = 0; i < manualLBLFeatures.length; i++){
            manualLBLFeatures[i] = new JLabel(Integer.toString(i));
            manualPanel.add(manualLBLFeatures[i]);
        }

        manualSP = ScrollPaneFactory.newScrollPane(manualPanel);

        this.add(manualPanel);
        this.add(instructionsBackButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instructionsBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        }
    }

}
