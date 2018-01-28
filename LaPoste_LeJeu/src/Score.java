import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Score extends BasicGameState
{

	@Override
	public void init(GameContainer gc, StateBasedGame sgb) throws SlickException
	{
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.setColor(Color.yellow);
		g.fillRect(435, 300, 600, 100);
		g.fillRect(635, 745, 200, 50);
		g.setColor(Color.blue);
		g.setLineWidth(5);
		g.drawRect(635, 745, 200, 50);
		g.setLineWidth(8);
		g.drawRect(435, 300, 600, 100);
		Game.titre.drawString(630, 330, "Score ="+Game.score);
		Game.slicker.drawString(700, 760, "Exit");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		boolean enter = Game.input.isKeyPressed(Input.KEY_ENTER);
		if (enter)
		{
			System.exit(0);
		}
		
	}

	@Override
	public int getID()
	{
		// TODO Stub de la méthode généré automatiquement
		return 8;
	}

}
