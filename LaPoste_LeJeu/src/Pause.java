import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState
{
	

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		MenuStart.startBackground.draw(75, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		if (Game.input.isKeyDown(Input.KEY_ESCAPE)||Game.input.isKeyDown(Input.KEY_P))
			sbg.enterState(Game.state);

	}

	@Override
	public int getID()
	{
		// TODO Stub de la méthode généré automatiquement
		return 4;
	}

}
