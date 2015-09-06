package io.piotrjastrzebski.dungen;

import com.kotcrab.vis.ui.widget.VisWindow;

public class DungenGUI extends VisWindow {
	GenSettings settings;
	public DungenGUI () {
		super("Settings");
		settings = new GenSettings();
	}

	public void setDefaults(GenSettings settings) {
		this.settings.copy(settings);

	}

}
