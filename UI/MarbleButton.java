package UI;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarbleButton extends JButton{
    private String key;

    public MarbleButton(String key/*, int i, int j ??? */) {
        this.key = key;
        setBackground(Color.cyan);

        init();
    }

    public MarbleButton(String key, Color c/*, int i, int j ??? */) {
        this.key = key;
        setBackground(c);

        init();
    }

    public void init() {
        setSize(20, 20);
        setText(key);
        setFont(new Font("Helvatica", getFont().getStyle(), 30));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Holla!");
            }
        });
    }
}