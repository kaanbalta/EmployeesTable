import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GirişEkranı extends JFrame {

    CalisanIslemleri islemler = new CalisanIslemleri();

    private JPanel panel;
    private JTextField kullanıcıadı;
    private JPasswordField parola;
    private JButton girişYapButton;
    private JLabel mesajkısmı;


    public GirişEkranı(){
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        setLocationRelativeTo(null);
        setResizable(false);

        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesajkısmı.setText("");

                String Kadı = kullanıcıadı.getText();
                String Parola = parola.getText();

                boolean girisbasarili = islemler.girisYap(Kadı,Parola);

                if(girisbasarili){
                    CalisanEkranı calisanEkranı = new CalisanEkranı();
                    setVisible(false);
                    calisanEkranı.setVisible(true);
                }
                else {
                    mesajkısmı.setText("Kullanıcı adı veya parola yanlış");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              GirişEkranı girişEkranı = new GirişEkranı();
              girişEkranı.setVisible(true);
            }
        });
    }
}
