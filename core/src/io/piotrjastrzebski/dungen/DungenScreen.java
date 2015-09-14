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

package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.JsonWriter;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import io.piotrjastrzebski.dungen.gui.*;

/**
 * Created by PiotrJ on 02/09/15.
 */
public class DungenScreen extends BaseScreen implements Restarter, Saver, GestureDetector.GestureListener {
	DungeonGenerator generator;
	Grid grid;
	GenSettingsGUI genGui;
	GenSettings genSettings;
	DrawSettingsGUI drawGui;
	DrawSettings drawSettings;
	DungenGame game;

	HelpGUI helpGUI;

	public DungenScreen (DungenGame game) {
		super();
		this.game = game;

		genSettings = new GenSettings()
			.setGridSize(.25f)
			.setCount(150)
			.setSpawnWidth(20).setSpawnHeight(10)
			.setRoomWidth(4).setRoomHeight(4)
			.setMainRoomScale(1.15f)
			.setReconnectChance(.2f)
			.setHallwaysWidth(2);

		drawSettings = new DrawSettings()
			.setDrawExtra(true)
			.setDrawMain(true)
			.setDrawHallWays(true)
			.setDrawUnused(true);

		generator = new DungeonGenerator();
		generator.init(genSettings);

		grid = new Grid();
		grid.setSize(genSettings.getGridSize());

		genGui = new GenSettingsGUI(this, this);
		genGui.setDefaults(genSettings);
		stage.addActor(genGui);
		genGui.setPosition(10, 10);

		drawGui = new DrawSettingsGUI(this);
		drawGui.setDefaults(drawSettings);
		stage.addActor(drawGui);
		drawGui.setPosition(10, stage.getHeight() - drawGui.getHeight() - 10);

		multiplexer.addProcessor(this);
		multiplexer.addProcessor(new GestureDetector(this));

		helpGUI = new HelpGUI();
		VisTable helpCont = new VisTable();
		helpCont.setFillParent(true);
		VisTextButton showHelp = new VisTextButton("Help!");
		showHelp.addListener(new ClickListener() {
			@Override public void clicked (InputEvent event, float x, float y) {
				helpGUI.show(stage);
			}
		});
		helpCont.add(showHelp).right().top().expand().pad(10);
		stage.addActor(helpCont);

		Preferences dungen = Gdx.app.getPreferences("Dungen");
		if (!dungen.getBoolean("help-shown", false)) {
			helpGUI.show(stage);
			dungen.putBoolean("help-shown", true);
			dungen.flush();
		}
	}

	@Override public void save (String name) {
		// TODO settings for type and pretty print
		String json = generator.toJson(JsonWriter.OutputType.javascript, true);
		if (!name.endsWith(".json"))
			name += ".json";
		game.bridge.save(name, json);
	}

	private int moveVert;
	private int moveHor;
	private int zoom;
	@Override public void render (float delta) {
		super.render(delta);
		generator.update(delta);
		if (zoom > 0) {
			gameCamera.zoom = MathUtils.clamp(gameCamera.zoom + gameCamera.zoom * 0.05f, 0.01f, 10);
		} else if (zoom < 0) {
			gameCamera.zoom = MathUtils.clamp(gameCamera.zoom - gameCamera.zoom * 0.05f, 0.01f, 10);

		}
		if (moveVert > 0) {
			gameCamera.position.y += 10 * delta;
		} else if (moveVert < 0) {
			gameCamera.position.y -= 10 * delta;
		}
		if (moveHor > 0) {
			gameCamera.position.x += 10 * delta;
		} else if (moveHor < 0) {
			gameCamera.position.x -= 10 * delta;
		}
		gameCamera.update();

		renderer.setProjectionMatrix(gameCamera.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		grid.render(renderer);
		renderer.end();

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		generator.render(renderer, gameCamera);
		renderer.end();

		stage.act(delta);
		stage.draw();
	}

	@Override public void resize (int width, int height) {
		super.resize(width, height);
		grid.resize(width, height);
	}

	@Override public boolean keyDown (int keycode) {
		switch (keycode) {
		case Input.Keys.SPACE:
			restart(genGui.getSettings());
			break;
		case Input.Keys.C:
			resetCamera();
			break;
		case Input.Keys.Q:
			zoom++;
			break;
		case Input.Keys.E:
			zoom--;
			break;
		case Input.Keys.UP:
		case Input.Keys.W:
			moveVert++;
			break;
		case Input.Keys.DOWN:
		case Input.Keys.S:
			moveVert--;
			break;
		case Input.Keys.LEFT:
		case Input.Keys.A:
			moveHor--;
			break;
		case Input.Keys.RIGHT:
		case Input.Keys.D:
			moveHor++;
			break;
		}
		return super.keyDown(keycode);
	}


	@Override public boolean keyUp (int keycode) {
		switch (keycode) {
		case Input.Keys.Q:
			zoom--;
			break;
		case Input.Keys.E:
			zoom++;
			break;
		case Input.Keys.UP:
		case Input.Keys.W:
			moveVert--;
			break;
		case Input.Keys.DOWN:
		case Input.Keys.S:
			moveVert++;
			break;
		case Input.Keys.LEFT:
		case Input.Keys.A:
			moveHor++;
			break;
		case Input.Keys.RIGHT:
		case Input.Keys.D:
			moveHor--;
			break;
		}
		return super.keyDown(keycode);
	}

	private void resetCamera () {
		gameCamera.position.setZero();
		gameCamera.zoom = 1;
		gameCamera.update();
	}

	@Override public boolean scrolled (int amount) {
		gameCamera.zoom = MathUtils.clamp(gameCamera.zoom + gameCamera.zoom * amount * 0.05f, 0.01f, 10);
		gameCamera.update();
		return super.scrolled(amount);
	}

	@Override public void dispose () {
		super.dispose();
		generator.dispose();
	}

	@Override public void restart (GenSettings settings) {
		this.genSettings.copy(settings);
		generator.init(settings);
		grid.setSize(settings.getGridSize());
	}

	@Override public void update (DrawSettings settings) {
		generator.setDrawSettings(settings);
	}

	@Override public boolean pan (float x, float y, float deltaX, float deltaY) {
		// this feels wrong...
		float ppw = gameViewport.getWorldWidth() / gameViewport.getScreenWidth();
		float pph = gameViewport.getWorldHeight() / gameViewport.getScreenHeight();
		gameCamera.position.add(-deltaX * ppw * gameCamera.zoom, deltaY * pph * gameCamera.zoom, 0);
		gameCamera.update();
		return false;
	}

	@Override public boolean panStop (float x, float y, int pointer, int button) {
		return false;
	}

	@Override public boolean zoom (float initialDistance, float distance) {
		// TODO touch screen zoom
		return false;
	}

	@Override public boolean touchDown (float x, float y, int pointer, int button) {
		return false;
	}

	@Override public boolean tap (float x, float y, int count, int button) {
		return false;
	}

	@Override public boolean longPress (float x, float y) {
		return false;
	}

	@Override public boolean fling (float velocityX, float velocityY, int button) {
		return false;
	}

	@Override public boolean pinch (Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}
}
