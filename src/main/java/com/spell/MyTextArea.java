package com.spell;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.Transferable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MyTextArea extends JFrame implements ClipboardOwner, FlavorListener {
    JTextArea textArea;
    Clipboard clip;

    public MyTextArea() {
        this.setSize(500, 500);
        textArea = new JTextArea(40, 40);
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(clip.getContents(null), this);
        clip.addFlavorListener(this);

        // Add the text area to the content pane
        getContentPane().add(textArea);
    }

    @Override
    public void flavorsChanged(FlavorEvent e) {
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(clip.getContents(null), this);
        clip.addFlavorListener(this);
        // Handle clipboard content change here
        // Show a pop-up message when the content changes
        JOptionPane.showMessageDialog(null, "Clipboard content changed!", "Info", JOptionPane.INFORMATION_MESSAGE);

        // Re-register the FlavorListener
        // clip.removeFlavorListener(this);
        // clip.addFlavorListener(this);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        // Handle clipboard ownership loss (if needed)
    }

    //public static void main(String[] args) {
    //    JFrame frame = new MyTextArea();
    //    frame.setVisible(true);
    //    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    //}
}
