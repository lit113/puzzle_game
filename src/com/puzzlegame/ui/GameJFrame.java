package com.puzzlegame.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int[][] data = new int[4][4];
    int x = 0;
    int y = 0;

    int count = 0;

    String path = "/Users/tongli/Downloads/PuzzleGame/";

    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };

    JMenuItem rePlayItem = new JMenuItem("Replay");
    JMenuItem exitItem = new JMenuItem("Exit");
    JMenuItem accountItem = new JMenuItem("QR Code");

    public GameJFrame() {

        initJFrame();

        initJMenuBar();

        initData();

        initImage();


        this.setVisible(true);
    }

    private void initData() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(15);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }


        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
                data[i / 4][i % 4] = arr[i];
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();

        if (victory(data)) {
            JLabel win = new JLabel(new ImageIcon(path + "Image2/victory.jpeg"));
            win.setBounds(203, 40, 197, 79);
            this.getContentPane().add(win);
        }

        JLabel stepCount = new JLabel("Steps: " + count);
        stepCount.setBounds(50, 20, 100, 20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[j][i];
                JLabel jLabel = new JLabel(new ImageIcon(path + "Fox/" + num + ".jpeg"));
                jLabel.setBounds(105 * i + 83, 105 * j + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }
        JLabel background = new JLabel(new ImageIcon(path + "Image2/1200px-Sky.jpeg"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("Function");
        JMenu aboutUs = new JMenu("About Us");

        functionJMenu.add(rePlayItem);
        functionJMenu.add(exitItem);

        aboutUs.add(accountItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutUs);

        this.setJMenuBar(jMenuBar);

        rePlayItem.addActionListener(this);
        exitItem.addActionListener(this);
        accountItem.addActionListener(this);

    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("Puzzle Game v1.0 by SPC Li");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        //取消默认的居中放置，只有取消了才会按xy轴添加组件
        this.setLayout(null);
        this.addKeyListener(this);
    }

    public boolean victory(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (victory(data)) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "Fox/Original.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon(path + "Image2/1200px-Sky.jpeg"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory(data)) {
            return;
        }

        int code = e.getKeyCode();
//        System.out.println(code);
        if (code == 38) {
            if (x == 0) {
                return;
            } else {
                System.out.println("move left");
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                count++;
                initImage();
            }
        } else if (code == 37) {
            if (y == 0) {
                return;
            } else {
                System.out.println("move up");
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                count++;
                initImage();
            }
        } else if (code == 40) {
            if (x == 3) {
                return;
            } else {
                System.out.println("move right");
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                count++;
                initImage();
            }
        } else if (code == 39) {
            if (y == 3) {
                return;
            } else {
                System.out.println("move down");
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                count++;
                initImage();
            }
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = win;
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == rePlayItem) {
            System.out.println("replay");
            count = 0;
            initData();
            initImage();
        } else if (obj == exitItem) {
            System.out.println("exit");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("QR code");
            JDialog jDialog = new JDialog();
            JLabel QR = new JLabel(new ImageIcon(path + "Image2/IMG_4753.JPG"));
            QR.setBounds(0,0,258,258);
            jDialog.getContentPane().add(QR);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);

        }
    }
}
