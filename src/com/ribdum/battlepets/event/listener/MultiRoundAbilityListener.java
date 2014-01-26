package com.ribdum.battlepets.event.listener;

import com.ribdum.battlepets.PlayerChoice;
import com.ribdum.battlepets.PlayerChoice.AbilityChoice;
import com.ribdum.battlepets.ability.Ability;
import com.ribdum.battlepets.event.Event;
import com.ribdum.battlepets.event.EventType;
import com.ribdum.battlepets.event.SkipIfSimilar;
import com.ribdum.battlepets.player.Player;

public class MultiRoundAbilityListener extends EventListener implements
		SkipIfSimilar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4423564333949262092L;
	private int roundsRemaining;
	private Ability ability;
	private Player player;

	public MultiRoundAbilityListener(int rounds, Ability ability, Player player) {
		this.roundsRemaining = rounds;
		this.ability = ability;
		this.player = player;
	}

	@Override
	public void execute(Event event) {
		switch (event.getEventType()) {
		case END_OF_ROUND:
			this.roundsRemaining--;
			if (this.roundsRemaining < 1) {
				this.expired = true;
			}
			break;
		case PLAYER_CHOICE:
			if (event.getSource() instanceof PlayerChoice.AbilityChoice) {
				PlayerChoice.AbilityChoice source = (AbilityChoice) event
						.getSource();
				if (this.player.equals(source.player)) {
					source.player.setSkipChoice(true);
					source.player.setSelectedAbility(this.ability);
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public EventType[] getListenedEvents() {
		return new EventType[] { EventType.END_OF_ROUND,
				EventType.PLAYER_CHOICE };
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null || !(obj instanceof MultiRoundAbilityListener))
			return false;
		MultiRoundAbilityListener obj0 = (MultiRoundAbilityListener) obj;
		return (this.ability.equals(obj0.ability));
	}

}
