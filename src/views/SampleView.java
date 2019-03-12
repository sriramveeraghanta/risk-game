package views;

import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Label;
import java.lang.Integer; //int from Model is passed as an Integer
import java.util.Observable; //for update();
import java.util.Observer;

import action.SampleAction;

import java.awt.event.ActionListener; //for addController()

/**
 * 
 * EXAMPLE LINK http://www.austintek.com/mvc/
 */

public class SampleView implements Observer {

	// attributes as must be visible within class
	private TextField myTextField;
	private Button button;

	// private Model model; // Model is hard-wired in,
	// needed only if view initializes model (which we aren't doing)

	public SampleView() {
		System.out.println("SampleView()");

		// frame in constructor and not an attribute as doesn't need to be visible to
		// whole class
		Frame frame = new Frame("simple MVC");
		frame.add("North", new Label("counter"));

		myTextField = new TextField();
		frame.add("Center", myTextField);

		// panel in constructor and not an attribute as doesn't need to be visible to
		// whole class
		Panel panel = new Panel();
		button = new Button("PressMe");
		panel.add(button);
		frame.add("South", panel);

		frame.addWindowListener(new SampleAction());
		frame.setSize(200, 100);
		frame.setLocation(100, 100);
		frame.setVisible(true);
	}

	// Called from the Model
	public void update(Observable obs, Object obj) {

		// who called us and what did they send?
		// System.out.println ("View : Observable is " + obs.getClass() + ", object
		// passed is " + obj.getClass());

		// model Pull
		// ignore obj and ask model for value,
		// to do this, the view has to know about the model (which I decided I didn't
		// want to do)
		// uncomment next line to do Model Pull
		// myTextField.setText("" + model.getValue());

		// model Push
		// parse obj
		myTextField.setText("" + ((Integer) obj).intValue()); // obj is an Object, need to cast to an Integer

	} // update()

//to initialise TextField
	public void setValue(int v) {
		myTextField.setText("" + v);
	} // setValue()

	public void addController(ActionListener controller) {
		System.out.println("View      : adding controller");
		button.addActionListener(controller); // need instance of controller before can add it as a listener
	} // addController()

//uncomment to allow controller to use view to initialise model	
//public void addModel(Model m){
//	System.out.println("View      : adding model");
//	this.model = m;
//} //addModel()

}
