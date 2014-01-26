package com.ribdum.battlepets.ability;

import com.ribdum.battlepets.ability.effect.DirectDamageEffect;
import com.ribdum.battlepets.ability.effect.Effect;

public class ClawAbility extends Ability {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2548757893706238279L;

	@Override
	public String getName() {
		return "Claw";
	}

	@Override
	protected Effect[] getEffects() {
		return new Effect[] { new DirectDamageEffect(50) };
	}

}
