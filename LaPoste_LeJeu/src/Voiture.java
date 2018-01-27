import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class Voiture 
{
	protected Image	image;
	protected float	x, y, speed;
	protected static SpriteSheet voituresImg;

	public abstract void render(Graphics g);

	public abstract boolean update(int delta);

}
