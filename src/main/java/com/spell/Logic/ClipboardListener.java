package com.spell.Logic;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class ClipboardListener extends Thread {
    Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    String editor;
    String prevClipboardContent = null;
    String output = null;
    JToggleButton toggleButton;
    

    public ClipboardListener(String editor, JToggleButton toggleButton) {
        this.editor = editor;
        this.toggleButton = toggleButton;
    }

    public static void copyFromTextArea(JTextArea textArea) {
        copyOutputToClipboard(textArea.getText());
        JOptionPane.showMessageDialog(null, "Text have been copied to clipboard.");
    }

    public void run() {
        while (toggleButton.isSelected()) {
            detectClipboardChanges();
            try {
                this.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!toggleButton.isSelected()){
                this.interrupt();
            }
        }
    }

    public void detectClipboardChanges() {
        Transferable contents = sysClip.getContents(this);
        String currentClipboardContent = "";
        try {
            currentClipboardContent = (String) contents.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!(currentClipboardContent.equals(prevClipboardContent)) && !(currentClipboardContent.equals(output))){
            prevClipboardContent = currentClipboardContent;

            output = processInput(currentClipboardContent);
            System.out.println(output);
            copyOutputToClipboard(output);
        }
    }

    String processInput(String input) {
        if (editor.equals("upperCase") || editor.equals("lowerCase") || editor.equals("sentenceCase")
            || editor.equals("camelCasing") || editor.equals("capitalizedCase")) {
            CaseConverter caseEditor = new CaseConverter(input);
            Method method;
            try {
                method = CaseConverter.class.getMethod(editor);
                method.invoke(caseEditor);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return caseEditor.getText();
        } else if (editor.equals("removeLineBreaks") || editor.equals("removeSpaces")){
            SpaceAndLineRemover spaceEditor = new SpaceAndLineRemover(input);
            Method method;
            try {
                method = SpaceAndLineRemover.class.getMethod(editor);
                method.invoke(spaceEditor);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return spaceEditor.getText();
        } else {
            return "(Under Maintenance.)";
        }
    }

    public static void copyOutputToClipboard(String output) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(output);
        clipboard.setContents(selection, null);
    }
}
