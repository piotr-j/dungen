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

/**
 * Created by PiotrJ on 04/09/15.
 */
public class DrawSettings {
	public boolean drawMinSpanTree;
	public boolean drawHallWayPaths;
	public boolean drawHallWays;
	public boolean drawBodies;
	public boolean drawUnused;
	public boolean drawMain;
	public boolean drawExtra;
	public boolean drawEdges;

	public DrawSettings () {

	}

	public void copy (DrawSettings o) {
		this.drawMinSpanTree = o.drawMinSpanTree;
		this.drawHallWayPaths = o.drawHallWayPaths;
		this.drawHallWays = o.drawHallWays;
		this.drawBodies = o.drawBodies;
		this.drawUnused = o.drawUnused;
		this.drawMain = o.drawMain;
		this.drawExtra = o.drawExtra;
		this.drawEdges = o.drawEdges;
	}

	public DrawSettings setDrawMinSpanTree (boolean drawMinSpanTree) {
		this.drawMinSpanTree = drawMinSpanTree;
		return this;
	}

	public DrawSettings setDrawHallWayPaths (boolean drawHallWayPaths) {
		this.drawHallWayPaths = drawHallWayPaths;
		return this;
	}

	public DrawSettings setDrawHallWays (boolean drawHallWays) {
		this.drawHallWays = drawHallWays;
		return this;
	}

	public DrawSettings setDrawBodies (boolean drawBodies) {
		this.drawBodies = drawBodies;
		return this;
	}

	public DrawSettings setDrawUnused (boolean drawUnused) {
		this.drawUnused = drawUnused;
		return this;
	}

	public DrawSettings setDrawMain (boolean drawMain) {
		this.drawMain = drawMain;
		return this;
	}

	public DrawSettings setDrawExtra (boolean drawExtra) {
		this.drawExtra = drawExtra;
		return this;
	}

	public DrawSettings setDrawEdges (boolean drawEdges) {
		this.drawEdges = drawEdges;
		return this;
	}
}
