package com.ribdum.battlepets.event.listener;

import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventType;

public class CooldownListener extends EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6420695765711455751L;
	protected final int cooldown;
	private boolean isFirstRound = true;
	private Ability ability;

	public CooldownListener(Ability ability, int cooldown) {
		this.ability = ability;
		this.cooldown = cooldown;
	}

	@Override
	public void execute(Event event) {
		if (isFirstRound) {
			this.ability.setRemainingCooldown(this.cooldown);
			isFirstRound = false;
			return;
		}

		if (this.ability.getRemainingCooldown() > 0) {
			this.ability.setRemainingCooldown(this.ability
					.getRemainingCooldown() - 1);
		}

		if (this.ability.getRemainingCooldown() == 0) {
			this.expired = true;
		}
	}

	@Override
	public EventType[] getListenedEvents() {
		return new EventType[] { EventType.END_OF_ROUND };
	}

}
