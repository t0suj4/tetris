package tw.ntustgrp12.tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter {
	private final GameLogic gameLogic;

	public GameController(GameLogic gameLogic)
	{
		this.gameLogic = gameLogic;
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			gameLogic.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			gameLogic.moveRight();
			break;
		case KeyEvent.VK_DOWN:
			gameLogic.moveDown();
			break;
		case KeyEvent.VK_UP:
			gameLogic.rotate();
			break;
		case KeyEvent.VK_SPACE:
			gameLogic.land();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		

	}

	@Override
	public void keyTyped(KeyEvent key) {
		

	}

}
