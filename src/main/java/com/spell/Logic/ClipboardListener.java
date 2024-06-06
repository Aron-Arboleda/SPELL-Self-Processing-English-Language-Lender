package com.spell.Logic;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class ClipboardListener extends Thread {
    Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    String methodName;
    String bulletDesign;
    String prevClipboardContent = null;
    String output = null;
    JToggleButton toggleButton;
    Class<?>[] classes = {CaseConverter.class, SpaceAndLineRemover.class, Alphabetizer.class, BulletsEditor.class};

    public ClipboardListener(String methodName, JToggleButton toggleButton) {
        this.methodName = methodName;
        this.toggleButton = toggleButton;
    }

    public ClipboardListener(String methodName, JToggleButton toggleButton, String bulletDesign) {
        this.methodName = methodName;
        this.toggleButton = toggleButton;
        this.bulletDesign = bulletDesign;
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
        if (isClipboardContentString(contents)){
            currentClipboardContent = getClipboardContentAsString(contents);
            if (!(currentClipboardContent.equals(prevClipboardContent)) && !(currentClipboardContent.equals(output))) {
                prevClipboardContent = currentClipboardContent;
    
                output = processInput(currentClipboardContent);
                copyOutputToClipboard(output);
            }
        }
    }

    public static String getClipboardContentAsString(Transferable content) {
        try {
            return (String) content.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            return "";
        }
        
    } 

    String processInput(String input) {
        if (methodName.equals("upperCase") || methodName.equals("lowerCase") || methodName.equals("sentenceCase")
            || methodName.equals("camelCasing") || methodName.equals("capitalizedCase")) {
            CaseConverter editor = new CaseConverter(input);
            CaseConverter.callMethod(editor, methodName, input);
            return editor.getText();
        } else if (methodName.equals("removeLineBreaks") || methodName.equals("removeSpaces")){
            SpaceAndLineRemover editor = new SpaceAndLineRemover(input);
            SpaceAndLineRemover.callMethod(editor, methodName, input);
            return editor.getText();
        } else if (methodName.equals("sortAlphabetically") || methodName.equals("sortReverseAlphabetically")){
            Alphabetizer editor = new Alphabetizer(input);
            Alphabetizer.callMethod(editor, methodName, input);
            return editor.getText();
        } else if (methodName.equals("addBullets") || methodName.equals("removeBullets")){
            BulletsEditor editor = new BulletsEditor(input, bulletDesign);
            if (input.contains("\n")) {
                BulletsEditor.callMethod(editor, methodName, input);
                return editor.getText();
            } else {
                return input;
            }
        } else {
            return "(Under Maintenance.)";
        }
    }

    public static void copyOutputToClipboard(String output) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(output);
        clipboard.setContents(selection, null);
    }

    public static boolean isClipboardContentString(Transferable content) {
        // Check if the content is available and has a string flavor
        if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                // Try to get the string from the clipboard
                String text = (String) content.getTransferData(DataFlavor.stringFlavor);
                if (text != null) {
                    // The clipboard contains a string
                    return true;
                }
            } catch (UnsupportedFlavorException | IOException e) {
                return false;
            }
        }
        return false;
    }
}
