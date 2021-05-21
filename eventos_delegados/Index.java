
/**
 * Index
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Index extends JFrame implements ActionListener{

    public JButton button1, button2, button3, button4, button5;
    public JLabel label1, label2, label3, label4, label5;
    public JTextField text1, text2, txt3;
    public JTextArea area;

    public Index() {
        setLayout(null);

        // Numero a evaluar
        label1 = new JLabel("Recibir Datos");
        label1.setBounds(10, 10, 480, 20);
        add(label1);
        label2 = new JLabel("Numero a Evaluar");
        label2.setBounds(10, 40, 160, 20);
        add(label2);
        text1 = new JTextField();
        text1.setBounds(150, 40, 150, 20);
        add(text1);
        button1 = new JButton("evaluar");
        button1.setBounds(340, 40, 100, 20);
        add(button1);

        // contar primos
        label3 = new JLabel("Contar primos");
        label3.setBounds(10, 90, 480, 20);
        add(label3);
        label4 = new JLabel("Numero Inicial");
        label4.setBounds(10, 120, 160, 20);
        add(label4);
        text2 = new JTextField();
        text2.setBounds(150, 120, 150, 20);
        add(text2);
        label5 = new JLabel("Numero final");
        label5.setBounds(10, 150, 160, 20);
        add(label5);
        txt3 = new JTextField();
        txt3.setBounds(150, 150, 150, 20);
        add(txt3);
        button2 = new JButton("evaluar");
        button2.setBounds(340, 120, 100, 40);
        add(button2);

        // Eventos y delegados
        button3 = new JButton("Encontrar Primos");
        button3.setBounds(10, 200, 480, 20);
        add(button3);
        button4 = new JButton("Encontrar Primos desde el evento");
        button4.setBounds(10, 230, 480, 20);
        add(button4);
        area = new JTextArea();
        area.setBounds(10, 260, 475, 200);
        area.setEditable(false);
        add(area);
        // Fibonaci
        button5 = new JButton("Secuencia fibonacci");
        button5.setBounds(10, 500, 480, 20);
        add(button5);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        Primos objPrimos = new Primos();
        if (e.getSource() == button1) {
            String value = objPrimos.verificarPrimo(Integer.parseInt(text1.getText()));
            area.setText("");
            writeIntoArea(value);
        }

        if (e.getSource() == button2) {
            int val = objPrimos.cuentaPrimos(Integer.parseInt(text2.getText()), Integer.parseInt(txt3.getText()));
            area.setText("");
            String value = "Existen: " + val;
            writeIntoArea(value);
        }

        if (e.getSource() == button3) {
            String value = "Se encontraron los siguientes numeros:\n";
            writeIntoArea(value);
            primosEncontrados(Integer.parseInt(text2.getText()), Integer.parseInt(txt3.getText()));
        }

        if (e.getSource() == button4) {
            
        }

        if (e.getSource() == button5) {
            printFibonacci(Integer.parseInt(text2.getText()), Integer.parseInt(txt3.getText()));
        }
    }

    public void printFibonacci(int vrInicial, int vrFinal) {
        area.setText("");
        writeIntoArea("Esta es la serie de fibonacci:\n");
        for (int i = 0; i < vrFinal-vrInicial; i++) {
            writeIntoArea(Integer.toString(secuenciaFibonacci(vrInicial + i)));
        }
    }

    public int secuenciaFibonacci(int num) {
        if (num == 0 || num == 1) {
            return num;
        }

        return secuenciaFibonacci(num - 1) + secuenciaFibonacci(num- 2);
    }

    public void primosEncontrados(int vrInicial, int vrFinal) {
        Primos objPrimos = new Primos();
        for (int i = vrInicial; i <= vrFinal; i++) {
            if (objPrimos.verificarPrimo(i) == "Es primo") {
                System.out.println(i);
                writeIntoArea(Integer.toString(i));
            }
        }
    }

    public void writeIntoArea(String value) {
        area.setText(area.getText() + " " +  value);
    }

    public static void main(String[] args) {
        Index index = new Index();
        index.setBounds(0, 0, 510, 600);
        index.setResizable(false);
        index.setVisible(true);
        index.setLocationRelativeTo(null);
        index.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}