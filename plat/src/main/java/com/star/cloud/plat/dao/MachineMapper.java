package com.star.cloud.plat.dao;

import java.util.List;

import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.Machine;

public interface MachineMapper {
	
	Machine getMachine(String id, String name);
	
	Card getCard(String id, String name);
	
	List<Card> getCardsUnderMachine(String machineId);
	
}
