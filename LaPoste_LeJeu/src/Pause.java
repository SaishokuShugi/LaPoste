import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState
{

	public Pause()
	{
		// TODO Stub du constructeur généré automatiquement
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		MenuStart.startBackground.draw(75, 0);
	}


	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public int getID()
	{
		// TODO Stub de la méthode généré automatiquement
		return 4;
	}

}