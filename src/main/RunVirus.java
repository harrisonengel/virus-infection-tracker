package main;

import controller.Mediator;
import model.DiseaseManipulator;
import view.GUI;

public class RunVirus {
	private static GUI view;
	private static DiseaseManipulator model;
	private static Mediator controller;
	
	public static void main(String[] args) {
		try {
			controller = new Mediator();
			view = new GUI();
			model = new DiseaseManipulator();
			controller.setView(view);
			controller.setModel(model);
			model.setController(controller);
			view.setController(controller);
			controller.addActionListeners(view);
			view.initialize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
