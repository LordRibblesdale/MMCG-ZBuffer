class Light extends Point{
  // sorgente luminosa puntiforme
  public double i; // intensit\‘{a} della sorgete luminosa
  public double q, l, c; // coefficienti di decadimento
  //{quadratico, lineare e costante} */
  public Light(double x, double y, double z, double intensity){
    super(x, y, z);
    i = intensity;
    c=0;
    q = 1;
    l = 0;
  }
  public double getAtt(double r){
// getAtt() restituisce l’attenuazione dovuta dalla
//    distanza, usando i coefficienti
// caratteristici della sorgete luminosa
    double att = (c + l*r + q*r*r);
    if(att<1)
      return 1;
    return 1/att;
  }
}