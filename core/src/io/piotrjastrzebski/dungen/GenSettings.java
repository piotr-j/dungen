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

	public GenSettings setSpawnWidth (float ellipseWidth) {
		this.spawnWidth = ellipseWidth;
		return this;
	}

	public float getSpawnHeight () {
		return spawnHeight * gridSize;
	}

	public GenSettings setSpawnHeight (float ellipseHeight) {
		this.spawnHeight = ellipseHeight;
		return this;
	}

	public float getRoomWidth () {
		return roomWidth * gridSize;
	}

	public GenSettings setRoomWidth (float roomWidth) {
		this.roomWidth = roomWidth;
		return this;
	}

	public float getRoomHeight () {
		return roomHeight * gridSize;
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

	public GenSettings spawnCount (int count) {
		this.count = count;
		return this;
	}

	public int getCount () {
		return count;
	}
}
