package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by PiotrJ on 02/09/15.
 */
public class DungenScreen extends BaseScreen implements DungenGUI.Restarter {
	DungeonGenerator generator;
	Grid grid;
	GenSettings settings;
	DungenGUI gui;

	public DungenScreen () {
		super();
		settings = new GenSettings()
			.setGridSize(.25f)
			.setCount(150)
			.setSpawnWidth(20).setSpawnHeight(10)
			.setRoomWidth(4).setRoomHeight(4)
			.setMainRoomScale(1.15f)
			.setReconnectChance(.2f)
			.setHallwaysWidth(3);

		generator = new DungeonGenerator();
		generator.init(settings);

		grid = new Grid();
		grid.setSize(settings.getGridSize());

		gui = new DungenGUI(this);
		gui.setDefaults(settings);
		stage.addActor(gui);
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
			restart(gui.getSettings());
			break;
		case Input.Keys.B:
//			drawBodies = !drawBodies;
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

	@Override public void dispose () {
		super.dispose();
		generator.dispose();
	}

	@Override public void restart (GenSettings settings) {
		this.settings.copy(settings);
		generator.init(settings);
		grid.setSize(settings.getGridSize());
	}
}
