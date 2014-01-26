package com.ribdum.battlepets.ability;

import com.ribdum.battlepets.ability.effect.DirectHealEffect;
import com.ribdum.battlepets.ability.effect.Effect;
import com.ribdum.battlepets.event.listener.CooldownListener;
import com.ribdum.battlepets.event.listener.EventListener;
import com.ribdum.battlepets.player.Player;

public class HealingTouchAbility extends Ability {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3877164529491608042L;
	protected final int cooldown = 3;

	@Override
	protected Effect[] getEffects() {
		return new Effect[] { new DirectHealEffect(75) };
	}

	@Override
	public String getName() {
		return "Healing Touch";
	}

	@Override
	protected EventListener[] getRegisteredListeners(Player attackingPlayer,
			Player defendingPlayer) {
		return new EventListener[] { new CooldownListener(this, 3) };
	}

}
