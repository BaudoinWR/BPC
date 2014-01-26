package com.ribdum.battlepets.player;

import java.util.Map;

import com.ribdum.battlepets.RandomGenerator;
import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.pet.Moth;
import com.ribdum.battlepets.pet.Pet;
import com.ribdum.battlepets.pet.PetSlot;
import com.ribdum.battlepets.pet.Wolgar;

public class ComputerPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComputerPlayer() {
		super();
		this.pets.put(PetSlot.FIRST_SLOT, new Wolgar(this));
		this.pets.put(PetSlot.SECOND_SLOT, new Moth(this));
	}

	@Override
	public void selectPets() {
		Pet pet = pets.get(PetSlot.FIRST_SLOT);
		if (!pet.isAlive()) {
			pet = pets.get(PetSlot.SECOND_SLOT);
		}
		this.activePet = pet;
		System.out.println("Computer player selects " + activePet.getName());
	}

	@Override
	public AbilitySlot selectAbility() {
		Map<AbilitySlot, Ability> abilities = this.activePet.getAbilities();
		Ability ability1 = abilities.get(AbilitySlot.FIRST_SLOT);
		Ability ability2 = abilities.get(AbilitySlot.SECOND_SLOT);
		Ability ability3 = abilities.get(AbilitySlot.THIRD_SLOT);
		boolean ability1Available = ability1.isAvailable();
		boolean ability2Available = ability2.isAvailable();
		boolean ability3Available = ability3.isAvailable();
		if (ability1Available
				&& ((!ability2Available && !ability3Available) || RandomGenerator
						.isSuccess(33))) {
			return AbilitySlot.FIRST_SLOT;
		}
		if (ability2Available
				&& (!ability3Available || RandomGenerator.isSuccess(50))) {
			return AbilitySlot.SECOND_SLOT;
		}
		if (ability3Available) {
			return AbilitySlot.THIRD_SLOT;
		}
		return null;
	}
}
