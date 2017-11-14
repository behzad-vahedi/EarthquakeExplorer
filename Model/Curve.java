/**
 * This is an interface for lines and line-strings
 * Based on OGC Simple Features specification
 * Curve OGC definition: A Curve is a 1-dimensional geometric object usually stored as a sequence of Points, 
 * with the subtype of Curve specifying the form of the interpolation between Points.
 * class LineString implements Curve
 * interface curve has four methods: length, startPoint, endPoint and isClosed (in accordance with OGC)
 * 
 * classes that share implementations: LineString (implements Curve) because it is composed of points 
 * this is in accordance with OGC standards (see UML diagram)
 */

package Model;

public interface Curve extends Geometry {
	
	// return the length of a curve
	public double length();
	
	// returns the first node (starting point) of a curve
	public OurPoint startPoint();
	
	// returns the last node (ending point) of a curve
	public OurPoint endPoint();
	
	// checks whether or not a curve is closed
	public boolean isClosed();

}
