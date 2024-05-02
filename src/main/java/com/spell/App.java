package com.spell;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.spell.GUI.SPELLFrame;

public class App extends JFrame {
     
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SPELLFrame();
            }
        });
    }
}
