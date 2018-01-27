import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Paramettres extends BasicGameState
{

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		Game.Difficultee = 1;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		MenuStart.startBackground.draw(75, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException
	{

	}

	@Override
	public int getID()
	{
		return 1;
	}

}
