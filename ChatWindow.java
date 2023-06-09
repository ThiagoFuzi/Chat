package chat;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class ChatWindow extends javax.swing.JFrame implements Observer {

    private Connection connection;

    public ChatWindow(Connection connection) {
        super("APS - Chat");
        this.connection = connection;
        initComponents();
        connection.addObserver(this);
        print("Chat iniciado com " + connection.getIp() + ":" + connection.getPort());
        messagejTextArea.requestFocusInWindow();
    }

    private void send() {
        if (!messagejTextArea.getText().isEmpty()) {
        	connection.send(messagejTextArea.getText());
        	print("Você disse: "+messagejTextArea.getText());
        	messagejTextArea.setText("");
        }
    }

    private void print(String text){
        chatjTextArea.append(text+"\n");
         if (!chatjTextArea.getText().isEmpty() && !chatjTextArea.isFocusOwner()) {
                chatjTextArea.setCaretPosition(chatjTextArea.getText().length() - 1);
            }
        
    }
    
    @SuppressWarnings("unchecked")
    //                           
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatjTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        messagejTextArea = new javax.swing.JTextArea();
        sendjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatjTextArea.setEditable(false);
        chatjTextArea.setColumns(20);
        chatjTextArea.setRows(5);
        jScrollPane1.setViewportView(chatjTextArea);

        messagejTextArea.setColumns(20);
        messagejTextArea.setRows(5);
        messagejTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                messagejTextAreaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(messagejTextArea);

        sendjButton.setText("Enviar");
        sendjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(sendjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }                         

    private void sendjButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	send();
    }                                             

    private void messagejTextAreaKeyReleased(java.awt.event.KeyEvent evt) {                                              
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        	 send();
        }
    }                                             

    private javax.swing.JTextArea chatjTextArea;
    private javax.swing.JButton sendjButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea messagejTextArea;

    @Override
    public void update(Observable o, Object arg) {
        print(connection.getMessage());
    }
}