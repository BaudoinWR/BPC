package com.ribdum.battlepets.ability;

import com.ribdum.battlepets.ability.effect.DirectDamageEffect;
import com.ribdum.battlepets.ability.effect.Effect;
import com.ribdum.battlepets.event.listener.CooldownListener;
import com.ribdum.battlepets.event.listener.EventListener;
import com.ribdum.battlepets.player.Player;

public class BiteAbility extends Ability {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1691891203689828518L;

	@Override
	public String getName() {
		return "Bite";
	}

	@Override
	protected Effect[] getEffects() {
		return new Effect[] { new DirectDamageEffect(100) };
	}

	@Override
	protected EventListener[] getRegisteredListeners(Player attackingPlayer,
			Player defendingPlayer) {
		return new EventListener[] { new CooldownListener(this, 2) };
	}

}
