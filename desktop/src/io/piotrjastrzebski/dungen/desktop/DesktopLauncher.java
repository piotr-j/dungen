package io.piotrjastrzebski.dungen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.piotrjastrzebski.dungen.BaseScreen;
import io.piotrjastrzebski.dungen.DungenGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DungenGame.WIDTH;
		config.width = DungenGame.HEIGHT;
		config.useHDPI = true;
		new LwjglApplication(new DungenGame(), config);
	}
}
