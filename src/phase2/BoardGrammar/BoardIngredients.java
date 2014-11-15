package phase2.BoardGrammar;

import java.util.ArrayList;
import java.util.List;

import phase2.Board.Ball;
import phase2.Board.Gadget;

/**
 * An immutable class which contains the necessary input arguments to create a board
 * from a board file.
 *
 */
public class BoardIngredients {
	/*
	 * Abstraction function: a series of elements that can be used to generate a board
	 */
	
	private final List<Gadget> gadgetList;
	private final String name;
	private final double gravity;
	private final double mu;
	private final double mu2;
	private final List<Ball> ballList;
	
	public BoardIngredients(List<Gadget> gadgetList, List<Ball> ballList, String name, 
			double gravity, double mu, double mu2){
		this.gadgetList = new ArrayList<>(gadgetList);
		this.name = name;
		this.ballList = new ArrayList<>(ballList);
		this.gravity = gravity;
		this.mu = mu;
		this.mu2 = mu2;
	}

	public List<Gadget> getGadgetList() {
		return new ArrayList<>(gadgetList);
	}

	public String getName() {
		return name;
	}

	public double getGravity() {
		return gravity;
	}

	public double getMu() {
		return mu;
	}

	public double getMu2() {
		return mu2;
	}

	public List<Ball> getBallList() {
		return new ArrayList<>(ballList);
	}

}
