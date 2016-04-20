package Screens;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Allouch on 4/17/2016.
 */
public class MainPanel extends JPanel {

    private BorderLayout layout;
    private JLabel instruction;

    public MainPanel()
    {

        layout = new BorderLayout(5,5);
        instruction = new JLabel("Please press any of the desired algorithms to show the results");
        instruction.setHorizontalAlignment(SwingConstants.CENTER);
        setLayout(layout);
        add(instruction, BorderLayout.NORTH);
        add(new ButtomScreenPanel(), BorderLayout.SOUTH);
    }


}
