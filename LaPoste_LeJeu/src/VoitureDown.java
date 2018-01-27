import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class VoitureDown extends Voiture
{

	public VoitureDown() throws SlickException
	{
		y = -128f;
		x = Game.app.getWidth() * .5f - 64f - 256f;
		voituresImg = new SpriteSheet("res/SpriteSheetCar.png",128,128);
		image = voituresImg.getSprite(Game.random.nextInt(3), Game.random.nextInt(3));
		image.rotate(180f);
		speed = .5f;
		if (Game.random.nextBoolean())
			{
				speed = .55f;
				x += 128f;
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
