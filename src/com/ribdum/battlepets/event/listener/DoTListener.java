package com.ribdum.battlepets.event.listener;

import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventType;
import com.ribdum.battlepets.event.OverwriteSimilar;
import com.ribdum.battlepets.pet.Pet;

public class DoTListener extends EventListener implements OverwriteSimilar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6103474483392234246L;
	private Pet pet;
	private int damage;
	private int remainingRounds;
	private Class<? extends Ability> abilityClass;

	public DoTListener(Pet pet, int damage, int rounds,
			Class<? extends Ability> abilityClass) {
		this.pet = pet;
		this.damage = damage;
		this.remainingRounds = rounds;
		this.abilityClass = abilityClass;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof DoTListener))
			return false;
		DoTListener obj0 = (DoTListener) obj;
		return (this.pet.equals(obj0.pet) && this.abilityClass == obj0.abilityClass);
	}

	@Override
	public void execute(Event event) {
		switch (event.getEventType()) {
		case END_OF_ROUND:
			this.pet.getDamaged(damage);
			this.remainingRounds--;
			if (this.remainingRounds < 1) {
				this.expired = true;
			}
			break;
		case PET_DIES:
			if (this.pet.equals(event.getSource())) {
				this.expired = true;
			}
		default:
			break;
		}
	}

	@Override
	public EventType[] getListenedEvents() {
		return new EventType[] { EventType.END_OF_ROUND, EventType.PET_DIES };
	}

}
