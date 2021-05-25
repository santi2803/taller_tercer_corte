import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

/**
 * Index
 */
public class Index {

    public Index() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ImagePane());
                JLabel label = new JLabel();
                label.add(new Draw());
                frame.add(label);
                frame.pack();
                frame.setBounds(0, 0, 900, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class Draw extends JPanel {
        private Point inicioArrastre;
        private Point finArrastre;
        private ArrayList<Shape> lineas = new ArrayList<Shape>();

        public Draw() {
            setBounds(440, 110, 400, 400);
            System.out.println(getSize());
            MouseAdapter ma = new MouseAdapter() {
                public void mousePressed(MouseEvent e) { // cuando se presiona el mouse
                    inicioArrastre = new Point(e.getX(), e.getY());
                    repaint();
                }

                public void mouseReleased(MouseEvent e) { // cuando se deja de presionar el mouse
                    finArrastre = new Point(e.getX(), e.getY());
                    Shape linea = crearLinea(inicioArrastre.x, inicioArrastre.y, finArrastre.x, finArrastre.y);
                    lineas.add(linea);
                    repaint();
                }

                public void mouseDragged(MouseEvent e) { // cuando se esta arrastrando el mouse
                    finArrastre = new Point(e.getX(), e.getY());
                    Shape linea = crearLinea(inicioArrastre.x, inicioArrastre.y, finArrastre.x, finArrastre.y);
                    lineas.add(linea);
                    inicioArrastre = new Point(finArrastre.x, finArrastre.y);
                    repaint();
                }
            };

            addMouseListener(ma);
            addMouseMotionListener(ma);
        }

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            for (Shape linea : lineas) { // dibuja todos las elipses
                g2.draw(linea);
            }
        }

        private Line2D.Float crearLinea(int x1, int y1, int x2, int y2) {
            return new Line2D.Float(x1, y1, x2, y2);
        }
    }

    public class ImagePane extends JPanel {

        private ImageIcon img;

        private Point offset = new Point(0, 0);

        public ImagePane() {
            setBounds(10, 100, 300, 450);
            /* setBounds(400, 200, 300, 450);
            img = new ImageIcon(getClass().getResource("/image.jpg"));

            MouseAdapter ma = new MouseAdapter() {

                private Point startPoint;

                @Override
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                    startPoint.x -= offset.x;
                    startPoint.y -= offset.y;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    startPoint = null;
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    Point p = e.getPoint();
                    int x = p.x - startPoint.x;
                    int y = p.y - startPoint.x;
                    offset = new Point(x, y);
                    repaint();
                }

            };

            addMouseListener(ma);
            addMouseMotionListener(ma); */
            this.setSize(new Dimension(400, 400));

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Dimension height = getSize();
            ImageIcon Img = new ImageIcon(getClass().getResource("/image.jpg"));

            g.drawImage(Img.getImage(), 10, 10, height.width, height.height, null);

            setOpaque(false);
            super.paintComponent(g);
        }

    }

    public static void main(String[] args) {
        new Index();
    }
}