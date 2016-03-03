import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatRoomClient extends JFrame implements Runnable{
    BufferedReader in;
    PrintWriter out;
    JTextField inputField;
    JTextArea messageArea;
    JTextArea stateArea;
    JButton send;
    JPanel south;

    public ChatRoomClient() {
        initUI();
    }

    private void initUI() {
        inputField = new JTextField();
        messageArea = new JTextArea();
        stateArea = new JTextArea();
        inputField.setEditable(false);

        messageArea.setLineWrap(true);
        messageArea.setEditable(false);
        stateArea.setEditable(false);
        send = new JButton("发送");

        south = new JPanel();
        south.setLayout(new BorderLayout());
        south.add(inputField, BorderLayout.CENTER);
        south.add(send, BorderLayout.EAST);
        add(stateArea, BorderLayout.NORTH);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        setTitle("在线多人聊天室");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        inputField.addActionListener(e -> {
            out.println(inputField.getText());
            inputField.setText("");
        });
        send.addActionListener(e -> {
            out.println(inputField.getText());
            inputField.setText("");
        });

    }

    public String getName() {
        return JOptionPane.showInputDialog(
                this,
                "请输入你的昵称:",
                "登入",
                JOptionPane.PLAIN_MESSAGE);
    }



    public void run() {
        try {
            String serverAddress = "127.0.0.1";
            Socket socket = new Socket(serverAddress, 9002);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = in.readLine();
                if (line.startsWith("SUBMITNAME")) {
                    out.println(getName());
                } else if (line.startsWith("NAMEACCEPTED")) {
                    stateArea.setText("当前在线人数:" + line.substring(12));
                    inputField.setEditable(true);
                } else if (line.startsWith("MESSAGE")) {
                    messageArea.append(line.substring(8) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ChatRoomClient client = new ChatRoomClient();
        client.run();
    }
}
