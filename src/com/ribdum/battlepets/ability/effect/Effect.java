package com.ribdum.battlepets.ability.effect;

import java.io.Serializable;

import com.ribdum.battlepets.player.Player;

public abstract class Effect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5690058705839026859L;

	public abstract void execute(Player attackingPlayer, Player defendingPlayer);

}
