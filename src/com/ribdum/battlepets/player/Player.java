package com.ribdum.battlepets.player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.pet.Pet;
import com.ribdum.battlepets.pet.PetSlot;

public abstract class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4275264760081729244L;
	protected boolean ready;
	protected Map<PetSlot, Pet> pets;
	protected Pet activePet;
	protected Ability selectedAbility;
	private boolean skipChoice;

	public Player() {
		this.ready = true;
		this.pets = new HashMap<PetSlot, Pet>();
	}

	public boolean hasPetsAlive() {
		for (Pet pet : pets.values()) {
			if (pet.isAlive()) {
				return true;
			}
		}
		return false;
	}

	public abstract void selectPets();

	public boolean isReady() {
		return ready;
	}

	public Map<PetSlot, Pet> getPets() {
		return pets;
	}

	public void makeNotReady() {
		this.ready = false;
	}

	public void makeReady() {
		this.ready = true;
	}

	public abstract AbilitySlot selectAbility();

	public Ability getSelectedAbility() {
		return selectedAbility;
	}

	public Pet getActivePet() {
		return activePet;
	}

	public boolean skipChoice() {
		return skipChoice;
	}

	public void setSkipChoice(boolean skipChoice) {
		this.skipChoice = skipChoice;
	}

	public void setSelectedAbility(Ability ability) {
		selectedAbility = ability;
	}

}
