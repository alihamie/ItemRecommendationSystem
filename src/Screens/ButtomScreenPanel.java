package Screens;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Allouch on 4/17/2016.
 */
public class ButtomScreenPanel extends JPanel {

    private Button itemSimilarity;
    private Button AdjustedCosine;
    private Button Correlation;
    private BoxLayout layout;

    public ButtomScreenPanel()
    {

        itemSimilarity = new Button("Cosine Similarity");
        AdjustedCosine = new Button("Adjusted Cosine Similarity");
        Correlation = new Button("Correlation Similarity");
        itemSimilarity.setSize(getWidth(),400);
        layout = new BoxLayout(this,BoxLayout.LINE_AXIS);
        setLayout(layout);
        add(itemSimilarity);
        add(AdjustedCosine);
        add(Correlation);

    }
}
