package com.ribdum.battlepets.ability.effect;

import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.event.EventDispatcher;
import com.ribdum.battlepets.event.listener.DoTListener;
import com.ribdum.battlepets.player.Player;

public class DoTEffect extends Effect {

	private final int damage;
	private final int rounds;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4846302288406159455L;
	private Class<? extends Ability> abilityClass;

	public DoTEffect(Class<? extends Ability> abilityClass, int damage,
			int rounds) {
		this.abilityClass = abilityClass;
		this.damage = damage;
		this.rounds = rounds;
	}

	@Override
	public void execute(Player attackingPlayer, Player defendingPlayer) {
		EventDispatcher.registerListener(new DoTListener(defendingPlayer
				.getActivePet(), damage, rounds, abilityClass));
	}
}
