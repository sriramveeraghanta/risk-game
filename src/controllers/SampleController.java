package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import models.SampleModel;

import views.SampleView;

/**
 * 
 * EXAMPLE LINK http://www.austintek.com/mvc/
 */

public class SampleController implements ActionListener {

	// Controller has Model and View hard-wired in
	SampleModel model;
	SampleView view;

	public SampleController() {
		System.out.println("SampleController()");
	}

	// invoked when a button is pressed
	public void actionPerformed(ActionEvent e) {
		// uncomment to see what action happened at view
		/*
		 * System.out.println ("Controller: The " + e.getActionCommand() +
		 * " button is clicked at " + new java.util.Date(e.getWhen()) +
		 * " with e.paramString " + e.paramString() );
		 */
		System.out.println("Controller: acting on Model");

		boolean yesInclude = false;
		SampleHelperClass helper = new SampleHelperClass();
		if (new Random().nextInt(20) % 3 == 0) {
			System.out.println("Add Extra 5");
			yesInclude = helper.includeJumper();
		}
		model.incrementValue(yesInclude);
	} // actionPerformed()

	// I should be able to add any model/view with the correct API
	// but here I can only add Model/View
	public void addModel(SampleModel m) {
		System.out.println("Controller: adding model");
		this.model = m;
	} // addModel()

	public void addView(SampleView v) {
		System.out.println("Controller: adding view");
		this.view = v;
	} // addView()

	public void initModel(int x) {
		model.setValue(x);
	} // initModel()
}
