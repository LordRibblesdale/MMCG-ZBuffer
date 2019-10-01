import javax.swing.*;
import java.awt.event.*;

public class RayTracingZ extends JFrame {
  public MyJPanel canvas;
  public int f;
  public static Scene scn;
  public static Main sv;

  public RayTracingZ (String s) {
    super(s);
    f = 700;
    canvas = new MyJPanel(f, 1);
    this.add(canvas);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    scn = new Scene(0.1);
    scn.makeSphere(300, new Point(0,0, 1300), 50, 0.8, 0.3,
        0.5, 0.1, 0.9);
    /* il primo parametro e’ il raggio della sfera;
     l’argomento Point e’ il centro: la variabile y
     e’ l’altezza e cresce verso il basso,
     z e’ la profondita’ spaziale e cresce all’allontanarsi
     dal viewplane, che e’ a z=0;
     il parametro successivo e’ il numero di ripartizioni
     della latitudine e della longitudine nel creare
     la modellazione a maglie triangolari della sfera;
     i tre parametri succressivi sono le coordinate R, G, B
     del colore, fra 0 e 1;
     infine abbiamo il coefficiente di riflessione e quello di
     diffusione.
     */
    scn.makeSphere(150, new Point(-200, 150, 1000), 50, 0.2,
        0.2, 0.8, 0.8, 0.7);
    final Light l1 = new Light(-300, -400, 1000, 150);
    Light l2 = new Light(600, -400, 0, 350);
    l2.l=l1.l=0.5;
    l2.q=l1.q=0;
    scn.lgt.add(l1);
    scn.lgt.add(l2);
    this.setVisible(true);
    this.addKeyListener(new KeyListener() {
      public double d = 0.5;
      public double r = 0.5;
      public double diff = d;
      public double ref = r;
      public Point v;
      @Override public void keyPressed(KeyEvent e) {
        v = new Point(0, 0, 0);
        /*
        if(e.getKeyCode() == KeyEvent.VK_UP) v = new
            Point(0,-10,0);
        if(e.getKeyCode() == KeyEvent.VK_DOWN) v = new
            Point(0,10,0);
        if(e.getKeyCode() == KeyEvent.VK_LEFT) v = new
            Point(-10,0,0);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) v = new
            Point(10,0,0);
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) v =
            new Point(0,0,-10);
        if(e.getKeyCode() == KeyEvent.VK_SPACE) v = new
            Point(0,0,10);
        l1.shift(v);
        repaint();
        */

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

      @Override public void keyTyped(KeyEvent e) {
        v = new Point(0,0,0);
        if(e.getKeyChar() == 'w') v = new Point(0,-20,0);
        if(e.getKeyChar() == 's') v = new Point(0,20,0);
        if(e.getKeyChar() == 'a') v = new Point(-20,0,0);
        if(e.getKeyChar() == 'd') v = new Point(20,0,0);
        if(e.getKeyChar() == 'l') diff = -(diff - 0.5);
        if(e.getKeyChar() == 'p') ref = -(ref - 0.5);
        //System.out.println(diff+" "+ref);
        for(int i=0; i<scn.pts.size(); i++){
          scn.pts.get(i).shift(v.per(-1));
          scn.pts.get(i).kDif = diff;
          scn.pts.get(i).kRef = ref;
        }

        scn.lgt.get(0).shift(v.per(-1));
        repaint();
      }
      @Override public void keyReleased(KeyEvent e) {
      }
    });
  }
}
