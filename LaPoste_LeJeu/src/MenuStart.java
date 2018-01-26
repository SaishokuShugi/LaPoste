import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuStart extends BasicGameState
{
	public static Image startBackground;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// TODO Auto-generated method stub
		startBackground = new Image("res/testMenuBackground.png");



	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(startBackground, 0, 0);
		g.setColor(new Color(255,255,255,100));
		g.fillRect(0, 0, Game.app.getWidth(), Game.app.getHeight());

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
