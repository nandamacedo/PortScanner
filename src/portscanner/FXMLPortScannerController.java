/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portscanner;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author nanda
 */
public class FXMLPortScannerController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label label2;
    @FXML
    private TextField txtIP;
    @FXML
    private TextField txtPortas;
    @FXML
    private TextField txtPortaIndividual;
    @FXML
    private Button buttonOk;
    @FXML
    private Pane pane;
    static String ip;
    static String aux;
    static int numeroDePortas;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        ip = txtIP.getText();

        try {

            InetAddress addr = InetAddress.getByName(ip);
            // Obtém o nome do computador/dispositivo pelo endereço IP informado
            String hostname = addr.getHostName();
            ArrayList<Integer> portas = new ArrayList<>();
            portas.add(12);
            String st = "";
            String g, esp;
            esp = ",";
            g = " ";

            if (!txtPortas.getText().equalsIgnoreCase("")) {
                numeroDePortas = Integer.parseInt(txtPortas.getText());
                st = " \n";
                for (int i = 0; i < numeroDePortas; i++) {
                    Socket socket = null;

                    try {

                        socket = new Socket(addr, i);

                        portas.add(i);

                        g = Integer.toString(i);
                        st += g;
                        st += esp;

                    } catch (IOException ex) {
                    } finally {
                        try {
                            // Fecha o socket
                            if (socket != null) {
                                socket.close();
                            }
                        } catch (IOException ex) {
                        }
                    }

                }
                pane.setVisible(true);
                label.setText("Portas abertas: " + st + "\n Host: " + hostname);
            } else if (!txtPortaIndividual.getText().equalsIgnoreCase("")) {
                int i = Integer.parseInt(txtPortaIndividual.getText());
                Socket socket = null;

                try {

                    socket = new Socket(addr, i);

                    portas.add(i);

                    g = Integer.toString(i);
                    st += g;

                } catch (IOException ex) {
                } finally {
                    try {
                        // Fecha o socket
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException ex) {
                    }
                }

                pane.setVisible(true);
                if (!st.equalsIgnoreCase("")) {
                    label.setText("A porta " + st + " está aberta");
                } else {
                    label.setText("A porta não está aberta");
                }
            }

        } catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        label2.setText("Scanner Finalizado. ");

    }

    @FXML
    private void handleButtonLimpar(ActionEvent event) {
        txtIP.clear();
        txtPortas.clear();
        txtPortaIndividual.clear();
        pane.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setVisible(false);
    }

}
