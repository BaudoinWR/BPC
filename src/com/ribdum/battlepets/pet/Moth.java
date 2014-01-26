package com.ribdum.battlepets.pet;

import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.ability.HealingTouchAbility;
import com.ribdum.battlepets.ability.PoisonAbility;
import com.ribdum.battlepets.ability.RampageAbility;
import com.ribdum.battlepets.player.Player;

public class Moth extends Pet {

	public Moth(Player player) {
		super(player);
		this.abilities.put(AbilitySlot.FIRST_SLOT, new PoisonAbility());
		this.abilities.put(AbilitySlot.SECOND_SLOT, new RampageAbility());
		this.abilities.put(AbilitySlot.THIRD_SLOT, new HealingTouchAbility());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 28305724076575313L;

	@Override
	protected int getMaxHitPoints() {
		return 700;
	}

	@Override
	public String getName() {
		return "Moth";
	}

}
