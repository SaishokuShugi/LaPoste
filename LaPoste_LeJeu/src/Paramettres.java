import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Paramettres extends BasicGameState
{
	protected static int	difficulte;
	protected static float	volume;
	private static int		choix, pVolume;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		difficulte = 1;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		MenuStart.startBackground.draw(75, 0);
		Game.slicker.drawString(100, 100, "Difficulte :");
		Game.slicker.drawString(100, 200, "Volume");
		switch (difficulte)
		{
			case 0:
			{
				Game.slicker.drawString(250, 100, "Easy");
				break;
			}
			case 1:
			{
				Game.slicker.drawString(250, 100, "Normal");
				break;
			}
			case 2:
			{
				Game.slicker.drawString(250, 100, "Hard");
				break;
			}
		}
		
		Game.slicker.drawString(250, 200, Integer.toString(pVolume));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException
	{
		boolean down = Game.input.isKeyPressed(Input.KEY_DOWN), up = Game.input.isKeyPressed(Input.KEY_UP),
				left = Game.input.isKeyPressed(Input.KEY_LEFT), right = Game.input.isKeyPressed(Input.KEY_RIGHT);
		if (down)
		{
			choix++;
		}
		else if (up & choix > 0)
		{
			choix--;
		}
		else if (up & choix == 0)
		{
			choix = 1;
		}
		choix %= 2;
		
		if (choix == 0)
		{
			if (right)
			{
				difficulte++;
			}
			else if (left & difficulte > 0)
			{
				difficulte--;
			}
			else if (left & difficulte == 0)
			{
				difficulte = 2;
			}
			difficulte %= 3;
		}
		else if (choix ==1)
		{
			if (right)
			{
				pVolume++;
			}
			else if (left & pVolume > 0)
			{
				pVolume--;
			}
			else if (left & pVolume == 0)
			{
				pVolume = 99;
			}
			pVolume %= 100;
			volume = pVolume/100;
		}
	}

	@Override
	public int getID()
	{
		return 1;
	}

}
