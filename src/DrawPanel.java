import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawPanel extends JPanel implements MouseMotionListener {
    private Angles polygon = new Angles();
    private int verticesSize = 50;
    private Rectangle[] vertices = new Rectangle[4];
    private Polygon poly;
    private int currentVertexIndex = -1;
    public Color mainColor = new Color(255, 255, 255);
    private Color backgroundColor = new Color(32, 32, 32);
    private float polygonWidth = 8.0F;
    private JButton areaLabel = new JButton();
    private JButton reset = new JButton("Set to square");
    private JLabel errorLabel = new JLabel();  // New label for error message

    public DrawPanel(final JFrame jFrame) {
        this.setSize(1200, 800);
        this.setBackground(this.backgroundColor);
        this.setLayout(new BorderLayout());

        // Setting up initial polygon vertices
        int[] xP = {this.getWidth() / 2 - 200, this.getWidth() / 2 + 200, this.getWidth() / 2 + 200, this.getWidth() / 2 - 200};
        int[] yP = {this.getHeight() / 2 - 200, this.getHeight() / 2 - 200, this.getHeight() / 2 + 200, this.getHeight() / 2 + 200};
        this.polygon.setXs(xP);
        this.polygon.setYs(yP);
        this.polygon.changePoint();
        this.poly = new Polygon(this.polygon.getXs(), this.polygon.getYs(), 4);

        this.areaLabel.setFont(new Font("", Font.BOLD, 30));
        this.areaLabel.setForeground(this.mainColor);
        this.areaLabel.setBackground(this.backgroundColor);
        this.areaLabel.setSize(200, 50);
        this.add(this.areaLabel, BorderLayout.NORTH);

        this.reset.setFont(new Font("plain", Font.PLAIN, 20));
        this.reset.setForeground(this.mainColor);
        this.reset.setBackground(this.backgroundColor);
        this.reset.setSize(200, 50);
        this.add(this.reset, BorderLayout.SOUTH);

        initializeVertices();
        this.areaLabel.setText(" Area - " + this.polygon.getArea() + " unit²");

        // Adding error label for the angle check
        errorLabel.setFont(new Font("", Font.BOLD, 30));
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(errorLabel, BorderLayout.EAST);

        this.reset.addActionListener(e -> SwingUtilities.invokeLater(() -> resetToSquare(jFrame)));

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                DrawPanel.this.currentVertexIndex = DrawPanel.this.getVertexIndex(x, y);
                if (DrawPanel.this.currentVertexIndex >= 0) {
                    DrawPanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                } else {
                    DrawPanel.this.setCursor(Cursor.getDefaultCursor());
                }
            }
        });

        this.addMouseMotionListener(this);
    }

    private void initializeVertices() {
        for (int i = 0; i < 4; ++i) {
            Rectangle r = new Rectangle();
            r.setBounds((int) (this.polygon.getX(i) - this.verticesSize * 0.5),
                    (int) (this.polygon.getY(i) - this.verticesSize * 0.5),
                    this.verticesSize, this.verticesSize);
            this.vertices[i] = r;
        }
    }

    private void resetToSquare(JFrame jFrame) {
        jFrame.getContentPane().removeAll();
        Info info = new Info(jFrame, 0);
        jFrame.getContentPane().add(info);
        jFrame.revalidate();
        jFrame.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.backgroundColor);

        for (Rectangle vertex : vertices) {
            g2d.draw(vertex);
        }

        g2d.setColor(this.mainColor);
        g2d.setStroke(new BasicStroke(this.polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(this.poly);

        drawAnglesAndLengths(g2d);
        checkQuadrilateralValidity(); // Check the validity of the quadrilateral
    }

    private void drawAnglesAndLengths(Graphics2D g2d) {
        g2d.setFont(new Font("Century Gothic", Font.BOLD, 15));
        for (int i = 0; i < 4; i++) {
            g2d.drawString(this.polygon.getAngle(i) + "°", this.polygon.getX(i) + 10, this.polygon.getY(i) + 25);
            g2d.drawString(this.polygon.getLength(i) + " units", (int) this.polygon.getXMid(i) - 30, (int) this.polygon.getYMid(i) - 10);
        }
        g2d.setFont(new Font("Century Gothic", Font.BOLD, 25));
        this.areaLabel.setText(" Area - " + this.polygon.getArea() + " unit²");
    }

    private void checkQuadrilateralValidity() {
        int angleSum = 0;
        for (int i = 0; i < 4; i++) {
            angleSum += this.polygon.getAngle(i);
        }

        // Check if angle sum is within valid range and display error if not
        if (angleSum < 357 || angleSum > 363) {
            errorLabel.setText("Error: The shape is not a valid quadrilateral.");
        } else {
            errorLabel.setText(""); // Clear error if the quadrilateral is valid
        }
    }

    private int getVertexIndex(int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (this.vertices[i].contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    public void mouseMoved(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (this.getVertexIndex(x, y) >= 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

        public void mouseDragged(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        if (this.getBounds().contains(x, y) && this.currentVertexIndex >= 0) {
            //updatePolygon(x, y);
            updatePolygonAsync(x, y);
        }
    }

//    private void updatePolygon(int x, int y) {
//        polygon.changePoint(x, y, currentVertexIndex);
//        vertices[currentVertexIndex].x = (int) (polygon.getX(currentVertexIndex) - verticesSize * 0.5);
//        vertices[currentVertexIndex].y = (int) (polygon.getY(currentVertexIndex) - verticesSize * 0.5);
//        poly = new Polygon(polygon.getXs(), polygon.getYs(), 4);
//        repaint();
//    }

    private void updatePolygonAsync(int x, int y) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                polygon.changePoint(x, y, currentVertexIndex);
                vertices[currentVertexIndex].x = (int) (polygon.getX(currentVertexIndex) - verticesSize * 0.5);
                vertices[currentVertexIndex].y = (int) (polygon.getY(currentVertexIndex) - verticesSize * 0.5);
                poly = new Polygon(polygon.getXs(), polygon.getYs(), 4);
                return null;
            }


            protected void done() {
                repaint();
            }
        };
        worker.execute();
    }
}

