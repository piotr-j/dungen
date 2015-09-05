package io.piotrjastrzebski.dungen;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by PiotrJ on 03/09/15.
 */
public class HallwayPath {
	private static int ID = 0;
	public int id;
	public Vector2 start = new Vector2();
	public Vector2 bend = new Vector2();
	public Vector2 end = new Vector2();
	public boolean hasBend;
	public float width = 1;
	public Room roomA;
	public Room roomB;
	public Array<Room> overlap = new Array<>();
	private float gridSize;

	public HallwayPath (float gridSize) {
		this.gridSize = gridSize;
		this.id = ID++;
	}

	public void draw (ShapeRenderer renderer) {
		if (hasBend) {
			renderer.rectLine(start.x, start.y, bend.x, bend.y, gridSize * 0.1f);
			renderer.rectLine(bend.x, bend.y, end.x, end.y, gridSize * 0.1f);
		} else {
			renderer.rectLine(start.x, start.y, end.x, end.y, gridSize * 0.1f);
		}
	}

	public void set (float sx, float sy, float ex, float ey) {
		start.set(sx, sy);
		end.set(ex, ey);
		Utils.roundToSize(start, gridSize);
		Utils.roundToSize(end, gridSize);
		hasBend = false;
	}

	public void set (float sx, float sy, float bx, float by, float ex, float ey) {
		start.set(sx, sy);
		bend.set(bx, by);
		end.set(ex, ey);
		Utils.roundToSize(start, gridSize);
		Utils.roundToSize(bend, gridSize);
		Utils.roundToSize(end, gridSize);
		hasBend = true;
	}

	private static Polygon poly = new Polygon();
	private static float[] verts = new float[8];

	public boolean intersects (Room room) {
		Rectangle b = room.bounds;
		verts[0] = b.x;
		verts[1] = b.y;

		verts[2] = b.x;
		verts[3] = b.y + b.height;

		verts[4] = b.x + b.width;
		verts[5] = b.y + b.height;

		verts[6] = b.x + b.width;
		verts[7] = b.y;
		poly.setVertices(verts);
		boolean intersects;
		if (hasBend) {
			intersects =
				Intersector.intersectSegmentPolygon(start, bend, poly) || Intersector.intersectSegmentPolygon(bend, end, poly);
		} else {
			intersects = Intersector.intersectSegmentPolygon(start, end, poly);
		}
		if (intersects) {
			overlap.add(room);
		}
		return intersects;
	}

	@Override public String toString () {
		return "Hallway{" +
			"id=" + id +
			'}';
	}
}
