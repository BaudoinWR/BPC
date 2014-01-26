package com.ribdum.battlepets.ability;

import java.io.Serializable;

import com.ribdum.battlepets.ability.effect.Effect;
import com.ribdum.battlepets.event.EventDispatcher;
import com.ribdum.battlepets.event.listener.EventListener;
import com.ribdum.battlepets.player.Player;

public abstract class Ability implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5166372359153231033L;
	private Effect[] effects;
	private int remainingCooldown;

	public Ability() {
		this.effects = getEffects();
	}

	protected abstract Effect[] getEffects();

	public abstract String getName();

	public void execute(Player attackingPlayer, Player defendingPlayer) {
		for (Effect effect : effects) {
			effect.execute(attackingPlayer, defendingPlayer);
		}
		EventListener[] registeredListeners = this.getRegisteredListeners(
				attackingPlayer, defendingPlayer);
		for (EventListener eventListener : registeredListeners) {
			EventDispatcher.registerListener(eventListener);
		}
	}

	protected EventListener[] getRegisteredListeners(Player attackingPlayer,
			Player defendingPlayer) {
		return new EventListener[0];
	}

	public int getRemainingCooldown() {
		return remainingCooldown;
	}

	public void setRemainingCooldown(int remainingCooldown) {
		this.remainingCooldown = remainingCooldown;
	}

	public boolean isAvailable() {
		return this.remainingCooldown < 1;
	}

}
