package objects;

public class PlaneType {

	private int maxpassengers;
	private double maxweight;
	private double maxvolume;

	/**
	 * @param maxpassengers
	 * @param maxweight
	 * @param maxvolume
	 */
	public PlaneType(int maxpassengers, double maxweight, double maxvolume) {
		setMaxpassengers(maxpassengers);
		setMaxweight(maxweight);
		setMaxvolume(maxvolume);
	}
	
	/**
	 * @return
	 */
	public int getMaxpassengers() {
		return maxpassengers;
	}

	/**
	 * @param maxpassengers
	 */
	public void setMaxpassengers(int maxpassengers) {
		this.maxpassengers = maxpassengers;
	}

	/**
	 * @return
	 */
	public double getMaxweight() {
		return maxweight;
	}

	/**
	 * @param maxweight
	 */
	public void setMaxweight(double maxweight) {
		this.maxweight = maxweight;
	}

	/**
	 * @return
	 */
	public double getMaxvolume() {
		return maxvolume;
	}

	/**
	 * @param maxvolume
	 */
	public void setMaxvolume(double maxvolume) {
		this.maxvolume = maxvolume;
	}

}
