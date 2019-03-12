import controllers.SampleController;
import views.SampleView;
import models.SampleModel;

public class RunMVC {

	// The order of instantiating the objects below will be important for some pairs
	// of commands.
	// I haven't explored this in any detail, beyond that the order below works.

	private int start_value = 10; // initialise model, which in turn initialises view

	public RunMVC() {

		// create Model and View
		SampleModel myModel = new SampleModel();
		SampleView myView = new SampleView();

		// tell Model about View.
		myModel.addObserver(myView);
		/*
		 * init model after view is instantiated and can show the status of the model (I
		 * later decided that only the controller should talk to the model and moved
		 * initialisation to the controller (see below).)
		 */
		// uncomment to directly initialise Model
		// myModel.setValue(start_value);

		// create Controller. tell it about Model and View, initialise model
		SampleController myController = new SampleController();
		myController.addModel(myModel);
		myController.addView(myView);
		myController.initModel(start_value);

		// tell View about Controller
		myView.addController(myController);
		// and Model,
		// this was only needed when the view inits the model
		// myView.addModel(myModel);
	}

}
