import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


public class Componentes extends JFrame{
    private JLabel label= new JLabel("URL");
    private JLabel label2 = new JLabel("");
    private JTextField textField = new JTextField();
    private JButton button = new JButton("Download");
    private JProgressBar progressBar = new JProgressBar();

    public Componentes() {

        setTitle("File downloader");
        setSize(700, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Box b = Box.createVerticalBox();
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();

        b.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));
        panel2.setLayout(new BorderLayout(0, 0));

        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);

        panel.add(panel2, BorderLayout.SOUTH);
        panel2.add(progressBar, BorderLayout.CENTER);
        panel2.add(label2, BorderLayout.SOUTH);



        textField.setPreferredSize(new Dimension(500, 30));
        progressBar.setPreferredSize(new Dimension(700, 40));
        label2.setPreferredSize(new Dimension(750, 40));

        getContentPane().add(b);
        pack();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please enter a URL", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Worker worker = new Worker(progressBar, label2, textField.getText());
                worker.execute();
            }
        });


    }

}
