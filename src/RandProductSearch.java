import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {
    private JLabel searchLbl;

    private JTextField searchFld;

    private JButton searchBtn;

    private JPanel inputPnl;

    private JScrollPane scrollPane;

    private JTextArea resultTA;

    private RandomAccessFile randomAccessFile;

    private static final int recordSize = 256;

    public RandProductSearch(String filePath) {
        try {
            randomAccessFile = new RandomAccessFile(filePath, "r");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        setLayout(new BorderLayout());

        inputPnl = new JPanel();
        inputPnl.setLayout(new FlowLayout());
        inputPnl.add(searchLbl = new JLabel("Enter Search String: "));
        inputPnl.add(searchFld = new JTextField(20));
        inputPnl.add(searchBtn = new JButton("Search"));

        add(inputPnl, BorderLayout.NORTH);

        scrollPane = new JScrollPane(resultTA = new JTextArea(10, 30));
        resultTA.setEditable(false);

        add(scrollPane, BorderLayout.CENTER);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRecords();
            }
        });
    }

    private void searchRecords() {
        try {
            String searchString = searchFld.getText();
            randomAccessFile.seek(0);
            boolean found = false;
            resultTA.setText("");


            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                byte[] buffer = new byte[recordSize];
                int bytesRead = randomAccessFile.read(buffer);

                String record = new String(buffer, 0, bytesRead, "UTF-8").trim();

                if (record.toLowerCase().contains(searchString.toLowerCase())) {
                    resultTA.append(record);
                    found = true;
                    }
                }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No matching records found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching records", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
