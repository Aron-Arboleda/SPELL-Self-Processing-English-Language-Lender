package com.spell.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.*;

import org.languagetool.rules.RuleMatch;

class SPELLPage extends JLayeredPane {
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
        ImageIcon image = new ImageIcon(SPELLPage.class.getResource("/images/" + imageName));
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
        this.setBorder(BorderFactory.createLineBorder(foreColor, 1));

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

    public void removeBorder() {
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
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
        this.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
        this.setForeground(foreColor);
        
    }

    public void convertToHeading3() {
        this.setFont(new Font("Segoe UI", Font.BOLD, 20));
        this.setForeground(Color.black);
    }
    public void convertToParag() {
        this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.setForeground(Color.black);
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
        this.setCaretColor(foreColor);
    }

    public static JPopupMenu createCustomContextMenu(final SPELLTextArea textArea, RuleMatch match, final String page) {
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
                    SPELLManualPage.refreshLanguageToolChecker(textArea.getText(), textArea, page);
                }
            });
            contextMenu.add(replacementMenu);
            i++;
        }
        return contextMenu;
    }
}

class SPELLComboBox extends JComboBox<String> {
    public SPELLComboBox(String[] items, String toolTip, int fontSize) {
        for (String item : items) {
            this.addItem(item);
        }
        this.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
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

class FakeComboBox extends JPanel {

    public FakeComboBox(final CustomizedDropdownPanel dropdownPanel) {
        this.setLayout(null);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setToolTipText("Bullet Editor");
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(0x1B1E23));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        final FakeComboBox panel = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                panel.setBackground(new Color(0x23272E));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                panel.setBackground(new Color(0x1B1E23));
            }
        });

        SPELLLabel bulletLabel = new SPELLLabel("Bullet Editor", 16, Color.WHITE);
        bulletLabel.setBounds(22, 5, 100, 35);

        JButton arrowButton = new BasicArrowButton(SwingConstants.SOUTH);
        arrowButton.setBackground(new Color(0x1B1E23));
        arrowButton.setBounds(165, 10, 25, 25);
        arrowButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dropdownPanel.isVisible()) {
                    dropdownPanel.setVisible(false);
                } else {
                    dropdownPanel.setVisible(true);
                }
            }
        });

        this.add(bulletLabel);
        this.add(arrowButton);
    }

}

class CustomizedDropdownPanel extends JPanel {

    public CustomizedDropdownPanel(Color backColor) {
        this.setLayout(null);
        this.setBackground(backColor);
        this.setBounds(this.getX(), this.getX() + this.getHeight(), this.getWidth(), this.getHeight());
        this.setVisible(false);
    }
}

class SPELLTextField extends JTextField {

    public SPELLTextField(String toolTip, int fontSize) {
        this.setFont(new Font("Segoe UI Emoji", Font.PLAIN, fontSize));
        this.setToolTipText(toolTip);
        this.setForeground(Color.WHITE);
        this.setBackground(new Color(0x1B1E23));
        this.setCaretColor(Color.WHITE);
    }
}

class SPELLAutoIconsPanel extends JPanel {
    String iconName;
    JToggleButton iconToggleButton;
    static JToggleButton activeToggleButton;
    private Image backgroundImage;
    JPanel taskBar;

    public SPELLAutoIconsPanel(String iconName, int width, int height, String iconImageName) {
        this.iconName = iconName;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createDashedBorder(Color.BLACK, 4, 5, 2, true));
        this.setSize(width, height);
        
        ImageIcon panelBackgroundImage = SPELLPage.newScaledImage("AutomaticPageIcons/" + iconImageName, width, height);
        this.backgroundImage = panelBackgroundImage.getImage();

        JPanel titleBar = new JPanel();
        titleBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        titleBar.setBackground(new Color(255, 255, 255, 0));

        SPELLLabel title = new SPELLLabel(iconName, 11, Color.BLACK);
        titleBar.add(title);


        taskBar = new JPanel();
        taskBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        taskBar.setBackground(new Color(255, 255, 255, 0));

        iconToggleButton = new JToggleButton("Off");
        iconToggleButton.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        iconToggleButton.setFocusable(false);
        iconToggleButton.setBackground(Color.WHITE);
        iconToggleButton.setPreferredSize(new Dimension(50, 25));
        iconToggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        iconToggleButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton button = (AbstractButton) c;
                ButtonModel model = button.getModel();

                if (model.isSelected()) {
                    button.setBackground(new Color(0xB2D2B6));
                } else {
                    button.setBackground(UIManager.getColor("Button.background")); 
                }

                super.paint(g, c);
            }
        });

        final SPELLAutoIconsPanel panel = this;

        iconToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (iconToggleButton.getText().equals("Off")) {
                    if (activeToggleButton != null) {
                        activeToggleButton.doClick();
                        activeToggleButton = iconToggleButton;
                    }
                    
                    iconToggleButton.setText("On");
                    iconToggleButton.setBackground(new Color(0xB2D2B6));
                    panel.setBackground(new Color(0xE9EB87));
                    activeToggleButton = iconToggleButton;
                } else {
                    iconToggleButton.setText("Off");
                    iconToggleButton.setBackground(Color.WHITE);
                    panel.setBackground(Color.WHITE);
                    activeToggleButton = null;
                }
            }

            
        });
        taskBar.add(iconToggleButton);

        this.add(taskBar, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
