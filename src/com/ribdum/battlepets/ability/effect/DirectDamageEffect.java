package com.ribdum.battlepets.ability.effect;

import com.ribdum.battlepets.pet.Pet;
import com.ribdum.battlepets.player.Player;

public class DirectDamageEffect extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2592133338003618106L;
	private int damage;

	public DirectDamageEffect(int damage) {
		this.damage = damage;
	}

	@Override
	public void execute(Player attackingPlayer, Player defendingPlayer) {
		Pet activePet = defendingPlayer.getActivePet();
		activePet.getDamaged(damage);
	}

}
