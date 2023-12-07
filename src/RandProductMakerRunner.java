import javax.swing.*;

public class RandProductMakerRunner {
    public static void main(String[] args) {
        RandProductMaker RPMFrame = new RandProductMaker();
        RPMFrame.setTitle("Random Product Maker");
        RPMFrame.setSize(600, 400);
        RPMFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RPMFrame.setVisible(true);
    }
}