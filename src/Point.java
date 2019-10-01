import java.awt.*;

public class Point{
  // cooridnate del punto nello spazio
  public double x;
  public double y;
  public double z;
  // colore
  public Point clr;
  // versore normale al punto
  public Point normal;
  public Point(){
//costruttore
    this.set(0, 0, 0);
  }
  public Point(double a){
//costruttore
    this.set(a, a, a);
  }
  public Point(double a, double b, double c){
//costruttore da coordinate
    this.set(a, b, c);
    clr = new Point();
  }
  public Point(double q, double p, double d, double r, double
      g, double b){
//costruttore con coordiante e colore
    this.set(q, p, d);
    clr = new Point(r,g,b);
  }
  public Point(Point P){
// costruttore che copia un altro punto P
    this.set(P.x, P.y, P.z);
    clr = P.clr;
    normal = P.normal;
  }
  public void set(double a, double b, double c){
// set delle coordinate e colore a grigio 70% circa

    x = a;
    y = b;
    z = c;
  }
  public void set(Point P){
// set delle coordinate
    x = P.x;
    y = P.y;
    z = P.z;
  }
  public Point sum(Point P){
// somma vettoriale
    return new Point(x + P.x, y + P.y, z + P.z);
  }
  public Point sum(double a){
// somma vettoriale
    return new Point(x + a, y + a, z + a);
  }
  public void shift(Point v){
// traslazione di vettore v
    x += v.x;
    y += v.y;
    z += v.z;
  }
  public void shift(double a, double b, double c){
// traslazione da coordinate
    x += a;
    y += b;
    z += c;
  }
  public Point min(Point P){
// differenza vettoriale
    return new Point(x - P.x, y - P.y, z - P.z);
  }

  public Point per(double l){
// moltiplicazione per uno scalare l
    return new Point(x * l, y * l, z * l);
  }
  public Point star(Point P){
    return new Point(x*P.x, y*P.y, z*P.z);
  }
  public double dot(Point P){
// prodotto scalare
    return x*P.x + y*P.y + z*P.z;
  }
  public Point vect(Point P){
// prodotto vettoriale
    return new Point(this.y*P.z - this.z*P.y, this.z*P.x -
        this.x*P.z, this.x*P.y - this.y*P.x );
  }
  public Point to(Point P){
// resituisce il vettore applicato che va "this" al punto P
    return P.min(this);
  }
  public double norm(){
// norma euclidea
    return Math.sqrt(this.dot(this));
  }
  public double squareNorm(){
// norma euclidea al quadrato
    return this.dot(this);
  }
  public Point normalize(){
// noramlizzazione
    double n = this.norm();
    if(n == 0){
      return new Point(0,0,7);
    }
    return this.per( 1/n );
  }
  public Point normalizeL1(){
    // normalizzazione secondo la norma l1
    double n = Math.abs(x) + Math.abs(y) + Math.abs(z);
    if(n == 0) return this;
    return this.per(1/n);
  }
  public Point getNormal(){
// getNormal() restituisce la normale, se esiste
    if(normal == null){
      System.out.println("Normal is null!:");
      return new Point(0,0,0);
    }
    return this.normal;
  }
  public void setNormal(Point v){
// imposta normale al punto
    v = v.normalize();
    this.normal = v;
  }
  public static double clamp(double v, double min, double max){
// clamp() costringe i valori di un double fra min e max
    if(v<min) v=min;
    if(v>max) v=max;
    return v;
  }
  public Point clamp(double min, double max){
    double xc = clamp(x, min, max);
    double yc = clamp(y, min, max);
    double zc = clamp(z, min, max);
    return new Point(xc, yc, zc);
  }
  public void print(){
// stampa a schermo delle coordinate del punto
    System.out.println("p("+x+" "+y+" "+z+")");
  }
  public Color getColor(){
    Point c = (this.clr.per(255)).clamp(0, 255);
    return new Color(round(c.x), round(c.y), round(c.z));
  }

  public double getDistFrom(Point P){
// distanza fra due punti
    Point V = this.to(P);
    return Math.sqrt(V.dot(V));
  }
  public Point project(double f){
/* project() calcola la proiezione centrale di un punto sul
piano z=0 rispetto al centro (0,0,-f)
f rappresenta la distanza del viewplane dal punto di
vista, in termini fotografici
e’ la lunghezza focale */
    double k = f/(f + z);
    Point projection = new Point(k*x, k*y, z); // risultato
    //della matrice di proiezione, conservo la coordinata z
    //dello spazio
    return projection;
  }
  public Point comb(double a, Point A, double b, Point B){
// comb calcola la combinazione lineare di due vettori
// usando i coefficienti a e b.
    return (A.per(a)).sum(B.per(b));
  }
  public static Point comb(double a, Point A, double b, Point
      B, double c, Point C){
// comb calcola la combinazione lineare di tre vettori
// usando i coefficienti a,b e c.
    Point p = new Point(a*A.x + b*B.x + c*C.x, a*A.y + b*B.y +
        c*C.y , a*A.z + b*B.z + c*C.z);
    return p;
  }
  public static Point average(Point P, Point Q){
// punto medio fra due punti
    return (P.sum(Q)).per(0.5);
  }
  public static Point average(Point P, Point Q, Point U){
// baricentro di tre punti
    return (P.sum(Q).sum(U)).per(1/3.0);
  }

  public static int round(double x){
// round() arrotonda valori double all’intero piu’ vicino
// e’ utilizzata per passare dalle corrdinate dello spazio
    //(double)
// alle coordinate della pixelMatrix (int)
    return (int) (Math.round(x));
  }
}