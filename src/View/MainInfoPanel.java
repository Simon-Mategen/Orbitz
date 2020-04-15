package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.io.IOException;

/**
 * @Author: Manna Manojlovic
 *
* upper left corner: JavaFX-panel with lunar orbits around planets, in miniature
* bottom left corner: textare with general facts about the planet
*
* upper right corner: image gallery with 4 images in miniature. Underneath is one big picture which changes after a small is clicked
* bottom right corner: SWEN THE ALIEN AND HIS FUN FACTS!!!
*/

public class MainInfoPanel extends JPanel
{
    private BorderLayout layout;
    private ImageGalleryPanel imgPanel;
    private SwenTheAlien swenPanel;

    public MainInfoPanel() throws IOException
    {
        imgPanel = new ImageGalleryPanel();
        swenPanel = new SwenTheAlien();
        setupPanel();
    }

    public void setupPanel()
    {
        int width = 1000;
        int height = 600;

        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(12, 12, 12, 12);

        layout = new BorderLayout();
        setLayout(layout);
        setBackground(Color.black);
        setPreferredSize(new Dimension(width, height));
        setBorder(new CompoundBorder(border, margin));

        add(imgPanel,layout.NORTH);
        add(swenPanel,layout.EAST);

    }
}
