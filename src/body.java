
public class Body {
	// initialize private variables
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	
	// first constructor
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	// copy constructor
	public Body(Body b) {
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();	
	}
	
	//getter methods 
	public double getYVel() {
		return myYVel;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getX() {
		return myXPos; 
	}
	public double getY() {
		return myYPos;
	}
	public double getMass() {
		return myMass;
	}
	public String getName () {
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		double distance = Math.sqrt(((myXPos - b.getX()) * (myXPos - b.getX())) + ((myYPos - b.getY()) * (myYPos - b.getY())));
		return distance;
	}
	
	public double calcForceExertedBy(Body p) {
		double force = (6.67e-11) * ((myMass * p.getMass())/ (calcDistance(p)*calcDistance(p)));
		return force;
	}
	
	public double calcForceExertedByX(Body p) {
		double forceByX = calcForceExertedBy(p) * ((p.getX() - myXPos) / calcDistance(p));
		return forceByX;
	}
	
	public double calcForceExertedByY(Body p) {
		double forceByY = calcForceExertedBy(p) * ((p.getY() - myYPos) / calcDistance(p));
		return forceByY;	
	}
	
	public double calcNetForceExertedByX(Body [] bodies) {
		double netForceX = 0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				netForceX = netForceX + calcForceExertedByX(b);
			}
		}
	return netForceX;
	}
	
	public double calcNetForceExertedByY(Body [] bodies) {
		double netForceY = 0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				netForceY = netForceY + calcForceExertedByY(b);
			}
		}
	return netForceY; 
	}
	
	public void update( double deltaT, double xforce, double yforce) {
		double accelerationX = xforce / myMass; 
		double accelerationY = yforce / myMass;
		double nvx = myXVel + deltaT*accelerationX;
		double nvy = myYVel + deltaT*accelerationY;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;
		myXPos = nx;
		myYPos = ny; 
		myXVel = nvx;
		myYVel = nvy;	
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
