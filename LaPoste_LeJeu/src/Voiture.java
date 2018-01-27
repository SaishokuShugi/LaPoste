import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Voiture
{
	protected Image					image;
	protected float					x, y, speed;
	protected static SpriteSheet	voituresImg;
	protected static float			v0	= 3f;
	protected boolean				voie;
	protected Rectangle hitbox;

	public abstract void render(Graphics g);

	public abstract boolean update(int delta);

}
