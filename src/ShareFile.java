//程序入口

import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;
import trans.*;

public class ShareFile {
    boolean packFrame = false;

    public ShareFile() {
        MainFrame frame = new MainFrame();
        // 自动调整组件大小，以适应整体布局
        // 验证窗口中所有组件
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }

        // 打开出现在屏幕正中央
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	// 创建事件分发线程
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.
                                             getSystemLookAndFeelClassName());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                new ShareFile();
            }
        });
    }
}