package poste;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

public class Lampadaire
{
	protected float		x, y;
	protected Image		image;
	protected boolean	destroyed	= false;
	protected Circle	hitbox;
	protected int		timedest	= 0;

	public Lampadaire() throws SlickException
	{
		y = -128f;
		x = Game.app.getWidth() * .5f - 32;
		SpriteSheet ss = new SpriteSheet("res/Lampadaires.png", 64, 64);
		image = ss.getSprite(0, Game.random.nextInt(2));
		hitbox = new Circle(x, y , 32);
	}

	public void render(Graphics g)
	{
		g.drawImage(image, x, y);
		if (destroyed)
			{
				if ((PhaseVoiture.time - timedest) < 16 * Game.boom.f)
					Game.boom.draw(x-32, y-32, g, PhaseVoiture.time - timedest);

			}
	}

	public boolean update(int delta)
	{
		y += delta * PhaseVoiture.speed * .1;
		hitbox.setLocation(x, y);
		if (!destroyed && (PhaseVoiture.VoitureHitbox.intersects(hitbox)))
			{
				timedest = PhaseVoiture.time;
				destroyed = true;
				PhaseVoiture.iscarhit =true;
			}
		return y > Game.app.getHeight() || y < -129f;
	}

}
