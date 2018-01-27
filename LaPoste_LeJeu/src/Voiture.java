import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class Voiture
{
	protected Image					image;
	protected float					x, y, speed;
	protected static SpriteSheet	voituresImg;
	protected static float			v0	= 3f;
	protected boolean				voie;

	public abstract void render(Graphics g);

	public abstract boolean update(int delta);

}
