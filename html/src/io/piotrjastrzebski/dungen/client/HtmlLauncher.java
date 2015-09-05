package io.piotrjastrzebski.dungen.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import io.piotrjastrzebski.dungen.BaseScreen;
import io.piotrjastrzebski.dungen.DungenGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(DungenGame.WIDTH, DungenGame.HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new DungenGame();
        }
}
