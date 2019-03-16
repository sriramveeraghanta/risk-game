package models;

import java.util.ArrayList;
import java.util.HashMap;

public class MapModel {
	public String wrap;
	public String scroll;
	public String warn;
	public String imageName;
	public HashMap<String, String> continentControlValues = new HashMap<String, String>();
	public ArrayList<String> terittories = new ArrayList<String>();

	public MapModel() {

	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getWrap() {
		return wrap;
	}

	public void setWrap(String wrap) {
		this.wrap = wrap;
	}

	public String getScroll() {
		return scroll;
	}

	public void setScroll(String scroll) {
		this.scroll = scroll;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public HashMap<String, String> getContinentControlValues() {
		return continentControlValues;
	}

	public void setContinentControlValues(ArrayList<String> continentControlValue) {
		this.continentControlValues.put(continentControlValue.get(0), continentControlValue.get(1)) ;
	}

	public ArrayList<String> getTerittories() {
		return terittories;
	}

	public void addTerittories(String territory) {
		this.terittories.add(territory);
	}

}
