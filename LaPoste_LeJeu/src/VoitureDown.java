import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class VoitureDown extends Voiture
{

	public VoitureDown() throws SlickException
	{
		y = -128f;
		x = Game.app.getWidth() * .5f - 64f - 256f;
		voituresImg = new SpriteSheet("res/SpriteSheetCar.png", 128, 128);
		image = voituresImg.getSprite(Game.random.nextInt(3), Game.random.nextInt(3));
		image.rotate(180f);
		hitbox=new Rectangle(x,y,image.getWidth(),image.getHeight());
		if (voie = Game.random.nextBoolean())
			x += 128f;

	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image, x, y);
	}

	@Override
	public boolean update(int delta)
	{
		speed = (PhaseVoiture.speed + v0) * .1f + (voie ? .05f : 0);
		y += delta * speed;
		hitbox.setLocation(x, y);
		if (PhaseVoiture.VoitureHitbox.intersects(hitbox))
			destroyed=true;
		return y > Game.app.getHeight();
		
	}


}
