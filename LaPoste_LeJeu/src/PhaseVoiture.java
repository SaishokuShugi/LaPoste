import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PhaseVoiture extends BasicGameState {

	protected float VoitureX = Game.app.getWidth() * .5f+128f;
	protected float VoitureY = Game.app.getHeight() - 300f;
	protected Image VoitureHero;
	//protected ArrayList<Voiture> Voiture;

	public PhaseVoiture() {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		VoitureHero = new Image("res/car.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(VoitureHero, VoitureX, VoitureY);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_LEFT))
			VoitureX -= delta * 1.;
		if (input.isKeyDown(Input.KEY_RIGHT))
			VoitureX += delta * 1.;
	}

	@Override
	public int getID() {
		return 1;
	}

}
