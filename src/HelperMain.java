import javax.swing.*;
import java.awt.*;


public class HelperMain extends JFrame {
    public HelperMain() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(2, 3));
        setTitle("SHU Helper");
        setSize(600, 400);

        JButton WhatToEatButton = new JButton("今天吃什么");
        JButton EncryptedMessageButton = new JButton("秘密短信");
        JButton ShareFileButton = new JButton("文件共享");
        JButton LookAroundButton = new JButton("随便看看");
        JButton ChatRoomButton = new JButton("聊天室");
        JButton SiteBlockerButton = new JButton("网站屏蔽器");

        add(WhatToEatButton);
        add(EncryptedMessageButton);
        add(ShareFileButton);
        add(LookAroundButton);
        add(ChatRoomButton);
        add(SiteBlockerButton);

        WhatToEatButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {

        }));
        EncryptedMessageButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            RSAChinese rsaChinese = new RSAChinese();
        }));
        ShareFileButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            ShareFile shareFile = new ShareFile();
        }));
        LookAroundButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            LookAround lookAround = new LookAround();
        }));
        ChatRoomButton.addActionListener(e -> {
            Thread thread = new Thread(new ChatRoomClient());
            thread.run();
        });
        SiteBlockerButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            SiteBlocker siteBlocker = new SiteBlocker();
        }));
    }


    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(() -> {
            HelperMain MainFrame = new HelperMain();
            MainFrame.setVisible(true);
        });
    }
}
