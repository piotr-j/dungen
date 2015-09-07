package io.piotrjastrzebski.dungen.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import io.piotrjastrzebski.dungen.BaseScreen;
import io.piotrjastrzebski.dungen.DungenGame;
import io.piotrjastrzebski.dungen.PlatformBridge;

public class HtmlLauncher extends GwtApplication {

	@Override public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(DungenGame.WIDTH, DungenGame.HEIGHT);
	}

	@Override public ApplicationListener getApplicationListener () {
		return new DungenGame(new HtmlBridge());
	}

	public static class HtmlBridge implements PlatformBridge {
		@Override public float getPixelScaleFactor () {
			return 1;
		}
	}
}
