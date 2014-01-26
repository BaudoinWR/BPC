package com.ribdum.battlepets.player;

import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.gameinterface.HumanPlayerInterface;
import com.ribdum.battlepets.pet.PetSlot;

public class HumanPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5419074218118952598L;
	private HumanPlayerInterface hPInterface;

	public HumanPlayer(HumanPlayerInterface hPInterface) {
		super();
		this.hPInterface = hPInterface;
	}

	@Override
	public void selectPets() {
		this.pets = hPInterface.selectPetsScreen(this);

		this.activePet = pets.get(PetSlot.FIRST_SLOT);
		System.out.println("Human player selects " + activePet.getName());
	}

	@Override
	public AbilitySlot selectAbility() {
		AbilitySlot abilitySlot = hPInterface.selectAbilityScreen(this);
		return abilitySlot;
	}
}
