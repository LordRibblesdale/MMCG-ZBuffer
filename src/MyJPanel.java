import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class MyJPanel extends JPanel {
  public ArrayList<Point> GList = new ArrayList<Point>();
  public ArrayList<Point> PList = new ArrayList<Point>();
  public Point p1 = new Point(300,300,20);
  public float r, g, b;
  public static int f,j,i,h,w;

  public MyJPanel(int f){
    super();
    this.f = f;
    w = 720;
    h = 480;
    this.setPreferredSize(new Dimension(w, h));
    this.setVisible(true);
//PList.add(p1);
  }

  @Override
  public void paintComponent(Graphics gr){
    super.paintComponent(gr);
    gr.setColor(new Color(0,0,0));
    gr.fillRect(0, 0, this.getWidth(), this.getHeight());
    RenderingEngine.render(ZBuffer.scn);
    for(i=0; i<w; i++)
      for(j=0; j<h; j++){
        gr.setColor(RenderingEngine.pixelMatrix[i][j]);
        gr.drawLine(i,j,i,j);
      }
  }
}