package core;

public class Luggage {

	private double accum_volume = 0;
	private double accum_weight = 0;
	private double accum_excessfees = 0;
	private int accum_numberofpassengers = 0;

	/**
	 * @param accum_volume
	 * @param accum_weight
	 * @param accum_excessfees
	 * @param accum_numberofpassengers
	 */
	public Luggage(double accum_volume, double accum_weight, double accum_excessfees, int accum_numberofpassengers) {
		setAccum_volume(accum_volume);
		setAccum_weight(accum_weight);
		setAccum_excessfees(accum_excessfees);
		setAccum_numberofpassengers(accum_numberofpassengers);
	}

	/**
	 * @return
	 */
	public double getAccum_volume() {
		return accum_volume;
	}

	/**
	 * @param accum_volume
	 */
	public void setAccum_volume(double accum_volume) {
		this.accum_volume += accum_volume;
	}

	/**
	 * @return
	 */
	public double getAccum_weight() {
		return accum_weight;
	}

	/**
	 * @param accum_weight
	 */
	public void setAccum_weight(double accum_weight) {
		this.accum_weight += accum_weight;
	}

	/**
	 * @return
	 */
	public double getAccum_excessfees() {
		return accum_excessfees;
	}

	/**
	 * @param accum_excessfees
	 */
	public void setAccum_excessfees(double accum_excessfees) {
		this.accum_excessfees += accum_excessfees;
	}

	/**
	 * @return
	 */
	public int getAccum_numberofpassengers() {
		return accum_numberofpassengers;
	}

	/**
	 * @param accum_numberofpassengers
	 */
	public void setAccum_numberofpassengers(int accum_numberofpassengers) {
		this.accum_numberofpassengers += accum_numberofpassengers;
	}

}
