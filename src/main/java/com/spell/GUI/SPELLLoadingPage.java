package com.spell.GUI;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;

public class SPELLLoadingPage extends SPELLPage {
    JProgressBar progressBar;
    SPELLLabel loadingLabel;

    static Image loadingBackImage = SPELLPage.newScaledImage("LoadingBackground.jpg", 1210, 680).getImage();

    public SPELLLoadingPage(String name, Color background) {
        super(name, background);

        this.setImage(loadingBackImage);

        progressBar = new JProgressBar();
        progressBar.setBounds(210, 520, 800, 30);
        progressBar.setVisible(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setBorder(BorderFactory.createLineBorder(new Color(0xF03E4A), 3));
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(new Color(0xF03E4A));
        progressBar.setFocusable(false);
        progressBar.setBorderPainted(false);

        this.add(progressBar);

        loadingLabel = new SPELLLabel("Loading... 0%", 5, Color.WHITE);
        loadingLabel.convertToParag();
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setBounds(210, 545, 300, 40);
        this.add(loadingLabel);
    }

}
