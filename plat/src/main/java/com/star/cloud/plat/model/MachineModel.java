package com.star.cloud.plat.model;

import java.util.LinkedList;
import java.util.List;

import com.star.cloud.plat.util.IDUtil;

public class MachineModel {
	
	private final Machine machine;
	
	private static final String MODEL_TYPE_QF_V1100 = "QuickFire T-Video V1100";
	private static final String MODEL_TYPE_QF_V1200 = "QuickFire T-Video V1200";
	private static final String MODEL_TYPE_QF_V2200 = "QuickFire T-Video V2200";
	private static final String MODEL_TYPE_COMMON = "common";
	
	public MachineModel(Machine machine) {
		checkModel(machine.getModel());
		this.machine = machine;
	}
	
	private void checkModel(String name) {
		if (!MODEL_TYPE_QF_V1100.equals(name) && !MODEL_TYPE_QF_V1200.equals(name)
				&& !MODEL_TYPE_QF_V2200.equals(name) && !MODEL_TYPE_COMMON.equals(name)) {
			throw new StarCloudPlatException("Illegal name for MachineModel: " + String.valueOf(name));
		}
	}
	
	public List<Card> getCards() {
		if (machine.getMaster() == null) {
			return generateCards();
		} else {
			return collectCards();
		}
	}
	
	private List<Card> collectCards() {
		List<Card> cards = new LinkedList<Card>();
		
		Card master = machine.getMaster();
		master.setId(IDUtil.generateID());
		master.setMachine(machine);
		cards.add(master);
		for (Card w : machine.getWorkers()) {
			w.setId(IDUtil.generateID());
			w.setMachine(machine);
		}
		cards.addAll(machine.getWorkers());
		return cards;
	}
	
	private List<Card> generateCards() {
		List<Card> cards = new LinkedList<Card>();
		String modelName = machine.getModel();
		if (MODEL_TYPE_QF_V1100.equals(modelName) || MODEL_TYPE_QF_V1200.equals(modelName)) {
			cards.add(createMaster());
			for (int i = 0; i < 10; ++i) {
				cards.add(createWorker(i));
			}
		} else if (MODEL_TYPE_QF_V2200.equals(modelName)) {
			cards.add(createMaster());
			for (int i = 0; i < 8; ++i) {
				cards.add(createWorker(i));
			}
		} else if (MODEL_TYPE_COMMON.equals(modelName)) {
			cards.add(createMaster());
		}
		return cards;
	}
	
	private Master createMaster() {
		String name = machine.getName() + "-Master";
		Master m = new Master();
		m.setId(IDUtil.generateID());
		m.setName(name);
		m.setMachine(machine);
		return m;
	}
	
	private Worker createWorker(int num) {
		String name = machine.getName() + "-Worker-" + num;
		Worker w = new Worker();
		w.setId(IDUtil.generateID());
		w.setName(name);
		w.setMachine(machine);
		return w;
	}

}
