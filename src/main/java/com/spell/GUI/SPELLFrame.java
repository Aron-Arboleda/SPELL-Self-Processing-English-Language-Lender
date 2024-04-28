package com.spell.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class SPELLFrame extends JFrame implements ActionListener {
    static SPELLPage homePage, manualPage, automaticPage, iPage;
    ArrayList<SPELLPage> pagesList = new ArrayList<SPELLPage>();
    static SPELLPage ACTIVEPAGE;

    SPELLButton manualButton, automaticButton;
    

    public SPELLFrame() {
        this.setTitle("SPELL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setBounds(100,30, 1250,780);
        this.setSize(1250, 780);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);

        // #region[rgba(30, 87, 75, 0.15)] Home Page
        homePage = new SPELLPage("homePage", new Color(0xEFF1C5));

        manualButton = new SPELLButton("Manual", 17, new Color(0x002E2C), Color.white, "Manual Editor");
        manualButton.setBounds(400, 570, 170, 50);
        manualButton.addActionListener(this);
        automaticButton = new SPELLButton("Automatic", 17, new Color(0x002E2C), Color.white, "Automatic Editor");
        automaticButton.setBounds(640, 570, 170, 50);
        automaticButton.addActionListener(this);

        homePage.add(manualButton);
        homePage.add(automaticButton);

        pagesList.add(homePage);

        // #endregion

        manualPage = new SPELLManualPage("manualPage", new Color(0xE3E7AF));
        pagesList.add(manualPage);

        automaticPage = new SPELLAutomaticPage("automaticPage", new Color(0xE3E7AF));
        pagesList.add(automaticPage);

        // #region[rgba(80, 17, 25, 0.15)] Instructions Page
        iPage = new SPELLPage("iPage", new Color(0xE3E7AF));
        pagesList.add(iPage);
        // #endregion


        for (SPELLPage spellPage : pagesList) {
            spellPage.setVisible(false);
            this.add(spellPage);
        }

        homePage.setVisible(true);
        ACTIVEPAGE = homePage;

        this.setLayout(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manualButton) {
            switchPage(manualPage);
        } else if (e.getSource() == automaticButton) {
            switchPage(automaticPage);
        }
    }

    public static void switchPage(SPELLPage nextPage) {
        ACTIVEPAGE.setVisible(false);
        nextPage.setVisible(true);
        ACTIVEPAGE = nextPage;
    }
}
