/**
 * @author wnc
 */

public class TestBody{
	public static void main(String[] args){
		Body sun = new Body(1.0e12, 2.0e11, 1, 2, 2.0e30, "sun");
		Body saturn = new Body(2.3e12, 9.5e11, 2, 3, 6.0e26, "saturn");
		double force = sun.calcForceExertedBy(saturn);
		double forceC = 6.67e-11 * 2.0e30 * 6.0e26 / (1.3e12 * 1.3e12 + 7.5e11 * 7.5e11);
		if(force == forceC){
			System.out.print("Test passed. The force is " + force);
		}else{
			System.out.print("Test fail. Your force is " + force + ".But the true value is " + forceC);
		}

	}
}