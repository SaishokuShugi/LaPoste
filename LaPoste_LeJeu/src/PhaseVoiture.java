import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PhaseVoiture extends BasicGameState
{

	protected float VoitureX = Game.app.getWidth() * .5f+128f;
	protected float VoitureY = Game.app.getHeight() - 300f;
	protected Image VoitureHero;
	protected ArrayList<Voiture> VoitureHaut;


	public PhaseVoiture()
	{
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		VoitureHero = new Image("res/Car.png");
		VoitureHaut.add(new VoitureUp());
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(VoitureHero, VoitureX, VoitureY);
		for (Voiture voiture : VoitureHaut) {
			voiture.render(g);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_LEFT))
			VoitureX -= delta * 1.;
		if (input.isKeyDown(Input.KEY_RIGHT))
			VoitureX += delta * 1.;
		for (Voiture voiture : VoitureHaut) {
			voiture.update(delta);
		}
	}

	@Override
	public int getID()
	{
		return 1;
	}

}
