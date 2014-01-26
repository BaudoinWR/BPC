package com.ribdum.battlepets.event.listener;

import com.ribdum.battlepets.PlayerChoice;
import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventType;
import com.ribdum.battlepets.pet.Pet;
import com.ribdum.battlepets.player.Player;

public class SwitchPetOnDeathListener extends EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5609966111057434265L;

	@Override
	public void execute(Event event) {
		if (event.getSource() instanceof Pet) {
			Pet source = (Pet) event.getSource();
			Player player = source.getPlayer();
			player.setSelectedAbility(null);
			if (player.hasPetsAlive()) {
				PlayerChoice.selectPets(player);
			}
		}
	}

	@Override
	public EventType[] getListenedEvents() {
		return new EventType[] { EventType.PET_DIES };
	}

}
