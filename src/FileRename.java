import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by GeneGi
 * on 2/21/16 14:10.
 * SHUHelper
 */
public class FileRename extends JFrame{
    JPanel north = new JPanel(new GridLayout(1, 4));
    ArrayList<File> selectedFiles = new ArrayList<>();
    JTextArea inputTag = new JTextArea(1, 1);
    JButton addFiles = new JButton("添加文件");
    JButton addTag = new JButton("添加标签");
    JTextArea showSelectedFiles = new JTextArea("当前选择的文件:\n");

    public FileRename() {
        initUI();
    }
    private void initUI() {
        setTitle("文件批量重命名");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);

        north.add(inputTag);
        north.add(addTag);
        north.add(addFiles);
        add(north, BorderLayout.NORTH);

        showSelectedFiles.setEditable(false);
        add(showSelectedFiles, BorderLayout.CENTER);

        addFiles.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setMultiSelectionEnabled(true);
            jFileChooser.showOpenDialog(this);
            File[] files = jFileChooser.getSelectedFiles();
            for (File file : files) {
                selectedFiles.add(file);
                showSelectedFiles.append(file.getName() + "\n");
            }
        });

        addTag.addActionListener(e -> {
            String tag = inputTag.getText();
            for (File file : selectedFiles) {
                addTag(file, tag);
            }
        });
    }

    private void addTag(File file, String tag) {
        //File tagFile = new File(file.getName() + "_" + tag);
        try {
            rename(file, tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rename(File srcFile , String newName) throws IOException {
        Path src = srcFile.toPath();
        Files.move(src, src.resolveSibling(srcFile.getName() + "_" + newName));
    }

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
           FileRename fileRename = new FileRename();
       });
    }

}
