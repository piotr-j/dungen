package io.piotrjastrzebski.dungen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.piotrjastrzebski.dungen.DungenGame;
import io.piotrjastrzebski.dungen.PlatformBridge;
import org.lwjgl.opengl.Display;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DungenGame.WIDTH;
		config.height = DungenGame.HEIGHT;
		config.useHDPI = true;
		new LwjglApplication(new DungenGame(new DesktopBridge()), config);
	}

	public static class DesktopBridge implements PlatformBridge {
		@Override public float getPixelScaleFactor () {
			return Display.getPixelScaleFactor();
		}
	}
}
