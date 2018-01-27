import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class VoitureDown extends Voiture {

	public VoitureDown() throws SlickException {
		y = 0f;
		x = Game.app.getWidth() * .5f - 64f - 200f;
		image = new Image("res/Car2.png");
		image.rotate(180f);
		speed = .5f;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y);
	}

	@Override
	public void update(int delta) {
		y += delta * speed;
	}

}
