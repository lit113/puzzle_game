package com.itheima.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {

    public LoginJFrame(){
        initJFrame();
    }

    private void initJFrame(){
        this.setSize(400, 260);
        this.setTitle("Puzzle Game v1.0 by SPC Li");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
