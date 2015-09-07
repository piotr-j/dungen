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
public class GenSettings {
	private float gridSize;
	private float spawnWidth, spawnHeight;
	private float roomWidth, roomHeight;
	private float mainRoomScale;
	private float reconnectChance;
	private float hallwaysWidth;
	private int count;
	private int roomCount;

	public GenSettings () {
	}

	public float getHallwaysWidth () {
		return hallwaysWidth;
	}

	public GenSettings setHallwaysWidth (float hallwaysWidth) {
		this.hallwaysWidth = hallwaysWidth;
		return this;
	}

	public float getGridSize () {
		return gridSize;
	}

	public GenSettings setGridSize (float gridSize) {
		this.gridSize = gridSize;
		return this;
	}

	public float getSpawnWidth () {
		return spawnWidth * gridSize;
	}

	public float getRawSpawnWidth () {
		return spawnWidth;
	}

	public GenSettings setSpawnWidth (float ellipseWidth) {
		this.spawnWidth = ellipseWidth;
		return this;
	}

	public float getSpawnHeight () {
		return spawnHeight * gridSize;
	}


	public float getRawSpawnHeight () {
		return spawnHeight;
	}

	public GenSettings setSpawnHeight (float ellipseHeight) {
		this.spawnHeight = ellipseHeight;
		return this;
	}

	public float getRoomWidth () {
		return roomWidth * gridSize;
	}
	public float getRawRoomWidth () {
		return roomWidth;
	}

	public GenSettings setRoomWidth (float roomWidth) {
		this.roomWidth = roomWidth;
		return this;
	}

	public float getRoomHeight () {
		return roomHeight * gridSize;
	}
	public float getRawRoomHeight () {
		return roomHeight;
	}

	public GenSettings setRoomHeight (float roomHeight) {
		this.roomHeight = roomHeight;
		return this;
	}

	public float getMainRoomScale () {
		return mainRoomScale;
	}

	public GenSettings setMainRoomScale (float mainRoomScale) {
		this.mainRoomScale = mainRoomScale;
		return this;
	}

	public float getReconnectChance () {
		return reconnectChance;
	}

	public GenSettings setReconnectChance (float reconnectChance) {
		this.reconnectChance = reconnectChance;
		return this;
	}

	public GenSettings setCount (int count) {
		this.count = count;
		return this;
	}

	public int getCount () {
		return count;
	}

	public void copy (GenSettings s) {
		this.gridSize = s.gridSize;
		this.spawnWidth = s.spawnWidth;
		this.spawnHeight = s.spawnHeight;
		this.roomWidth = s.roomWidth;
		this.roomHeight = s.roomHeight;
		this.mainRoomScale = s.mainRoomScale;
		this.reconnectChance = s.reconnectChance;
		this.hallwaysWidth = s.hallwaysWidth;
		this.count = s.count;
	}

	public void setRoomCount (int roomCount) {
		this.roomCount = roomCount;
	}
}
