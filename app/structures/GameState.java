package structures;

import structures.basic.Card;
import structures.basic.Player;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

/**
 * This class can be used to hold information about the on-going game.
 * Its created with the GameActor.
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class GameState {

	
	public boolean gameInitalised = false;
	
	public boolean something = false;

	public boolean tileSelected = false;

	public int turn = 1;

	//game board
	public Board myBoard = new Board();

	//human and AI avatar
	public Unit humanAvatar = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
	public Unit aiAvatar = BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 1, Unit.class);

	// card hailstone_golem
	public Card hailstone_golem = BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 0, Card.class);

	// AI and human player
	public Player humanPlayer = new Player(20, 2);
	public Player aiPlayer = new Player(20, 2);
}
