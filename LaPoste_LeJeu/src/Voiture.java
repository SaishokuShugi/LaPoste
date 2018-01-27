import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Voiture
{
	protected Image					image;
	protected float					x, y, speed;
	protected static SpriteSheet	voituresImg;
	protected static float			v0			= 3f;
	protected boolean				voie;
	protected Rectangle				hitbox;
	protected boolean				destroyed	= false;
	protected int					timedest	= 0;
	public boolean render(Graphics g)
	{
		if (destroyed)
			{
				if ((PhaseVoiture.time - timedest) < 16 * Game.boom.f)
					{
						Game.boom.draw(x, y, g, PhaseVoiture.time - timedest);
						return false;
					} else
					{
						return true;
					}
			}
		g.drawImage(image, x, y);
		return false;
	}


	public abstract boolean update(int delta);

}
