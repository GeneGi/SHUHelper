import javax.swing.*;

/**
 * Created by genegi on 16-1-23.
 */
public class MessageBoard extends JFrame{
    public  MessageBoard() {
        initUI();
    }
    private void initUI() {
        JLabel TODO = new JLabel("TODO by 季俊哲");
        add(TODO);

        setVisible(true);
        setSize(600,400);

    }
}
