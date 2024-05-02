package com.spell.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SPELLFrame extends JFrame implements ActionListener {
    static SPELLPage homePage, manualPage, automaticPage, iPage;
    ArrayList<SPELLPage> pagesList = new ArrayList<SPELLPage>();
    static SPELLPage ACTIVEPAGE;
    static Image manualBackgroundImage = (new ImageIcon(SPELLPage.class.getResource("/BlackBoardManual.jpg")))
            .getImage();
    static Image autoBackgroundImage = (new ImageIcon(SPELLPage.class.getResource("/WhiteBoardAutomatic.jpg")))
            .getImage();

    OpaqueButton manualButton, automaticButton, instructionsButton;

    public SPELLFrame() {
        this.setTitle("SPELL");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setBounds(100,30, 1250,780);
        this.setSize(1225, 715);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);

        // #region[rgba(30, 87, 75, 0.15)] Home Page
        homePage = new SPELLPage("homePage", new Color(0xEFF1C5));
        homePage.setImage(manualBackgroundImage);

        manualButton = new OpaqueButton("Go to Manual Page");
        manualButton.setBounds(250, 415, 220, 85);
        manualButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                homePage.setImage(manualBackgroundImage);
                homePage.repaint();
            }
        });
        manualButton.addActionListener(this);

        automaticButton = new OpaqueButton("Go to Automatic Page");
        automaticButton.setBounds(660, 410, 310, 95);
        automaticButton.addActionListener(this);
        automaticButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                homePage.setImage(autoBackgroundImage);
                homePage.repaint();
            }
        });

        instructionsButton = new OpaqueButton("Go to Instructions Page");
        instructionsButton.setBounds(65, 55, 45, 65);
        instructionsButton.addActionListener(this);

        homePage.add(manualButton);
        homePage.add(automaticButton);
        homePage.add(instructionsButton);

        pagesList.add(homePage);

        // #endregion

        manualPage = new SPELLManualPage("manualPage", Color.WHITE);
        pagesList.add(manualPage);

        automaticPage = new SPELLAutomaticPage("automaticPage", Color.WHITE);
        pagesList.add(automaticPage);

        iPage = new SPELLInstructionsPage("iPage", Color.WHITE);
        pagesList.add(iPage);

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
        } else if (e.getSource() == instructionsButton) {
            switchPage(iPage);
        }
    }

    public static void switchPage(SPELLPage nextPage) {
        ACTIVEPAGE.setVisible(false);
        nextPage.setVisible(true);
        ACTIVEPAGE = nextPage;
    }
}
