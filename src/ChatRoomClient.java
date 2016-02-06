import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatRoomClient extends JFrame{
    BufferedReader in;
    PrintWriter out;
    JTextField textField;
    JTextArea messageArea;

    public ChatRoomClient() {
        initUI();
    }

    private void initUI() {
        textField = new JTextField(40);
        messageArea = new JTextArea(8, 40);
        textField.setEditable(false);
        messageArea.setEditable(false);
        add(textField, "North");
        add(new JScrollPane(messageArea), "Center");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Add Listeners
        textField.addActionListener(e -> {
            out.println(textField.getText());
            textField.setText("");
        });
    }

    public String getName() {
        return JOptionPane.showInputDialog(
                this,
                "Choose a screen name:",
                "Screen name selection",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws IOException {
        // Make connection and initialize streams
        String serverAddress = "192.241.239.251";
        Socket socket = new Socket(serverAddress, 8090);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ChatRoomClient client = new ChatRoomClient();
        client.run();
    }
}

