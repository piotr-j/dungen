/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package io.piotrjastrzebski.dungen.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.*;
import io.piotrjastrzebski.dungen.DrawSettings;

public class DrawSettingsGUI extends VisWindow {
	DrawSettings settings;
	Restarter restarter;

	VisCheckBox drawMinSpanTree;
	VisCheckBox drawHallWayPaths;
	VisCheckBox drawHallWays;
	VisCheckBox drawBodies;
	VisCheckBox drawUnused;
	VisCheckBox drawMain;
	VisCheckBox drawExtra;
	VisCheckBox drawEdges;

	public DrawSettingsGUI (Restarter restarter) {
		super("Generator settings");
		this.restarter = restarter;

		settings = new DrawSettings();
		VisTable c = new VisTable(true);
		c.add(new VisLabel("Hover on labels for tooltips")).colspan(3).row();

		drawBodies = toggle(c, "Bodies", "Draw box2d bodies used for separation", settings.drawBodies, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawBodies = value;
			}
		});
		c.row();
		drawUnused = toggle(c, "Unused", "Draw rooms that are unused", settings.drawUnused, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawUnused = value;
			}
		});
		c.row();
		drawExtra = toggle(c, "Extra", "Draw extra rooms, added to form paths", settings.drawExtra, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawExtra = value;
			}
		});
		c.row();
		drawHallWays = toggle(c, "Hallways", "Draw rooms that are part of hallways", settings.drawHallWays, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawHallWays = value;
			}
		});
		drawHallWayPaths = toggle(c, "Hallway Paths", "Draw hallway paths connecting main rooms", settings.drawHallWayPaths, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawHallWayPaths = value;
			}
		});
		c.row();
		drawMain = toggle(c, "Main", "Draw main rooms", settings.drawMain, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawMain = value;
			}
		});
		c.row();
		drawEdges = toggle(c, "Triangulation", "Draw triangulation for main rooms", settings.drawEdges, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawEdges = value;
			}
		});
		c.row();
		drawMinSpanTree = toggle(c, "Min Span Tree", "Draw minimum spanning tree for main rooms", settings.drawMinSpanTree, new Toggle() {
			@Override public void toggle (boolean value) {
				settings.drawMinSpanTree = value;
			}
		});

		add(c);
		pack();
	}

	private VisCheckBox toggle (VisTable c, String text, String tt, boolean def, final Toggle toggle) {
		final VisCheckBox cb = new VisCheckBox(text, def);
		new Tooltip(cb, tt);
		cb.addListener(new ChangeListener() {
			@Override public void changed (ChangeEvent event, Actor actor) {
				toggle.toggle(cb.isChecked());
				restarter.update(settings);
			}
		});
		c.add(cb);
		return cb;
	}

	private abstract class Toggle {
		public abstract void toggle(boolean checked);
	}

	public void setDefaults(DrawSettings settings) {
		this.settings.copy(settings);
		drawBodies.setChecked(settings.drawBodies);
		drawUnused.setChecked(settings.drawUnused);
		drawExtra.setChecked(settings.drawExtra);
		drawHallWays.setChecked(settings.drawHallWays);
		drawHallWayPaths.setChecked(settings.drawHallWayPaths);
		drawMain.setChecked(settings.drawMain);
		drawEdges.setChecked(settings.drawEdges);
		drawMinSpanTree.setChecked(settings.drawMinSpanTree);
		pack();
	}

	public DrawSettings getSettings () {
		return settings;
	}
}
