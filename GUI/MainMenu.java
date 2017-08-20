package GUI;

/**
 *
 * @author
 */
import GUI.*;
import LOGIC.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.LayerUI;

public class MainMenu {

    private static JButton bLoadMap;
    private static ActionListener al = new ActionListener() {
        JFileChooser file = new JFileChooser("C:\\Users\\Ryan\\Documents\\NetBeansProjects\\DATASALPROJECT\\Save File");

        @Override
        public void actionPerformed(ActionEvent e) {
            File selected = null;
            if (e.getSource().equals(bLoadMap)) {
                MazeApp app = null;
                int returnval = file.showOpenDialog(null);
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    selected = file.getSelectedFile();
                    if (selected != null) {
                        app = new MazeApp(selected);
                        app.setVisible(true);
                    }//if
                }//if
            }//if
        }//actionPerformed
    };//ActionListener

    public static void createUI() {
        JFrame frame = new JFrame("DATASAL Project");
        LayerUI<JPanel> layerUI = new SpotlightLayerUI();
        JPanel panel = createPanel();
        JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);
        frame.add(jlayer);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createPanel() {
        bLoadMap = new JButton("Load Map");
        bLoadMap.addActionListener(al);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        //con.insets = new Insets(10, 10, 10, 10);
        //con.anchor = GridBagConstraints.SOUTH;
        con.gridx = 0;
        con.gridy = 0;
        p.add(bLoadMap, con);

        con.gridx = 1;
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "MAIN MENU"));
        p.setBackground(new Color(127, 191, 127));
        return p;
    }

}

class SpotlightLayerUI extends LayerUI<JPanel> {

    private boolean mActive;
    private int mX, mY;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(
                AWTEvent.MOUSE_EVENT_MASK
                | AWTEvent.MOUSE_MOTION_EVENT_MASK
        );
    }

   /*@Override
    public void uninstallUI(JComponent c) {
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(0);
        super.uninstallUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();
        super.paint(g2, c);
        g2.dispose();
    }

    @Override
    protected void processMouseEvent(MouseEvent e, JLayer l) {
        if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            mActive = true;
        }
        if (e.getID() == MouseEvent.MOUSE_EXITED) {
            mActive = false;
        }
        l.repaint();
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
        Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
        mX = p.x;
        mY = p.y;
        l.repaint();
    }*/ 
}
