package com.ribdum.battlepets.ability;

import com.ribdum.battlepets.ability.effect.DoTEffect;
import com.ribdum.battlepets.ability.effect.Effect;

public class PoisonAbility extends Ability {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9039022708595443293L;

	@Override
	protected Effect[] getEffects() {
		return new Effect[] { new DoTEffect(PoisonAbility.class, 25, 3) };
	}

	@Override
	public String getName() {
		return "Poison";
	}

}
