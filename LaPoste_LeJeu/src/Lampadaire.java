import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Lampadaire
{
	protected float	x, y;
	protected Image	image;

	public Lampadaire() throws SlickException
	{
		y = -128f;
		x=-32;
		SpriteSheet ss = new SpriteSheet("res/Lampadaires.png",64,64);
		image = ss.getSprite(0, Game.random.nextInt(2));
	}

	public void render(Graphics g)
	{
		g.drawImage(image, x, y);
	}
	
	public boolean update(int delta)
	{
		y += delta *PhaseVoiture.speed;
		return y > Game.app.getHeight() || y < -129f;
	}

}
