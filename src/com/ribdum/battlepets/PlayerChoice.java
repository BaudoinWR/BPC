package com.ribdum.battlepets;

import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventDispatcher;
import com.ribdum.battlepets.event.EventType;
import com.ribdum.battlepets.player.Player;

public class PlayerChoice {

	public static void selectPets(Player player1, Player player2)
			throws InterruptedException, InstantiationException,
			IllegalAccessException {
		playerChoice(player1, player2, PetChoice.class);
	}

	public static void selectAbilities(Player player1, Player player2)
			throws InstantiationException, IllegalAccessException,
			InterruptedException {
		playerChoice(player1, player2, AbilityChoice.class);
	}

	private static void playerChoice(Player player1, Player player2,
			Class<? extends PlayerInterraction> interraction)
			throws InterruptedException, InstantiationException,
			IllegalAccessException {

		player1.makeNotReady();
		player2.makeNotReady();

		spawnThread(interraction, player1);
		spawnThread(interraction, player2);

		while (!player1.isReady() || !player2.isReady()) {
			System.out.println("Waiting for players to be ready");
			Thread.sleep(1000);
		}
	}

	private static void playerChoice(Player player,
			Class<PetChoice> interraction) throws InstantiationException,
			IllegalAccessException, InterruptedException {

		player.makeNotReady();

		spawnThread(interraction, player);

		while (!player.isReady()) {
			System.out.println("Waiting for players to be ready");
			Thread.sleep(1000);
		}

	}

	private static void spawnThread(
			Class<? extends PlayerInterraction> interraction, Player player)
			throws InstantiationException, IllegalAccessException {
		PlayerInterraction playerInterration = interraction.newInstance();
		playerInterration.setPlayer(player);
		Thread player1Selection = new Thread(playerInterration);
		player1Selection.start();
	}

	abstract static class PlayerInterraction implements Runnable {
		public Player player;

		public void setPlayer(Player player) {
			this.player = player;
		}

		@Override
		public void run() {
			EventDispatcher.fireEvent(new Event(EventType.PLAYER_CHOICE, this));
			if (!this.player.skipChoice()) {
				this.choose();
			}
			this.player.setSkipChoice(false);
			player.makeReady();
		}

		protected abstract void choose();
	};

	public static class PetChoice extends PlayerInterraction {
		protected PetChoice() {
		}

		@Override
		protected void choose() {
			player.selectPets();
		}
	}

	public static class AbilityChoice extends PlayerInterraction {
		protected AbilityChoice() {
		}

		@Override
		protected void choose() {
			AbilitySlot slot = player.selectAbility();
			if (slot == null) {
				player.setSelectedAbility(null);
				return;
			}
			if (slot == AbilitySlot.SWITCH_PET) {
				selectPets(player);
			}
			player.setSelectedAbility(player.getActivePet().getAbilities()
					.get(slot));
		}

	}

	public static void selectPets(Player player) {
		try {
			playerChoice(player, PetChoice.class);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
