package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.StringBuilder;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisWindow;

public class DungenGUI extends VisWindow {
	GenSettings settings;
	VisSlider grid;
	VisSlider count;
	VisSlider sWidth;
	VisSlider sHeight;
	VisSlider rWidth;
	VisSlider rHeight;
	VisSlider mainScale;
	VisSlider reconChance;

	public DungenGUI (final Restarter restarter) {
		super("Settings");
		settings = new GenSettings();
		VisTextButton restart = new VisTextButton("Restart");
		restart.addListener(new ClickListener() {
			@Override public void clicked (InputEvent event, float x, float y) {
				restarter.restart(settings);
			}
		});
		add(restart).row();

		grid = slider("Grid", 0.1f, 1.f, 0.05f);
		grid.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setGridSize(s.getValue());
			}
		});
		row();
		count = slider("Rooms", 50f, 300f, 10f);
		count.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setCount((int)s.getValue());
			}
		});
		row();
		sWidth = slider("Spawn Width", 5f, 40f, 5f);
		sWidth.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setSpawnWidth(s.getValue());
			}
		});
		row();
		sHeight = slider("Spawn Height", 5f, 40f, 5f);
		sHeight.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setSpawnHeight(s.getValue());
			}
		});
		row();
		rWidth = slider("Room Width", 1f, 10f, 1f);
		rWidth.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setRoomWidth(s.getValue());
			}
		});
		row();
		rHeight = slider("Room Height", 1f, 10f, 1f);
		rHeight.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setRoomHeight(s.getValue());
			}
		});
		row();
		mainScale = slider("Main Scale", 0.5f, 2.f, 0.05f);
		mainScale.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setMainRoomScale(s.getValue());
			}
		});
		row();
		reconChance = slider("Reconnect Chance", 0.0f, 1.f, 0.05f);
		reconChance.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setReconnectChance(s.getValue());
			}
		});
		row();
		pack();

	}

	private VisSlider slider(String text, float min, float max, float step) {
		VisLabel label = new VisLabel(text);
		final VisSlider slider = new VisSlider(min, max, step, false);
		add(label);
		add(slider);
		final VisLabel val = new VisLabel("0.00");
		slider.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				val.setText(toStr(slider.getValue()));
			}
		});
		add(val);
		return slider;
	}

	/*
	Simple String.format() replacements as it doesnt work on GWT
	 */
	StringBuilder sb = new StringBuilder();
	private String toStr(float value) {
		sb.setLength(0);
		int d = (int)value;
		float v = (value - d) * 100;
		sb.append(d);
		sb.append(".");
		sb.append((int)v);
		return sb.toString();
	}

	public void setDefaults(GenSettings settings) {
		this.settings.copy(settings);
		grid.setValue(settings.getGridSize());
		count.setValue(settings.getCount());
		sWidth.setValue(settings.getRawSpawnWidth());
		sHeight.setValue(settings.getRawSpawnHeight());
		rWidth.setValue(settings.getRawRoomWidth());
		rHeight.setValue(settings.getRawRoomHeight());
		mainScale.setValue(settings.getMainRoomScale());
		reconChance.setValue(settings.getReconnectChance());
	}

	public GenSettings getSettings () {
		return settings;
	}

	public interface Restarter {
		public void restart(GenSettings settings);
	}
}
