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
			controller = new Mediator(view, model);
			view = new GUI();
			model = new DiseaseManipulator();
			
			
			view.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
