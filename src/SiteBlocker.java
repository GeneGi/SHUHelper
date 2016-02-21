import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteBlocker {
    Scanner in = new Scanner(System.in);
    File hosts;

    public static void main(String[] args) {
        SiteBlocker siteBlocker = new SiteBlocker();
        siteBlocker.run();
    }

    public void run() {
        getHosts();
        int order = 1;
        while (order != 0) {
            menu();
            order = in.nextInt();
            switch (order) {
                case 1: {
                    block();
                    break;
                }
                case 2: {
                    showList();
                    break;
                }
                case 3: {
                    unblock();
                    break;
                }
            }
        }
    }

    private void menu() {
        System.out.println("1-屏蔽网站");
        System.out.println("2-已屏蔽列表");
        System.out.println("3-取消屏蔽");
        System.out.println("0-退出");
        System.out.print("请输入命令:");
    }

    private void getHosts() {
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
        hosts = new File(fileName);
    }

    private void block() {
        System.out.println("请输入你要屏蔽的网站:");
        String site = in.next();
        try {
            Writer output;
            output = new BufferedWriter(new FileWriter(hosts, true));
            output.append("127.0.0.1    " + site);
            output.close();
        } catch (IOException e) {
            System.out.println("没有足够的权限,请使用sudo java SiteBlocker 运行");
            e.printStackTrace();
        }
        System.out.println("屏蔽成功!");
    }

    public void backup(File file) throws IOException {
        File backFile = new File(file.getName() + ".bak");
        copy(file, backFile);

    }

    private void copy(File sourceFile, File destFile) throws IOException{
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
    public boolean isBlock(String s) {
        Pattern pattern = Pattern.compile("127\\.0\\.0\\.1\\s+w.+");
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            return true;
        }
        return false;

    }
    private void showList() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(hosts));
            String line = bufferedReader.readLine();
            while (line != null) {
                if (isBlock(line)) {
                    System.out.println(line.substring(12));
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
        }
    }
    private void unblock() {
        System.out.println("当前屏蔽网站列表:");
        showList();
        System.out.println("请输入你想要取消屏蔽的网站地址:");
        String unblockSite = in.next();
        System.out.println(unblockSite);
        try {
            File tempFile = new File("tempHosts");
            BufferedReader reader = new BufferedReader(new FileReader(hosts));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = "127.0.0.1    " + unblockSite;
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(hosts);
            if (successful) {
                System.out.println("取消成功!");
            } else {
                System.out.println("出现了错误!");
            }
        } catch (IOException e) {

        }
    }
}
