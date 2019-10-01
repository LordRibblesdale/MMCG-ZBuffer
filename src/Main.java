public class Main {
  public static void main(String args[]) {
    RenderingEngine R = new RenderingEngine(720, 480, 700);
    //ZBuffer C = new ZBuffer("Rendered");
    //R = new RenderingEngine(720, 480, 700);
    RayTracingZ RZ = new RayTracingZ("Rendered");
  }
}
