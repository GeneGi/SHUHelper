import javax.swing.*;

/**
 * Created by genegi on 16-1-23.
 */
public class LookAround extends JFrame{
    public LookAround() {
        initUI();
    }
    private void initUI() {
        JLabel TODO = new JLabel("TODO by 史大千");
        add(TODO);

        setVisible(true);
        setSize(600,400);

    }
}
