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
		setScreen(new DungenScreen(this));
	}

	@Override public void dispose () {
		super.dispose();
		VisUI.dispose();
	}
}
