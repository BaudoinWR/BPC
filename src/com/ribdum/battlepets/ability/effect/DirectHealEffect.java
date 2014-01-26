package com.ribdum.battlepets.ability.effect;

import com.ribdum.battlepets.player.Player;

public class DirectHealEffect extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1589692718152016847L;
	private int heal;

	public DirectHealEffect(int heal) {
		this.heal = heal;
	}

	@Override
	public void execute(Player attackingPlayer, Player defendingPlayer) {
		attackingPlayer.getActivePet().getHealed(heal);
	}

}
