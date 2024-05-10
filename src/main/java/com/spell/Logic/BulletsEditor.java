package com.spell.Logic;

public class BulletsEditor extends PerLineEditor {
    public String bulletDesign;
    public BulletsEditor(String textToEdit, String bulletDesign) {
        super(textToEdit);
        this.bulletDesign = bulletDesign;
    }

    public String addBullets() {
        String text = super.excessSpaceRemover();

        sbEditor.append(text);
        sbEditor.insert(0, bulletDesign + " ");
        text = sbEditor.toString();
        sbEditor.delete(0, sbEditor.length());

        super.setText(text.replaceAll("\n", "\n" + bulletDesign + " "));
        return super.getText();
    }

    public String removeBullets() {
        String text = super.excessSpaceRemover();
        text = text.replaceAll("•", "");
        return text;
    }
}
