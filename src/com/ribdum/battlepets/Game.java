package com.ribdum.battlepets;

import java.io.Serializable;

import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventDispatcher;
import com.ribdum.battlepets.event.EventType;
import com.ribdum.battlepets.event.listener.SwitchPetOnDeathListener;
import com.ribdum.battlepets.gameinterface.HumanPlayerInterface;
import com.ribdum.battlepets.player.ComputerPlayer;
import com.ribdum.battlepets.player.HumanPlayer;
import com.ribdum.battlepets.player.Player;

public class Game implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6982289103096543438L;
	protected Player player1;
	protected Player player2;
	private int round = 1;
	private HumanPlayerInterface hPInterface;

	public Game() {
	}

	public Game(HumanPlayerInterface hPInterface) {
		this.hPInterface = hPInterface;
	}

	public void run() {
		EventDispatcher.registerListener(new SwitchPetOnDeathListener());
		this.selectPlayers();
		try {
			PlayerChoice.selectPets(player1, player2);

			while (player1.hasPetsAlive() && player2.hasPetsAlive()) {
				this.round();
			}
			this.end();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void end() {
		System.out.println("End of the game");
	}

	private void round() throws InstantiationException, IllegalAccessException,
			InterruptedException {
		System.out.println("Start of round " + round);
		PlayerChoice.selectAbilities(player1, player2);
		this.resolveAbilities();
		System.out.println("End of round " + round++);
		EventDispatcher.fireEvent(new Event(EventType.END_OF_ROUND, this));
	}

	private void resolveAbilities() {
		Ability selectedAbility = this.player1.getSelectedAbility();
		if (selectedAbility != null) {
			selectedAbility.execute(this.player1, this.player2);
		}
		if (player2.getActivePet().isAlive()) {
			selectedAbility = this.player2.getSelectedAbility();
			if (selectedAbility != null) {
				selectedAbility.execute(this.player2, this.player1);
			}
		}
	}

	protected void selectPlayers() {
		this.player1 = new HumanPlayer(hPInterface);
		this.player2 = new ComputerPlayer();
	}

	public static void main(String[] args) {
		Game game = new Game() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 409649019936518881L;

			protected void selectPlayers() {
				player1 = new ComputerPlayer();
				player2 = new ComputerPlayer();
			}
		};
		game.run();
	}
}
