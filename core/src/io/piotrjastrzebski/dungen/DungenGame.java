package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;

public class DungenGame extends Game {
	// 'target' resolution
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	public final static float SCALE = 32f;
	public final static float INV_SCALE = 1.f / SCALE;
	public final static float VP_WIDTH = WIDTH * INV_SCALE;
	public final static float VP_HEIGHT = HEIGHT * INV_SCALE;

	protected PlatformBridge bridge;
	public DungenGame (PlatformBridge bridge) {
		this.bridge = bridge;
	}

	@Override public void create () {
		if (bridge.getPixelScaleFactor() > 1.5f) {
			VisUI.load(VisUI.SkinScale.X2);
		} else {
			VisUI.load(VisUI.SkinScale.X1);
		}
		setScreen(new DungenScreen());
	}

	@Override public void dispose () {
		super.dispose();
		VisUI.dispose();
	}
}
