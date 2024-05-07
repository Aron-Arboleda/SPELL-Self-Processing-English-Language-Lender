package com.spell.Logic;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SPELLBasicLogics {

    public static void copyFromTextArea(JTextArea textArea){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(textArea.getText());
        clipboard.setContents(selection, null);
        JOptionPane.showMessageDialog(null, "Text have been copied to clipboard.");
    }
}
