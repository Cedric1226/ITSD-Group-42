package demo;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import structures.basic.Tile;
import structures.basic.Unit;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

public class CheckMoveLogic {

	public static void executeDemo(ActorRef out) {
		
		// Draw two tiles to move between
		Tile tile = BasicObjectBuilders.loadTile(3, 2);
		BasicCommands.drawTile(out, tile, 0);	
		Tile tile2 = BasicObjectBuilders.loadTile(5, 4);
		BasicCommands.drawTile(out, tile2, 0);
	
		// Draw a unit
		Unit unit = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
		unit.setPositionByTile(tile); 
		BasicCommands.drawUnit(out, unit, tile);
		
		// Move unit, default, horizontal then vertical
		BasicCommands.moveUnitToTile(out, unit, tile2);
		unit.setPositionByTile(tile2); 
		
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		
		// Move unit, default, horizontal then vertical
		BasicCommands.moveUnitToTile(out, unit, tile);
		unit.setPositionByTile(tile); 
		
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		
		// Move unit, alternative, vertical then horizontal
		BasicCommands.moveUnitToTile(out, unit, tile2, true);
		unit.setPositionByTile(tile2); 
		
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		
		// Move unit, alternative, vertical then horizontal
		BasicCommands.moveUnitToTile(out, unit, tile, true);
		unit.setPositionByTile(tile); 
		
		BasicCommands.addPlayer1Notification(out, "drawCard [1u]", 1);
		Card hailstone_golem = BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 0, Card.class);
		BasicCommands.drawCard(out, hailstone_golem, 1, 0);
		BasicCommands.drawCard(out, hailstone_golem, 2, 0);
		BasicCommands.drawCard(out, hailstone_golem, 3, 0);
		BasicCommands.drawCard(out, hailstone_golem, 4, 0);
		BasicCommands.drawCard(out, hailstone_golem, 5, 0);
		BasicCommands.drawCard(out, hailstone_golem, 6, 0);
	}
	
}
