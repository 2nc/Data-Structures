/**
 * @author wnc
 */

public class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);

		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String fileName){
		In in = new In(fileName);

		int num = in.readInt();
		double radius = in.readDouble();
		Body[] b = new Body[num];
		for(int i = 0; i < num; i++){
			b[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), 
				in.readDouble(), in.readDouble(), in.readString());
		}
		return b;
	}

	public static void main(String[] args){
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1].toString());
		String fileName = args[2];
		double radius = readRadius(fileName);
		Body[] b = readBodies(fileName);
		/*Draw the universe*/
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		String backGround = "/images/starfield.jpg";
		StdDraw.picture(0, 0, backGround, 2 * radius, 2 * radius);
		StdDraw.show();
		/*Draw planets*/
		for(Body sinB: b){
			sinB.draw();
		}

		for(double i = 0; i < T; i = i + dt){
			double xForces[] = new double[b.length];
			double yForces[] = new double[b.length];
			for(int j = 0; j < b.length; j++){
				xForces[j] = b[j].calcNetForceExertedByX(b);
				yForces[j] = b[j].calcNetForceExertedByY(b);
			}
			for(int j = 0; j < b.length; j++){
				b[j].update(dt, xForces[j], yForces[j]);
			}
			StdDraw.picture(0, 0, backGround, 2 * radius, 2 * radius);
			for(Body sinB: b){
				sinB.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", b.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < b.length; i++) {
   			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            b[i].xxPos, b[i].yyPos, b[i].xxVel,
            b[i].yyVel, b[i].mass, b[i].imgFileName);
        }   
}}