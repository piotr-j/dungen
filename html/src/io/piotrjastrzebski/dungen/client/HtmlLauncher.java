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

package io.piotrjastrzebski.dungen.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.utils.Base64Coder;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Window;
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

		@Override public void save (String name, String data) {
			String toSave = "data:application/octet-stream;charset=utf-8;base64,";
			toSave += Base64Coder.encodeString(data);
			toSave+="\n";
			dl(name, toSave);
		}

		private static native void dl(String filename, String uri)
/*-{
    var link = document.createElement('a');
    if (typeof link.download === 'string') {
        link.href = uri;
        link.download = filename;

        //Firefox requires the link to be in the body
        document.body.appendChild(link);

        //simulate click
        link.click();

        //remove the link when done
        document.body.removeChild(link);
    } else {
        window.open(uri);
    }
}-*/;
	}
}
