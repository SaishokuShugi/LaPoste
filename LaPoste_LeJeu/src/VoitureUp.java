import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class VoitureUp extends Voiture
{

	public VoitureUp() throws SlickException
	{

		y = -128f;
		x = Game.app.getWidth() * .5f - 64f + 256f;
		image = voituresImg.getSprite(Game.random.nextInt(3), Game.random.nextInt(3));
		hitbox = new Rectangle(x+image.getWidth()/4f, y+image.getWidth()/6f, image.getWidth()/2f, image.getHeight()*2f/3f);
		if (voie = Game.random.nextBoolean())
			x -= 128f;

	}

	@Override
	public boolean update(int delta)
	{
		speed = (PhaseVoiture.speed - v0) * .1f + (voie ? 0 : .05f);
		y += delta * speed;
		hitbox.setLocation(x+image.getWidth()/4f, y+image.getWidth()/6f);
		if (!destroyed && (PhaseVoiture.VoitureHitbox.intersects(hitbox)))
			{
				timedest = PhaseVoiture.time;
				destroyed = true;
				PhaseVoiture.iscarhit =true;
			}
		return y > Game.app.getHeight() || y < -129f;

	}

}
