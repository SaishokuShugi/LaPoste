import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class VoitureUp extends Voiture
{

	protected Image	image;
	protected float	x, y, speed = .1f;

	public VoitureUp() throws SlickException
	{
		y = 0f;
		x = Game.app.getScreenWidth() * .5f + 200f;
		image = new Image("res/Car2.png");
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image, x, y);
	}

	@Override
	public void update(int delta)
	{
		y += delta * speed;
	}

}
