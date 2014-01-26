package com.ribdum.battlepets.pet;

import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.ability.BiteAbility;
import com.ribdum.battlepets.ability.ClawAbility;
import com.ribdum.battlepets.ability.HealingTouchAbility;
import com.ribdum.battlepets.player.Player;

public class Wolgar extends Pet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8763108490542110552L;

	public Wolgar(Player player) {
		super(player);
		this.abilities.put(AbilitySlot.FIRST_SLOT, new BiteAbility());
		this.abilities.put(AbilitySlot.SECOND_SLOT, new ClawAbility());
		this.abilities.put(AbilitySlot.THIRD_SLOT, new HealingTouchAbility());
	}

	@Override
	protected int getMaxHitPoints() {
		return 500;
	}

	@Override
	public String getName() {
		return "Wolgar";
	}
}
