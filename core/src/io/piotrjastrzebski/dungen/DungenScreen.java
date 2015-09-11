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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Base64Coder;
import io.piotrjastrzebski.dungen.gui.DrawSettingsGUI;
import io.piotrjastrzebski.dungen.gui.GenSettingsGUI;
import io.piotrjastrzebski.dungen.gui.Restarter;
import io.piotrjastrzebski.dungen.gui.Saver;

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
			.setHallwaysWidth(3);

		drawSettings = new DrawSettings();

		generator = new DungeonGenerator();
		generator.init(genSettings);

		grid = new Grid();
		grid.setSize(genSettings.getGridSize());

		genGui = new GenSettingsGUI(this, this);
		genGui.setDefaults(genSettings);
		stage.addActor(genGui);

		drawGui = new DrawSettingsGUI(this);
		drawGui.setDefaults(drawSettings);
		stage.addActor(drawGui);
		drawGui.setPosition(0, stage.getHeight()-drawGui.getHeight());

		multiplexer.addProcessor(this);
		multiplexer.addProcessor(new GestureDetector(this));
	}

	@Override public void save (String name) {
		// TODO make a reasonable json representation
		// TODO pick a name?
//		game.bridge.save(name + ".json", "welp:{data:0,welp2:welp}");
	}

	public void saveDungeon() {
	}

	@Override public void render (float delta) {
		super.render(delta);
		generator.update(delta);

		renderer.setProjectionMatrix(gameCamera.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		grid.render(renderer);
		renderer.end();

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		generator.render(renderer);
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
		case Input.Keys.B:
//			drawBodies = !drawBodies;
			break;
		case Input.Keys.C:
			resetCamera();
			break;
		case Input.Keys.Q:
//			if (pIters == 8) {
//				pIters = 100;
//			} else {
//				pIters = 8;
//			}
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
