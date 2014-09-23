package com.star.cloud.plat.dao;

import java.util.List;

import com.star.cloud.plat.model.Card;
import com.star.cloud.plat.model.Machine;

public interface IMachineDao {
	
	Machine queryMachine(String id, String name);

	Card queryCard(String id, String name);
	
	List<Card> queryCardUnderMachine(String machineId);

	void insertMachine(Machine machine);

	void deleteMachineByIdName(String id, String name);
	
	void insertCard(Card card);
	
	void insertCards(List<Card> cards);

	void updateCard(Card card);
	
	void updateCards(List<Card> cards);
	
}
