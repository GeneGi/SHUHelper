import javax.swing.*;
import java.awt.*;


public class HelperMain extends JFrame {
    public HelperMain() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(2,3));
        setTitle("SHU Helper");
        setSize(600,400);

        JButton WhatToEatButton = new JButton( "今天吃什么" );
        JButton EncryptedMessageButton = new JButton( "秘密短信" );
        JButton ShareFileButton = new JButton( "文件共享" );
        JButton LookAroundButton = new JButton( "随便看看" );
        JButton MessageBoardButton = new JButton( "留言板" );

        WhatToEatButton.addActionListener(e -> {
            WhatToEatToday whatToEatToday = new WhatToEatToday();
        });
        EncryptedMessageButton.addActionListener(e -> {
            EncryptedMessage encryptedMessage = new EncryptedMessage();
        });
        ShareFileButton.addActionListener(e -> {
            ShareFile shareFile = new ShareFile();
        });
        LookAroundButton.addActionListener(e -> {
            LookAround lookAround = new LookAround();
        });
        MessageBoardButton.addActionListener(e -> {
            ChatRoomClient messageBoard = new ChatRoomClient();
        });


        add(WhatToEatButton);
        add(EncryptedMessageButton);
        add(ShareFileButton);
        add(LookAroundButton);
        add(MessageBoardButton);
    }



    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            HelperMain MainFrame = new HelperMain();
            MainFrame.setVisible(true);
        });
    }
}
