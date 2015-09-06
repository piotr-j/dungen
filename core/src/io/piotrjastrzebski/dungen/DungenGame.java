package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.Game;

public class DungenGame extends Game {
	// 'target' resolution
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	public final static float SCALE = 32f;
	public final static float INV_SCALE = 1.f / SCALE;
	public final static float VP_WIDTH = WIDTH * INV_SCALE;
	public final static float VP_HEIGHT = HEIGHT * INV_SCALE;

	@Override public void create () {
		setScreen(new DungenScreen());
	}
}
