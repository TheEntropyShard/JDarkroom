package me.theentropyshard.jdarkroom;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public final class View extends JPanel {
    private static final String YOUR_CODE_IS = "Your code is: ";
    private static final float FONT_SIZE = 20.0F;
    private final JTextField codeField;

    public View() {
        this.setPreferredSize(new Dimension(800, 600));

        CardLayout cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        JLabel codeLabel = new JLabel(View.YOUR_CODE_IS) {{
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setFont(this.getFont().deriveFont(View.FONT_SIZE));
            this.setFocusable(true);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    cardLayout.show(View.this, "DropLabel");
                }
            });
        }};
        this.add(codeLabel, "CodeLabel");

        JLabel dropLabel = new JLabel("Drop save file here, or click to enter Internet Code!", JLabel.CENTER) {{
            this.setDropTarget(new DropTarget() {
                @Override
                public synchronized void drop(DropTargetDropEvent e) {
                    try {
                        e.acceptDrop(DnDConstants.ACTION_COPY);
                        List<File> droppedFiles = (List<File>) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        String code = JDarkroom.getInstance().findInSave(droppedFiles.get(0));
                        if(code == null) {
                            codeLabel.setText("Incorrect Internet Code entered!");
                            codeLabel.setForeground(Color.RED);
                            codeField.setText("");
                        } else {
                            codeLabel.setForeground(Color.DARK_GRAY);
                            codeLabel.setText(View.YOUR_CODE_IS + code);
                        }
                        cardLayout.show(View.this, "CodeLabel");
                        e.dropComplete(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    cardLayout.show(View.this, "InputPanel");
                }
            });
            this.setFocusable(true);
            this.setFont(this.getFont().deriveFont(View.FONT_SIZE));
        }};
        this.add(dropLabel, "DropLabel");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;

        this.codeField = new JTextField() {{
            this.setPreferredSize(new Dimension(250, 50));
            this.setFont(this.getFont().deriveFont(View.FONT_SIZE));
        }};
        panel.add(this.codeField, c);

        JButton button = new JButton("Enter") {{
            this.setPreferredSize(new Dimension(100, 50));
            this.setFont(this.getFont().deriveFont(View.FONT_SIZE));
            this.addActionListener(e -> {
                String code = JDarkroom.getInstance().decodeInternetCode(codeField.getText());
                if(code == null) {
                    codeLabel.setText("Incorrect Internet Code entered!");
                    codeLabel.setForeground(Color.RED);
                    codeField.setText("");
                } else {
                    codeLabel.setForeground(Color.DARK_GRAY);
                    codeLabel.setText(View.YOUR_CODE_IS + code);
                }
                cardLayout.show(View.this, "CodeLabel");
            });
        }};

        panel.add(button, c);
        this.add(panel, "InputPanel");

        cardLayout.show(this, "DropLabel");

        JFrame frame = new JFrame("JDarkroom");
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
