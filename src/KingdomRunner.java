
public class KingdomRunner {
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		System.setProperty("sun.java2d.opengl", "true");
		GraphicConstruct image = new GraphicConstruct("Kingdom Builder");
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000000.0);
		
	}
	
}
