package objects;

public class PlaneType {

	private int maxpassengers;
	private double maxweight;
	private double maxvolume;

	/**
	 * @param maxpassengers the maximum number of passengers in a plane
	 * @param maxweight the maximum weight a plane can carry
	 * @param maxvolume the maximum volume capacity of the plane
	 */
	public PlaneType(int maxpassengers, double maxweight, double maxvolume) {
		setMaxpassengers(maxpassengers);
		setMaxweight(maxweight);
		setMaxvolume(maxvolume);
	}
	
	/**
	 * @return maximum number of passengers
	 */
	public int getMaxpassengers() {
		return maxpassengers;
	}

	/**
	 * sets the maximum number of passengers
	 * @param maxpassengers
	 */
	public void setMaxpassengers(int maxpassengers) {
		this.maxpassengers = maxpassengers;
	}

	/**
	 * get the maximum weight the plane coud carry
	 * @return
	 */
	public double getMaxweight() {
		return maxweight;
	}

	/**
	 * set maximum weight the plane could carry
	 * @param maxweight 
	 */
	public void setMaxweight(double maxweight) {
		this.maxweight = maxweight;
	}

	/**
	 * @return the maximum volume capacity of the plane
	 */
	public double getMaxvolume() {
		return maxvolume;
	}

	/**
	 * sets the volume capacity of the plane
	 * @param maxvolume
	 */
	public void setMaxvolume(double maxvolume) {
		this.maxvolume = maxvolume;
	}

}
