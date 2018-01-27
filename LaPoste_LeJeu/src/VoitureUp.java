import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VoitureUp extends Voiture
{

	public VoitureUp() throws SlickException
	{

		y = -128f;
		x = Game.app.getWidth() * .5f - 64f + 256f;
		voituresImg = new SpriteSheet("res/SpriteSheetCar.png",128,128);;
		image = voituresImg.getSprite(Game.random.nextInt(3), Game.random.nextInt(3));
		speed = .15f;
		if (Game.random.nextBoolean())
			{
				speed = .1f;
				x -= 128f;
			}

	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image, x, y);
	}

	@Override
	public boolean update(int delta)
	{
		y += delta * speed;
		return y > Game.app.getHeight();
	}

}
