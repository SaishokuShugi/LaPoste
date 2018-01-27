import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Voiture {
	public static Random random;
	protected Image image;
	protected float x, y, speed;
	
	public abstract void render(Graphics g);

	public abstract void update(int delta);
	

}
