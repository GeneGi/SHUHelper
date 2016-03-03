import webspider.MyCrawler;

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

        JButton WhatToEatButton = new JButton("点菜系统");
        JButton EncryptedMessageButton = new JButton("秘密短信");
        JButton ShareFileButton = new JButton("文件共享");
        JButton LookAroundButton = new JButton("网络爬虫");
        JButton ChatRoomButton = new JButton("聊天室");
        JButton SiteBlockerButton = new JButton("网站屏蔽器");

        add(WhatToEatButton);
        add(EncryptedMessageButton);
        add(ShareFileButton);
        add(LookAroundButton);
        add(ChatRoomButton);
        add(SiteBlockerButton);

        WhatToEatButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            orderMenu orderMenu = new orderMenu();
        }));
        EncryptedMessageButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            RSAChinese rsaChinese = new RSAChinese();
        }));
        ShareFileButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            ShareFile shareFile = new ShareFile();
        }));
        LookAroundButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            MyCrawler crawler = new MyCrawler();
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
