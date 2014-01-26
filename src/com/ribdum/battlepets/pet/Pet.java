package com.ribdum.battlepets.pet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventDispatcher;
import com.ribdum.battlepets.event.EventType;
import com.ribdum.battlepets.player.Player;

public abstract class Pet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4908544980398528977L;
	protected int hitPoints;
	protected Map<AbilitySlot, Ability> abilities;
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public Pet(Player player) {
		this.player = player;
		this.hitPoints = getMaxHitPoints();
		this.abilities = new HashMap<AbilitySlot, Ability>();
	}

	protected abstract int getMaxHitPoints();

	public abstract String getName();

	public boolean isAlive() {
		return this.hitPoints > 0;
	}

	public void getDamaged(int hit) {
		System.out.println(getName() + " receives " + hit + " damage.");
		this.hitPoints -= hit;
		System.out.println(getName() + " has " + hitPoints + " left.");
		if (!isAlive()) {
			System.out.println(getName() + " dies.");
			EventDispatcher.fireEvent(new Event(EventType.PET_DIES, this));
		}
	}

	public Map<AbilitySlot, Ability> getAbilities() {
		return this.abilities;
	}

	public void getHealed(int heal) {
		if (this.hitPoints + heal > this.getMaxHitPoints()) {
			heal = this.getMaxHitPoints() - this.hitPoints;
		}
		System.out.println(getName() + " receives " + heal + " health.");
		this.hitPoints += heal;
	}

}
