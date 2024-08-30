import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.ArrayList;

public class CalisanEkranı extends JDialog {
    private JPanel contentPane;
    private JTable table1;
    private JScrollPane scrollpane;
    private JTextField aramaçubuğu;
    private JTextField adfield;
    private JTextField soyadfield;
    private JTextField departmanfield;
    private JTextField maaşfield;
    private JButton yeniÇalışanEkleButton;
    private JLabel mesaj;
    private JButton çalışanGüncelleButton;
    private JButton çalışanSilButton;
    private DefaultTableModel model;
    CalisanIslemleri islemler = new CalisanIslemleri();

    public CalisanEkranı() {
        setContentPane(contentPane);
        setResizable(false);
        setSize(1000,700);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        model = new DefaultTableModel(new Object[]{"Id", "Ad", "Soyad","Departman","Maaş"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table1.setModel(model);
        scrollpane.setViewportView(table1);
        calısangoruntule();


        aramaçubuğu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String ara = aramaçubuğu.getText();
                dinamikAra(ara);
            }
        });
        yeniÇalışanEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.setText("");
                String ad = adfield.getText();
                String soyad = soyadfield.getText();
                String departman = departmanfield.getText();
                String maaş = maaşfield.getText();
                islemler.calisanEkle(ad,soyad,departman,maaş);
                calısangoruntule();
                mesaj.setText("Yeni çalışan başarıyla eklendi");
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow();
                adfield.setText(model.getValueAt(selectedRow, 1).toString());
                soyadfield.setText(model.getValueAt(selectedRow, 2).toString());
                departmanfield.setText(model.getValueAt(selectedRow, 3).toString());
                maaşfield.setText(model.getValueAt(selectedRow, 4).toString());
            }
        });
        çalışanGüncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ad = adfield.getText();
                String soyad = soyadfield.getText();
                String departman = departmanfield.getText();
                String maaş = maaşfield.getText();

                int selectedRow = table1.getSelectedRow();
                if(selectedRow == -1){

                    if(model.getRowCount() == 0){
                        mesaj.setText("Çalışanlar tablosu şuanda boş");
                    }
                    else {
                        mesaj.setText("Lütfen güncellenecek bir çalışan seçin");
                    }
                }
                else {
                    int id = (int) model.getValueAt(selectedRow,0);
                    islemler.calısanGuncelle(id,ad,soyad,departman,maaş);
                    calısangoruntule();
                    mesaj.setText("Çalışan başarıyla güncellendi");
                }

            }
        });
        çalışanSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mesaj.setText("");
                int selectedRow = table1.getSelectedRow();
                if(selectedRow == -1){
                    if(model.getRowCount() == 0){
                        mesaj.setText("Çalışan tablosu şuanda boş");
                    }
                    else {
                        mesaj.setText("Lütfen silinecek bir çalışan seçin");
                    }
                }
                else {
                    int id = (int) model.getValueAt(selectedRow,0);
                    islemler.calisanSil(id);
                    calısangoruntule();
                    mesaj.setText("Çalışan başarıyla silindi");
                }
            }
        });
    }

    public void calısangoruntule(){

        model.setRowCount(0);
        ArrayList<Calısan> calısanlar = new ArrayList<Calısan>();

        calısanlar = islemler.calısanlarıgetir();

        if(calısanlar != null){

            for(int i = 0;i < calısanlar.size();i++){
                Object [] eklenecek = {calısanlar.get(i).getId(),calısanlar.get(i).getAd(),calısanlar.get(i).getSoyad(),calısanlar.get(i).getDepartman(),calısanlar.get(i).getMaaş()};
                model.addRow(eklenecek); //tabloya içerik ekleriz
            }

        }
    }

    public void dinamikAra(String ara){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
        table1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(ara));
    }
}
