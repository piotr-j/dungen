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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.*;

public class HelpGUI extends VisDialog {

	public HelpGUI () {
		super("HALP!");
		addCloseButton();
		closeOnEscape();
		setModal(true);
		VisTable c = new VisTable(true);
		c.add(new VisLabel("Welcome to Dungen!")).row();
		VisLabel link = new VisLabel("View this project on github!");
		link.setColor(0.2f, 0.4f, 1, 1);
		link.addListener(new ClickListener(){
			@Override public void clicked (InputEvent event, float x, float y) {
				Gdx.net.openURI("https://github.com/piotr-j/dungen");
			}
		});
		c.add(link).row();
		c.add(new VisLabel("Setting panels are movable")).row();
		c.add().row();
		c.add(new VisLabel("WSAD/arrows to pan view")).row();
		c.add(new VisLabel("Left click and drag to pan view")).row();
		c.add(new VisLabel("Q/E to zoom in/out")).row();
		c.add(new VisLabel("Scroll to zoom in/out")).row();
		c.add(new VisLabel("Space to restart")).row();
		c.add(new VisLabel("ESC to dismiss this dialog")).row();
		add(c);
		pack();
	}
}
