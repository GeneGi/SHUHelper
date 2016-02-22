import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteBlocker extends JFrame{
    File hosts;
    JPanel north;
    JTextArea textArea;
    JButton blockSite;
    JButton unblockSite;

    JFrame blockSiteFrame;

    public SiteBlocker() {
        initUI();
        hosts = getHosts();
        showBlockList();
        initBlockSite();
    }
    private void initUI() {
        north = new JPanel(new GridLayout(1, 2));
        textArea = new JTextArea();
        blockSite = new JButton("屏蔽网站");
        unblockSite = new JButton("取消屏蔽");

        textArea.setEditable(false);

        north.add(blockSite);
        north.add(unblockSite);
        add(north, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("网站屏蔽");
        setVisible(true);
        setSize(400, 300);

        blockSite.addActionListener(e -> {
//            String site = JOptionPane.showInputDialog("请输入你要屏蔽的网站地址:");
//            block(site);
//            showBlockList();
            blockSiteFrame.setVisible(true);
        });

        unblockSite.addActionListener(e -> {
            String site = JOptionPane.showInputDialog("请输入你要取消屏蔽的网站地址:");
            unblockSite(site);
            showBlockList();
        });

    }
    private void initBlockSite() {
        blockSiteFrame = new JFrame();
        blockSiteFrame.setLayout(new GridLayout(5, 1));
        JLabel blockSiteLabel = new JLabel("屏蔽网站:");
        JTextField inputBlockSite = new JTextField();
        JLabel blockTimeLabel = new JLabel("屏蔽时间(单位:秒):");
        JTextField inputBlockTime = new JTextField();
        JButton block = new JButton("确认");

        blockSiteFrame.add(blockSiteLabel);
        blockSiteFrame.add(inputBlockSite);
        blockSiteFrame.add(blockTimeLabel);
        blockSiteFrame.add(inputBlockTime);
        blockSiteFrame.add(block);

        blockSiteFrame.setTitle("屏蔽网站");
        blockSiteFrame.setSize(300, 200);
        blockSiteFrame.setVisible(false);

        block.addActionListener(e -> {
            String site = inputBlockSite.getText();
            long time = Long.parseLong(inputBlockTime.getText());
            block(site, time);
            showBlockList();
            blockSiteFrame.setVisible(false);
        });
    }
    private File getHosts() {
        String fileName = "";
        String OS = System.getProperty("os.name").toLowerCase();
        if ((OS.indexOf("nix")) >= 0) {
            fileName = "/etc/hosts";
        } else if ((OS.indexOf("mac")) >= 0) {
            fileName = "/private/etc/hosts";
        } else if ((OS.indexOf("win")) >= 0) {
            fileName = "C://WINDOWS//system32//drivers//etc//hosts";
        } else {
            System.out.println("未识别的操作系统!");
        }
        return new File(fileName);
    }

    private void block(String site) {
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter(hosts, true));
            output.append("127.0.0.1    " + site + "\n");
            output.close();
        } catch (IOException e) {
            System.out.println("没有足够的权限,请使用管理员权限运行");
        }
    }

    private void block(String site, long delayTime) {
        Date date = new Date();
        long time = date.getTime()+delayTime*1000;
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter(hosts, true));
            output.append("127.0.0.1    " + site + "    #" + time + "\n");
            output.close();
        } catch (IOException e) {
            System.out.println("没有足够的权限,请使用管理员权限运行");
        }

    }

    private void unblockSite(String site) {
        String blockLine = "127.0.0.1    " + site;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(hosts));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith(blockLine)) {
                    String[] blockLineParameter = currentLine.split("\\s");
                    Date now = new Date();
                    long blockTime = Long.parseLong(blockLineParameter[8].substring(1));
                    if (now.getTime() < blockTime) {
                        JOptionPane.showMessageDialog(this, "时间未到,无法取消");
                    }
                    else {
                        unblock(site);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void unblock(String site) {
        try {
            File tempFile = new File("tempHosts");
            BufferedReader reader = new BufferedReader(new FileReader(hosts));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = "127.0.0.1    " + site;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.startsWith(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            tempFile.renameTo(hosts);
        } catch (IOException e) {
            System.out.println("取消失败!");
        }
    }

    public boolean isBlock(String s) {
        Pattern pattern = Pattern.compile("127\\.0\\.0\\.1\\s+w.+\\s#\\d+");
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            return true;
        }
        return false;

    }
    private ArrayList getBlockList() {
        ArrayList<String> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(hosts));
            String line = bufferedReader.readLine();
            while (line != null) {
                if (isBlock(line)) {
                    String[] blockSiteParameter = line.split("\\s");
                    for (int i=0; i<blockSiteParameter.length; i++) {
                        System.out.println(blockSiteParameter[i]);
                    }
                    list.add(blockSiteParameter[4]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
        }
        return list;
    }
    private void showBlockList() {
        textArea.setText("已屏蔽的列表: \n");
        ArrayList<String> list = getBlockList();
        for (String line : list) {
            textArea.append(line);
            System.out.println(line);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SiteBlocker siteBlocker = new SiteBlocker();
        });
    }
}
