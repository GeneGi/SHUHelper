import javax.swing.*;

public class WhatToEatToday extends JFrame{
    public WhatToEatToday() {
        initUI();
    }
    private void initUI() {
        JLabel TODO = new JLabel("TODO by 刁晓蕾");
        add(TODO);

        setVisible(true);
        setSize(600,400);
    }
}
