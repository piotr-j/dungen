package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;

/**
 * Created by EvilEntity on 07/06/2015.
 */
public abstract class BaseScreen implements Screen, InputProcessor {
	private final static String TAG = "BaseScreen";

	protected OrthographicCamera gameCamera;
	protected OrthographicCamera guiCamera;
	protected ExtendViewport gameViewport;
	protected ScreenViewport guiViewport;

	protected SpriteBatch batch;
	protected ShapeRenderer renderer;

	protected Stage stage;
	protected Table root;

	protected final InputMultiplexer multiplexer;

	boolean debugStage;

	public BaseScreen () {
		VisUI.load(VisUI.SkinScale.X1);
		gameCamera = new OrthographicCamera();
		gameViewport = new ExtendViewport(DungenGame.VP_WIDTH, DungenGame.VP_HEIGHT, gameCamera);
		guiCamera = new OrthographicCamera();
		guiViewport = new ScreenViewport(guiCamera);

		batch = new SpriteBatch();
		renderer = new ShapeRenderer();

		stage = new Stage(guiViewport, batch);
		stage.setDebugAll(debugStage);
		root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		Gdx.input.setInputProcessor(multiplexer = new InputMultiplexer(stage, this));

		Gdx.app.log(TAG, "F1 - toggle stage debug");

		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override public void show () {

	}

	@Override public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override public void resize (int width, int height) {
		gameViewport.update(width, height, false);
		guiViewport.update(width, height, true);
	}

	@Override public void pause () {

	}

	@Override public void resume () {

	}

	@Override public void hide () {
		dispose();
	}

	@Override public void dispose () {
		batch.dispose();
		renderer.dispose();
		stage.dispose();
		VisUI.dispose();
	}

	@Override public boolean keyDown (int keycode) {
		if (keycode == Input.Keys.F1) {
			debugStage = !debugStage;
			stage.setDebugAll(debugStage);
			Gdx.app.log(TAG, "F1 - Stage debug is " + (debugStage ? "enabled" : "disabled"));
		}
		return false;
	}

	@Override public boolean keyUp (int keycode) {
		return false;
	}

	@Override public boolean keyTyped (char character) {
		return false;
	}

	@Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override public boolean touchDragged (int screenX, int screenY, int pointer) {
		return false;
	}

	@Override public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	@Override public boolean scrolled (int amount) {
		return false;
	}
}
