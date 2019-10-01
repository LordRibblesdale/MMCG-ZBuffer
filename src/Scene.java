import java.util.ArrayList;

public class Scene {
  public ArrayList<Point> pts; // Lista dei punti nella scena
  public ArrayList<Triangle> obj; // Lista dei triangoli nella scena

  public Scene() {
    pts = new ArrayList<Point>();
    obj = new ArrayList<Triangle>();
  }

  public Scene(ArrayList<Triangle> objects) {
    obj = objects;
  }

  public Triangle get(int i) {
    return obj.get(i);
  }

  public void add(Point p) {
    pts.add(p);
  }

  public void add(Triangle t) {
    obj.add(t);
  }

  public void add(ArrayList<Triangle> list) {
    for (int i = 0; i < list.size(); i++)
      obj.add(list.get(i));
  }

  public int size() {
    return obj.size();
  }

  public void makeSphere(double r, Point c, int n, double R,
                         double G, double B) {
    /* makeSphere() genra una mesh sferica di triangonli
    la sfera ha raggio r, centro c ed e’ divisa in n
    paralleli ed
    n meridiani. In tutto e’ composta da n^2 triangoli.
    La sfera ha colore (R,G,B) */

    Point color = new Point(R, G, B);
    double x, y, z, a, b;
    double pi = Math.PI;
    int i, j;
    ArrayList<Point> tpts = new ArrayList<Point>();
    Triangle t;
    Point v1, v2, v3, p;
    for (i = 0; i < n; i++) {
      for (j = 0; j < n; j++) {
        x = r * Math.sin(i * pi / n) * Math.cos(2 * j * pi / n) + c.x;
        y = r * Math.sin(i * pi / n) * Math.sin(2 * j * pi / n) + c.y;
        z = -r * Math.cos(i * pi / n) + c.z;
        p = new Point(x, y, z);
        p.setNormal(c.to(p));
        this.add(p);
        tpts.add(p);
      }
    }
    for (i = 0; i < n - 1; i++) {
      for (j = 0; j < n; j++) {
        t = new Triangle(tpts.get((i * n + j) % (n * n)),
            tpts.get(((i + 1) * n + j) % (n * n)),
            tpts.get((i * n + j + 1) % (n * n)));
        t.normal.clr = color;
        this.add(t);
        t = new Triangle(tpts.get(((i + 1) * n + j) % (n * n)),
            tpts.get(((i + 1) * n + j + 1) % (n * n)),
            tpts.get((i * n + j + 1) % (n * n)));
        t.normal.clr = color;
        this.add(t);
      }
    }
  }

  public void makeQuad(Point v0, Point l1, Point l2, double
      r, double g, double b) {

    /* makeQuad genera un parallelogramma con vertice in v0,
    l1 e l2 come i due lati uscenti da v0,
    di colore (r,g,b)*/
    Point color = new Point(r, g, b);
    Triangle t;
    Point v1 = v0.sum(l1);
    Point v2 = v0.sum(l2);
    Point v3 = v1.sum(l2);
    pts.add(v0);
    pts.add(v1);
    pts.add(v2);
    pts.add(v3);
    t = new Triangle(v0, v1, v3);
    t.normal.clr = color;
    this.add(t);
    t = new Triangle(v0, v3, v2);
    t.normal.clr = color;
    this.add(t);
  }
}