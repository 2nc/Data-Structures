/**
 * @author wnc
 */

public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body anoB){
		double dx = anoB.xxPos - xxPos;
		double dy = anoB.yyPos - yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Body anoB){
		double distance = calcDistance(anoB);
		double force = G * mass * anoB.mass / (distance * distance);
		return force;
	}

	public double calcForceExertedByX(Body anoB){
		double dx = anoB.xxPos - xxPos;
		return calcForceExertedBy(anoB) * dx / calcDistance(anoB);
		
	}

	public double calcForceExertedByY(Body anoB){
		double dy = anoB.yyPos - yyPos;
		return calcForceExertedBy(anoB) * dy / calcDistance(anoB);
	}

	public double calcNetForceExertedByX(Body[] bodies){
		double totalF = 0;
		for(Body oneB : bodies){
			if(!oneB.equals(this)){
				totalF = totalF + calcForceExertedByX(oneB);
			}
		}
		return totalF;
	}

	public double calcNetForceExertedByY(Body[] bodies){
		double totalF = 0;
		for(Body oneB : bodies){
			if(!oneB.equals(this)){
				totalF = totalF + calcForceExertedByY(oneB);
			}
		}
		return totalF;
	}

	public void update(double dt, double fX, double fY){
		double accX = fX / mass;
		double accY = fY / mass;
		xxVel += dt * accX;
		yyVel += dt * accY;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
		StdDraw.show();
	}
}