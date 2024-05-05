package com.spell.GUI;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import org.languagetool.rules.RuleMatch;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class SPELLPage extends JPanel {
    String pageName;
    private Image backgroundImage;

    public SPELLPage(String name, Color background) {
        pageName = name;
        this.setLayout(null);
        this.setSize(1210, 680);
        this.setBackground(background);
    }

    public void setImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static ImageIcon newScaledImage(String imageName, int width, int height) {
        ImageIcon image = new ImageIcon(SPELLPage.class.getResource("/" + imageName));
        ImageIcon scaledImage = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        return scaledImage;
    }
}

class SPELLButton extends JButton {
    public SPELLButton(String text, int fontSize, final Color backColor, Color foreColor, String tooltip) {
        this.setText(text);
        this.setMargin(new Insets(3, 3, 3, 3));
        this.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setBackground(backColor);
        this.setForeground(foreColor);
        this.setToolTipText(tooltip);
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        final SPELLButton button = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setBackground(button.getBackground().brighter());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backColor);
            }
        });
    }
}

class OpaqueButton extends JButton {
    public OpaqueButton(String tooltip) {
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setToolTipText(tooltip);
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBackground(new Color(255, 255, 255, 50));
        this.setOpaque(false);
        final OpaqueButton button = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setOpaque(true);
                    SPELLFrame.homePage.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setOpaque(false);
            }

        });
    }
}

class SPELLLabel extends JLabel {
    public SPELLLabel(String text, int fontSize, Color foreColor) {
        this.setText(text);
        this.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        this.setForeground(foreColor);
    }
}

class SPELLTextArea extends JTextArea {
    public SPELLTextArea(Font font, Insets margin, Color backColor, Color foreColor) {
        this.setFont(font);
        this.setMargin(margin);
        this.setBackground(backColor);
        this.setForeground(foreColor);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setCaretColor(Color.WHITE);
    }

    public static JPopupMenu createCustomContextMenu(final SPELLTextArea textArea, RuleMatch match) {
        JPopupMenu contextMenu = new JPopupMenu();

        int i = 0;
        for (final String replacementText : match.getSuggestedReplacements()) {
            if (i >= 5) {
                break;
            }
            JMenuItem replacementMenu = new JMenuItem(replacementText);
            replacementMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textArea.replaceSelection(replacementText);
                    SPELLManualPage.refreshLanguageToolChecker(textArea.getText(), textArea);
                }
            });
            contextMenu.add(replacementMenu);
            i++;
        }
        return contextMenu;
    }
}

class SPELLComboBox extends JComboBox<String> {
    public SPELLComboBox(String[] items, String toolTip) {
        for (String item : items) {
            this.addItem(item);
        }
        this.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setToolTipText(toolTip);
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(0x1B1E23));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));

        final SPELLComboBox comboBox = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (comboBox.isEnabled()) {
                    comboBox.setBackground(new Color(0x23272E));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                comboBox.setBackground(new Color(0x1B1E23));
            }
        });

        this.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = new BasicArrowButton(SwingConstants.SOUTH);
                arrowButton.setBackground(new Color(0x1B1E23));
                arrowButton.setForeground(Color.WHITE);
                arrowButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                return arrowButton;
            }

        });
    }
}

class SPELLAutoIconsPanel extends JPanel {
    String iconName;
    JToggleButton iconToggleButton;

    public SPELLAutoIconsPanel(String iconName, Color background) {
        this.iconName = iconName;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(200, 200));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setBackground(background);

        JPanel taskBar = new JPanel();
        taskBar.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
        taskBar.setBackground(background.darker());

        iconToggleButton = new JToggleButton("•");
        iconToggleButton.setSize(15, 15);
        taskBar.add(iconToggleButton);

        this.add(taskBar, BorderLayout.SOUTH);
    }
}
