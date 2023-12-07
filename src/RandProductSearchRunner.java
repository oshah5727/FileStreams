import javax.swing.*;

public class RandProductSearchRunner {

    public static void main(String[] args) {
        RandProductSearch RPSFrame = new RandProductSearch("C:\\Users\\oshah\\IdeaProjects\\FileStreams\\product.dat");
        RPSFrame.setTitle("Random Product Search");
        RPSFrame.setSize(600, 400);
        RPSFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RPSFrame.setVisible(true);
    }
}