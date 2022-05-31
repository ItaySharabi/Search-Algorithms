package UI;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class MarbleButtonView extends JButton implements OnClickListener {
    private String key;
    private OnClickListener listener;

    public MarbleButtonView(String key, OnClickListener listener/*, int i, int j ??? */) {
        this.key = key;
        this.listener = listener;
        init();
    }

    public void init() {
        setSize(20, 20);
        setText(key);
        setFont(new Font("Helvatica", getFont().getStyle(), 30));
        setBackground(Color.cyan);
    }

    @Override
    public void onClick(View v) {

    }
}
