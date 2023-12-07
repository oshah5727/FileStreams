import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {
    private JLabel idLbl;
    private JLabel nameLbl;
    private JLabel descriptionLbl;
    private JLabel costLbl;
    private JLabel recordCountLbl;

    private JTextField idFld;
    private JTextField nameFld;
    private JTextField descriptionFld;
    private JTextField costFld;
    private JTextField recordCountFld;
    private JButton addBtn;
    private int recordCount;
    private RandomAccessFile randomAccessFile;

    public RandProductMaker() {

        idLbl = new JLabel("ID:");
        nameLbl = new JLabel("Name:");
        descriptionLbl = new JLabel("Description:");
        costLbl = new JLabel("Cost:");
        recordCountLbl = new JLabel("Record Count:");
        nameFld = new JTextField(35);
        descriptionFld = new JTextField(75);
        idFld = new JTextField(6);
        costFld = new JTextField();
        recordCountFld = new JTextField();
        recordCountFld.setEditable(false);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(idLbl);
        inputPanel.add(idFld);
        inputPanel.add(nameLbl);
        inputPanel.add(nameFld);
        inputPanel.add(descriptionLbl);
        inputPanel.add(descriptionFld);
        inputPanel.add(costLbl);
        inputPanel.add(costFld);
        inputPanel.add(recordCountLbl);
        inputPanel.add(recordCountFld);

        add(inputPanel, BorderLayout.CENTER);

        addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });
        add(addBtn, BorderLayout.SOUTH);

        openRandomAccessFile();

    }

    private void openRandomAccessFile() {
        try {
            randomAccessFile = new RandomAccessFile("product.dat", "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addRecord() {
        try {
            String id = padField(idFld.getText(), 6);
            String name = padField(nameFld.getText(), 35);
            String description = padField(descriptionFld.getText(), 75);
            double cost = Double.parseDouble(costFld.getText());

            randomAccessFile.writeUTF(id);
            randomAccessFile.writeUTF(name);
            randomAccessFile.writeUTF(description);
            randomAccessFile.writeDouble(cost);

            recordCount++;
            recordCountFld.setText(String.valueOf(recordCount));

            idFld.setText("");
            nameFld.setText("");
            descriptionFld.setText("");
            costFld.setText("");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private String padField(String value, int length) {
        StringBuilder paddedValue = new StringBuilder(value);
        paddedValue.setLength(length);
        return paddedValue.toString();
    }
}

