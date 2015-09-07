package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StringBuilder;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;

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
		VisTable c = new VisTable(true);

		VisTextButton restart = new VisTextButton("Restart");
		restart.addListener(new ClickListener() {
			@Override public void clicked (InputEvent event, float x, float y) {
				restarter.restart(settings);
			}
		});
		c.add(restart);
		c.row();
		c.add(new VisLabel("Hover on labels for tooltips")).colspan(3);
		c.row();

		grid = slider(c, "Grid Size", "Size of the grid in units\n1u=32px at 720p", 0.1f, 1.f, 0.05f);
		grid.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setGridSize(s.getValue());
			}
		});
		c.row();
		count = slider(c, "Room Count", "Number of rooms to be spawned", 50f, 500f, 10f);
		count.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setCount((int)s.getValue());
			}
		});
		c.row();
		sWidth = slider(c, "Spawn Width", "Width of the ellipse the rooms will be spawned in\nin grid units", 5f, 40f, 5f);
		sWidth.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setSpawnWidth(s.getValue());
			}
		});
		c.row();
		sHeight = slider(c, "Spawn Height", "Height of the ellipse the rooms will be spawned in\nin grid units", 5f, 40f, 5f);
		sHeight.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setSpawnHeight(s.getValue());
			}
		});
		c.row();
		rWidth = slider(c, "Room Width", "Mean room width in grid units", 1f, 10f, 1f);
		rWidth.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setRoomWidth(s.getValue());
			}
		});
		c.row();
		rHeight = slider(c, "Room Height", "Mean room height in grid units", 1f, 10f, 1f);
		rHeight.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setRoomHeight(s.getValue());
			}
		});
		c.row();
		mainScale = slider(c, "Main Scale", "Percent of the mean size for a room to be marked as main", 0.5f, 2.f, 0.05f);
		mainScale.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setMainRoomScale(s.getValue());
			}
		});
		c.row();
		reconChance = slider(c, "Reconnect %", "Chance to reconnect the path after\nminimum spanning tree is created", 0.0f, 1.f, 0.05f);
		reconChance.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				VisSlider s = (VisSlider)actor;
				settings.setReconnectChance(s.getValue());
			}
		});
		c.row();
		add(c);
		pack();
	}

	private VisSlider slider(Table container, String text, String tooltip, float min, float max, float step) {
		VisLabel label = new VisLabel(text);
		final VisSlider slider = new VisSlider(min, max, step, false);
		container.add(label).left();
		new Tooltip(label, tooltip);
		float scale = VisUI.getSizes().scaleFactor;
		container.add(slider).prefWidth(120 * scale);
		final VisLabel val = new VisLabel("000.00");
		slider.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				val.setText(toStr(slider.getValue()));
			}
		});
		val.setAlignment(Align.right);
		container.add(val).right().width(45 * scale);
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
		pack();
	}

	public GenSettings getSettings () {
		return settings;
	}

	public interface Restarter {
		public void restart(GenSettings settings);
	}
}
