package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by PiotrJ on 02/09/15.
 */
public class DungenScreen extends BaseScreen {
	DungeonGenerator generator;
	Grid grid;
	GenSettings settings;

	public DungenScreen () {
		super();
		settings = new GenSettings()
			.setGridSize(.25f)
			.spawnCount(150)
			.setSpawnWidth(20).setSpawnHeight(10)
			.setRoomWidth(4).setRoomHeight(4)
			.setMainRoomScale(1.15f)
			.setReconnectChance(.2f)
			.setHallwaysWidth(3);

		generator = new DungeonGenerator();
		generator.init(settings);

		grid = new Grid();
		grid.setSize(settings.getGridSize());
	}

	@Override public void render (float delta) {
		super.render(delta);
		generator.update(delta);

		Gdx.gl.glEnable(GL20.GL_BLEND);
		renderer.setProjectionMatrix(gameCamera.combined);
		renderer.begin(ShapeRenderer.ShapeType.Line);
		grid.render(renderer);
		renderer.end();

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		generator.render(renderer);
		renderer.end();
	}

	@Override public void resize (int width, int height) {
		super.resize(width, height);
		grid.resize(width, height);
	}

	@Override public boolean keyDown (int keycode) {
		switch (keycode) {
		case Input.Keys.SPACE:
			generator.init(settings);
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
}
