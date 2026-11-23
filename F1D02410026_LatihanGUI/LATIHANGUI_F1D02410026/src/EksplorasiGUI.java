package LATIHANGUI_F1D02410026.src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Menggunakan Top-level Container (JFrame) & mengimplementasikan Event Listener Interface (ActionListener)
public class EksplorasiGUI extends JFrame implements ActionListener {

    private static final String NAMA_MAHASISWA = "WIMAR ARYASMARTA PRAKASA";
    private static final String NIM_MAHASISWA = "F1D02410026";

    // Deklarasi Atomic Components
    private JTextField txtNama;
    private JComboBox<String> cmbKategori;
    private JButton btnSimpan, btnReset;
    private JTextArea areaLog;

    public EksplorasiGUI() {
        // 1. Konfigurasi Top-Level Container (JFrame)
        setTitle("Eksplorasi GUI - " + NAMA_MAHASISWA + " (" + NIM_MAHASISWA + ")");
        setSize(480, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi jendela di tengah layar

        // Layout Class #1: BorderLayout pada JFrame
        setLayout(new BorderLayout(10, 10));

        // 2. Intermediate Container #1: Panel Header (North)
        JPanel panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBackground(new Color(45, 118, 232));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));

        JLabel lblJudul = new JLabel("Aplikasi Catatan Aktivitas");
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblJudul.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblIdentitas = new JLabel(NAMA_MAHASISWA + " | NIM: " + NIM_MAHASISWA);
        lblIdentitas.setForeground(new Color(225, 238, 255));
        lblIdentitas.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblIdentitas.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblJudul);
        panelHeader.add(Box.createVerticalStrut(4));
        panelHeader.add(lblIdentitas);
        add(panelHeader, BorderLayout.NORTH);

        // 3. Intermediate Container #2: Panel Form Input (Center)
        // Layout Class #2: GridLayout (3 baris, 2 kolom, jarak 8px)
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Atomic Components di dalam panelForm
        panelForm.add(new JLabel("Nama Aktivitas:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Kategori:"));
        String[] opsiKategori = {"Belajar", "Pekerjaan", "Olahraga", "Hiburan"};
        cmbKategori = new JComboBox<>(opsiKategori);
        panelForm.add(cmbKategori);

        // Intermediate Container #3: Panel Tombol di dalam Grid
        // Layout Class #3: FlowLayout
        panelForm.add(new JLabel("Aksi:"));
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        btnSimpan = new JButton("Simpan");
        btnReset = new JButton("Bersihkan Log");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnReset);
        panelForm.add(panelTombol);

        add(panelForm, BorderLayout.CENTER);

        // 4. Intermediate Container #4: JScrollPane untuk Output Log (South)
        areaLog = new JTextArea(8, 30);
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaLog);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Riwayat Aktivitas - " + NIM_MAHASISWA));
        add(scrollPane, BorderLayout.SOUTH);

        // 5. Registrasi Event Listener
        // Cara A: Menggunakan Interface ActionListener (this)
        btnSimpan.addActionListener(this);
        btnReset.addActionListener(this);

        // Cara B: Menggunakan Event Adapter Class (MouseAdapter)
        btnSimpan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSimpan.setBackground(new Color(144, 238, 144)); // Hijau muda saat hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSimpan.setBackground(UIManager.getColor("Button.background")); // Warna default
            }
        });
    }

    // Override method dari ActionListener Interface
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSimpan) {
            String nama = txtNama.getText().trim();
            String kategori = (String) cmbKategori.getSelectedItem();

            if (nama.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Nama aktivitas tidak boleh kosong!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
            } else {
                areaLog.append("[" + NIM_MAHASISWA + " | " + kategori + "] " + nama + "\n");
                txtNama.setText("");
                txtNama.requestFocus();
            }
        } else if (e.getSource() == btnReset) {
            areaLog.setText("");
        }
    }

    public static void main(String[] args) {
        // Menjalankan GUI di Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new EksplorasiGUI().setVisible(true);
        });
    }
}