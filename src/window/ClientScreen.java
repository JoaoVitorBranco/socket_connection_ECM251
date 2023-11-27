package src.window;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.communication.Client;
import src.msg.Message;


public class ClientScreen extends JFrame implements ActionListener{
    // Atributos encapsulados
    private JTextArea chatTextField;
    private JComboBox cb;

    private JLabel labelInput;
    private JTextField textFieldInput;

    private JButton buttonEnviar;
    private JButton buttonLimpar;
    private JButton buttonSair;

    private Client client;


    // construtor
    public ClientScreen(Client client, JTextArea chatTextField){
        // Método "super"
        super("Cliente " + client.getAddress());
        
        // Atributos encapsulados
        this.client = client;

        // Criação do GUI inteiro
        Container box = getContentPane();
        box.setLayout(new BorderLayout());
        
        // Separação do GUI
        JPanel buttons = new JPanel(new FlowLayout());
        JPanel text = new JPanel(new FlowLayout());

        // Criação dos widgets
        String names[] = {"broadcast", "unicast"};
        cb = new JComboBox<String>(names);
        cb.setMaximumRowCount(2); 

        this.chatTextField = chatTextField;
        
        labelInput = new JLabel("Input: ");
        textFieldInput = new JTextField(20);
        
        buttonEnviar = new JButton("Enviar");
        buttonLimpar = new JButton("Limpar");
        buttonSair = new JButton("Sair");
        
        
        // Fazendo a adição dos widgets nas separações feitas
        buttons.add(buttonEnviar);
        buttons.add(buttonLimpar);
        buttons.add(buttonSair);

        text.add(labelInput);
        text.add(textFieldInput);
        text.add(cb);

        // Fazendo a adição das partes do GUI no GUI
        box.add(buttons, BorderLayout.SOUTH);
        box.add(text, BorderLayout.NORTH);
        box.add(chatTextField, BorderLayout.CENTER);
        

        // Adicionando ações aos botões
        buttonEnviar.addActionListener(this);
        buttonLimpar.addActionListener(this);
        buttonSair.addActionListener(this);

        // Tamannho total da janela
        setSize(600, 400);

        // Se fechar a janela, o processo se encerra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Habilitando a visualização da janela
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonEnviar){
            String content = textFieldInput.getText();
            String op = (String) cb.getSelectedItem();
            if(op.equals("broadcast")){
                Message msg = new Message("broadcast", content, client.getAddress());
                client.sendMessage(msg);
            }
            else{
                client.unicastProtocolStart(); 
                Message msg = new Message("unicast", content, client.getAddress());
                client.sendMessage(msg);
            }

            // try{
            //     cliente.sendMsg(msg);
            //     // chatTextField.setText(chatTextField.getText() + "\n" + msgRecebida);
            // }
            // catch (Exception err){
            //     JOptionPane.showMessageDialog(null, "Falha na comunicação: " + err.getMessage());
            //     try{
            //         cliente.close();
            //     }
            //     catch (Exception error){
            //         System.out.println("Erro ao fechar conexão: "+error.getMessage());
            //     }
            //     finally {
            //         System.exit(0);
            //         this.dispose();
            //     }
            // }
        } else if(e.getSource() == buttonLimpar){
            chatTextField.setText("");
        } else if(e.getSource() == buttonSair){
            try{
                this.client.close();
            }
            catch (Exception error){
                System.out.println("Erro ao fechar conexão: " + error.getMessage());
            }
            finally {
                System.exit(0);
                this.dispose();
            }
        }
    }
}




