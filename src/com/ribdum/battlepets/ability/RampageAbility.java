package com.ribdum.battlepets.ability;

import com.ribdum.battlepets.ability.effect.DirectDamageEffect;
import com.ribdum.battlepets.ability.effect.Effect;
import com.ribdum.battlepets.event.EventDispatcher;
import com.ribdum.battlepets.event.listener.MultiRoundAbilityListener;
import com.ribdum.battlepets.player.Player;

public class RampageAbility extends Ability {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3251323532720251743L;

	@Override
	protected Effect[] getEffects() {
		return new Effect[] { new DirectDamageEffect(75) };
	}

	@Override
	public String getName() {
		return "Rampage";
	}

	@Override
	public void execute(Player attackingPlayer, Player defendingPlayer) {
		EventDispatcher.registerListener(new MultiRoundAbilityListener(3, this,
				attackingPlayer));
		super.execute(attackingPlayer, defendingPlayer);
	}

}
