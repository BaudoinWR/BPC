package com.ribdum.battlepets.gameinterface;

import java.io.Serializable;
import java.util.Map;

import com.ribdum.battlepets.ability.AbilitySlot;
import com.ribdum.battlepets.pet.Pet;
import com.ribdum.battlepets.pet.PetSlot;
import com.ribdum.battlepets.player.HumanPlayer;

public interface HumanPlayerInterface extends Serializable {

	public Map<PetSlot, Pet> selectPetsScreen(HumanPlayer humanPlayer);

	public AbilitySlot selectAbilityScreen(HumanPlayer humanPlayer);

}
