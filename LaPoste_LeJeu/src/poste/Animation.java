package poste;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

public class Animation
{
	private SpriteSheet	ss;
	public int			f;

	public Animation(SpriteSheet ss, int f)
	{
		this.ss = ss;
		this.f = f;
	}

	public void draw(float x, float y, Graphics g, float t)
	{
		int i = (int) (t / f) % ss.getHorizontalCount();
		int j = ((int) (t / f) / ss.getHorizontalCount()) % ss.getVerticalCount();
		g.drawImage(ss.getSprite(i, j), x, y);
	}

}
