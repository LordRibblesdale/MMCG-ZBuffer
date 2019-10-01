import javax.swing.*;
import java.awt.event.*;

public class ZBuffer extends JFrame {
  public MyJPanel canvas;
  public int f;
  public static Scene scn;
  public static ZBuffer sv;

  public ZBuffer(String s) {
    // Main si occupa solo di far eseguire il programma e
    //    gestire l’interfaccia grafica.

    // Non e’ interessante dal punto di vista teorico. Non e’
    //pienamente commentato.

    super(s);
    f = 700;
    canvas = new MyJPanel(f, 0);
    this.add(canvas);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    scn = new Scene(/*0.1*/);
    scn.makeSphere(300, new Point(0,0, 1300), 50, 0.8, 0.3,
        0.5);
    scn.makeQuad(new Point(0,-450,1600), new
        Point(0,600,0),new Point(711,0,0), .8,.8,.8);
    scn.makeSphere(150, new Point(-200, 150, 800), 6, 0.2,
        0.2, 0.8);
    this.setVisible(true);
    this.addKeyListener(new KeyListener() {
      public Point v;
      /* le prossime istruzioni permettono di spostare il
      punto di visuale tramite i tasti di freccia e
      backspace/delete
      */
      @Override public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
          v = new Point(0,-10,0);
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
          v = new Point(0,10,0);
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
          v = new Point(-10,0,0);
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
          v = new Point(10,0,0);
        }

        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
          v = new Point(0,0,-10);
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
          v = new Point(0,0,10);
        }

        for(int i=0; i<scn.pts.size(); i++){
          scn.pts.get(i).shift(v.per(-1));
        }
        repaint();
      }

      @Override public void keyTyped(KeyEvent e) {}
      @Override public void keyReleased(KeyEvent e){}
    });
  }
}
