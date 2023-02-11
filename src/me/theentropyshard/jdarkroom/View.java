/*      JDarkroom. Reimplementation of Darkroom website.
 *      Copyright (C) 2023 TheEntropyShard
 *
 *      This program is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.theentropyshard.jdarkroom;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public final class View extends JPanel {
    private static final Color TRANSPARENT_COLOR = new Color(255, 255, 255, 96);
    private static final Color TRANSPARENT_COLOR_DARKER = View.TRANSPARENT_COLOR.darker().darker().darker();
    private static final BufferedImage BACKGROUND = Utils.loadImage("/background.png");
    private static final int WIDTH = View.BACKGROUND.getWidth();
    private static final int HEIGHT = View.BACKGROUND.getHeight();
    private static final Font FONT = new Font("Arial", Font.BOLD, 14);

    private final JLabel resultLabel;

    @SuppressWarnings("unchecked")
    public View() {
        this.setPreferredSize(new Dimension(View.WIDTH, View.HEIGHT));

        CardLayout cardLayout = new CardLayout(30, 50);
        this.setLayout(cardLayout);

        this.resultLabel = new JLabel() {{
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setFont(View.FONT);
        }};

        JPanel dropFilePanel = new JPanel() {
            {
                this.setOpaque(false);
                this.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.anchor = GridBagConstraints.CENTER;
                this.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        cardLayout.show(View.this, "enterCodePanel");
                    }
                });
                this.add(new JPanel() {
                    {
                        this.setOpaque(false);
                        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                        this.add(new JLabel(I18N.getString("dropFileHere")) {{
                            this.setFont(View.FONT);
                            this.setHorizontalAlignment(JLabel.CENTER);
                        }});
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setColor(View.TRANSPARENT_COLOR_DARKER);
                        g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    }
                }, c);
                this.setDropTarget(new DropTarget() {
                    @Override
                    public synchronized void drop(DropTargetDropEvent dtde) {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);
                        try {
                            List<File> droppedFiles = (List<File>)
                                    dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                            String code = SaveFileReader.findInSave(droppedFiles.get(0));
                            if(code == null) {
                                resultLabel.setText(I18N.getString("badFile"));
                            } else {
                                resultLabel.setText(code);
                            }
                            cardLayout.show(View.this, "resultPanel");
                        } catch (Exception e) {
                            e.printStackTrace();
                            dtde.dropComplete(false);
                        }
                        dtde.dropComplete(true);
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(View.TRANSPARENT_COLOR);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };

        JPanel enterCodePanel = new JPanel() {
            {
                this.setOpaque(false);
                this.setLayout(new BorderLayout());
                this.add(new JPanel() {{
                    this.setOpaque(false);
                    this.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.BOTH;
                    c.anchor = GridBagConstraints.CENTER;
                    JTextField textField = new JTextField() {{
                        this.setFont(View.FONT);
                        this.setPreferredSize(new Dimension(220, 30));
                    }};
                    this.add(new JPanel() {
                        {
                            this.setOpaque(false);
                            this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                            this.setLayout(new GridLayout(2, 1));
                            this.add(new JPanel() {{
                                this.setLayout(new BorderLayout());
                                this.setOpaque(false);
                                this.add(new JLabel(I18N.getString("enterCodeHere")) {{
                                    this.setHorizontalAlignment(JLabel.CENTER);
                                    this.setFont(View.FONT);
                                }});
                            }});
                            this.add(new JPanel() {{
                                this.setOpaque(false);
                                this.add(textField);
                                this.add(new JButton(I18N.getString("submit")) {{
                                    this.setFont(View.FONT);
                                    this.setPreferredSize(new Dimension(120, 30));
                                    this.addActionListener(e -> {
                                        String text = textField.getText();
                                        textField.setText("");
                                        if(text.length() != 16) {
                                            resultLabel.setText(I18N.getString("incorrectCodeLength"));
                                        } else {
                                            resultLabel.setText(Decoder.decodeInternetCode(text));
                                        }
                                        cardLayout.show(View.this, "resultPanel");
                                    });
                                }});
                            }});
                        }

                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            g.setColor(View.TRANSPARENT_COLOR_DARKER);
                            g.fillRect(0, 0, this.getWidth(), this.getHeight());
                        }
                    }, c);
                }});
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(View.TRANSPARENT_COLOR);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };

        JPanel resultPanel = new JPanel() {
            {
                this.setOpaque(false);
                this.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.fill = GridBagConstraints.BOTH;
                c.anchor = GridBagConstraints.CENTER;
                this.add(new JPanel() {
                    {
                        this.setOpaque(false);
                        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                        this.add(resultLabel);
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.setColor(View.TRANSPARENT_COLOR_DARKER);
                        g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    }
                }, c);
                this.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        cardLayout.show(View.this, "dropFilePanel");
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(View.TRANSPARENT_COLOR);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        };

        this.add(dropFilePanel, "dropFilePanel");
        this.add(enterCodePanel, "enterCodePanel");
        this.add(resultPanel, "resultPanel");

        cardLayout.show(this, "dropFilePanel");

        JFrame frame = new JFrame("JDarkroom");
        frame.setResizable(false);
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Utils.loadImage("/mdisk.png"));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(View.BACKGROUND, 0, 0, null);
    }
}
