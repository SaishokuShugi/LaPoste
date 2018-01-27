import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState
{
	int timer = 0, choix = 0, pVolume = Paramettres.pVolume;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		MenuStart.startBackground.draw(0, 0);
		MenuStart.R4.draw(1000, 550);
		Game.slicker.drawString(100, 100, "Volume");
		Game.slicker.drawString(100, 200, "Resume");
		Game.slicker.drawString(100, 300, "Main Menu");
		Game.slicker.drawString(250, 100, Integer.toString(pVolume));
		g.setColor(Color.darkGray);
		if (choix == 0)
		{
			g.drawRect(240, 90 + ((float) choix * 100), 110, 50);
		} else
		{
			g.drawRect(90, 90 + ((float) choix * 100), 200, 50);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{

		boolean down = Game.input.isKeyPressed(Input.KEY_DOWN), up = Game.input.isKeyPressed(Input.KEY_UP), left = Game.input.isKeyPressed(Input.KEY_LEFT), right = Game.input.isKeyPressed(Input.KEY_RIGHT), enter = Game.input.isKeyPressed(Input.KEY_ENTER);

		timer += delta;
		if ((Game.input.isKeyDown(Input.KEY_ESCAPE)||Game.input.isKeyDown(Input.KEY_P) | (choix == 1 & enter)) & timer > 250)
		{
			timer = 0;
			sbg.enterState(Game.state);
		}
		
		
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
			choix = 2;
		}
		choix %= 3;
		
		if (choix ==0)
		{
			if (right)
			{
				pVolume+=10;
			}
			else if (left & pVolume > 0)
			{
				pVolume-=10;
			}
			else if (left & pVolume == 0)
			{
				pVolume = 90;
			}
			pVolume %= 100;
			gc.setMusicVolume(((float)(pVolume))/100);
		}else if(choix == 2)
		{
			if (enter)
			{
				Game.state = 100;
				sbg.enterState(100);
			}
		}

	}

	@Override
	public int getID()
	{
		// TODO Stub de la méthode généré automatiquement
		return 4;
	}

}
