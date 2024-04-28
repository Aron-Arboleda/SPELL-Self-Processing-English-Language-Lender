package com.spell.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SPELLAutomaticPage extends SPELLPage implements ActionListener {
    // Automatic Page Components
    SPELLAutoIconsPanel grammarAIP, casingAIP, spaceRemoverAIP, alphabetizerAIP;

    SPELLButton automaticBackButton;

    public SPELLAutomaticPage(String name, Color background) {
        super(name, background);

        // #region[rgba(80, 87, 145, 0.15)] Automatic Page
        automaticBackButton = new SPELLButton("Back", 15, new Color(0x035E7B), Color.white, "Return to Previous Page");
        automaticBackButton.setBounds(20, 20, 100, 50);
        automaticBackButton.addActionListener(this);

        JPanel autoIconsContainer = new JPanel();
        autoIconsContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        autoIconsContainer.setBackground(new Color(0xEFF1C5));
        autoIconsContainer.setBounds(20, 130, 900, 500);

        grammarAIP = new SPELLAutoIconsPanel("Grammar", new Color(0x035E7B));
        casingAIP = new SPELLAutoIconsPanel("Spelling", new Color(0x035E7B));
        spaceRemoverAIP = new SPELLAutoIconsPanel("Whitespace", new Color(0x035E7B));
        alphabetizerAIP = new SPELLAutoIconsPanel("Alphabetical", new Color(0x035E7B));

        autoIconsContainer.add(grammarAIP); autoIconsContainer.add(casingAIP); autoIconsContainer.add(spaceRemoverAIP); autoIconsContainer.add(alphabetizerAIP);

        this.add(automaticBackButton);
        this.add(autoIconsContainer);
        // #endregion
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == automaticBackButton) {
            SPELLFrame.switchPage(SPELLFrame.homePage);
        }    
    }
}
