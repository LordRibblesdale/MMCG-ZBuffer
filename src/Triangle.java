import java.awt.*;

public class Triangle{
  Point a;
  Point b;
  Point c;
  private Point center;
  Point normal;

  Triangle(Point x, Point y, Point z){
    a = x;
    b = y;
    c = z;

    if (a.normal != null && b.normal != null && c.normal != null) {
      normal = Point.average(a.normal, b.normal, c.normal);
    } else{
      this.makeNormal();
    }

    if (a != null && b != null && c != null) {
      center = Point.average(a, b, c);
    }

    if (a.clr != null && b.clr != null && c.clr != null) {
      normal.clr = Point.average(a.clr, b.clr, c.clr);
    }
  }
  public Point getNormal(){
    if(normal != null)
      return normal;
    else return this.makeNormal();
  }
  public Point makeNormal(){
    Point v = a.min(b);
    Point w = c.min(b);
    normal = w.vect(v).normalize();
    return normal;
  }
  public void print(){
    System.out.println("tr:");
    a.print();
    b.print();
    c.print();
  }
  public Point getCenter(){
    if(center != null)
      return center;
    Point cnt = Point.average(a,b,c);
    return cnt;
  }
  public Color getColor(){
    return normal.getColor();
  }
  public Triangle sum(Point p){
    return new Triangle(a.sum(p), b.sum(p), c.sum(p));
  }
  public void shift(Point v){
// traslazione di vettore v
    a.shift(v);
    b.shift(v);
    c.shift(v);
    center = this.getCenter();
  }
}