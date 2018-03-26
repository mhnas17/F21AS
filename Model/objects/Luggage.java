package objects;

import exceptions.NegativeNumbers;

public class Luggage {

	private double accum_volume;
	private double accum_weight;
	private double accum_excessfees;
	private int accum_numberofpassengers;

	/**
	 * luggage object constructor 
	 * @param accum_volume the accumulated baggage volume for one flight
	 * @param accum_weight the accumulated baggage weight for one flight
	 * @param accum_excessfees the accumulated excess fees for one flight
	 * @param accum_numberofpassengers the number of passengers for one flight
	 * @throws Exception 
	 */
	public Luggage(double accum_volume, double accum_weight, double accum_excessfees, int accum_numberofpassengers) throws Exception {
		setAccum_volume(accum_volume);
		setAccum_weight(accum_weight);
		setAccum_excessfees(accum_excessfees);
		setAccum_numberofpassengers(accum_numberofpassengers);
	}

	/**
	 * @return the accumulated volume of bags for one flight
	 */
	public double getAccum_volume() {
		return accum_volume;
	}

	/**
	 * adds the bag volume for one passenger to the accumulated volume of the flight
	 * @param accum_volume bag volume for a single passenger
	 * @throws NegativeNumbers 
	 * @throws NoNumbers 
	 */
	public void setAccum_volume(double accum_volume) throws NegativeNumbers{
		if(accum_volume<0) throw new NegativeNumbers(accum_volume);
		this.accum_volume += accum_volume;
	}

	/**
	 * @return the accumulated bag weight for one flight
	 */
	public double getAccum_weight() {
		return accum_weight;
	}

	/**
	 *adds the bag weight of one passenger to the accumulated bags weight of the flight
	 * @param accum_weight bag weight for one passenger
	 * @throws NegativeNumbers 
	 * @throws NoNumbers 
	 */
	public void setAccum_weight(double accum_weight) throws NegativeNumbers{
		if(accum_weight<0) throw new NegativeNumbers(accum_weight);
		this.accum_weight += accum_weight;
	}

	/**
	 * @return the accumulated excess fees of one flight
	 */
	public double getAccum_excessfees() {
		return accum_excessfees;
	}

	/**
	 * adds the excess fees of one passenger to the accumulated excess fees of the flight
	 * @param accum_excessfees bag weight for one passenger
	 * @throws NegativeNumbers 
	 */
	public void setAccum_excessfees(double accum_excessfees) {
		this.accum_excessfees += accum_excessfees;
	}

	/**
	 * @return the number of passengers who have checked in one flight
	 */
	public int getAccum_numberofpassengers() {
		return accum_numberofpassengers;
	}

	/**
	 * adds one passenger to the total number of passengers of one flight
	 * @param accum_numberofpassengers one passenger
	 * @throws NegativeNumbers 
	 */
	public void setAccum_numberofpassengers(int accum_numberofpassengers) {
		this.accum_numberofpassengers += accum_numberofpassengers;
	}

}
