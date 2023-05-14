package chat;

import javax.swing.JOptionPane;

public class Application {
    
    public static void main(String[] args) {
        String  ip = (String)JOptionPane.showInputDialog("Informe o IP","192.168.0.");
        int port = Integer.parseInt(JOptionPane.showInputDialog("Informe a Porta","5000"));
        
        Connection c = new Connection(ip, port);
        
        ChatWindow j = new ChatWindow(c);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}