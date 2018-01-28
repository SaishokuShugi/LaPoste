import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Parametres extends BasicGameState
{
	protected static int	difficulte, pVolume = 50;
	private static int		choix, time = 0;
	private boolean			go	= false;
	private float			vx	= 1000;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		difficulte = 1;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		MenuStart.startBackground.draw(0, 0);
		MenuStart.R4.draw(vx, 550);
		g.setColor(Color.yellow);
		g.fillRect(90, 90, 260, 50);
		g.fillRect(90, 190, 260, 50);
		g.fillRect(90, 290, 200, 50);
		Game.slicker.drawString(100, 100, "Difficultee :");
		Game.slicker.drawString(100, 200, "Volume :");
		Game.slicker.drawString(100, 300, "Main Menu");
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
		g.setLineWidth(5);
		g.setColor(Color.blue);
		if (choix == 0 | choix == 1)
		{
			g.drawRect(240, 90 + ((float) choix * 100), 110, 50);
		} else
		{
			g.drawRect(90, 90 + ((float) choix * 100), 200, 50);
		}
		Game.slicker.drawString(900, 20, "Commandes voiture :");
		Game.slicker.drawString(950, 70, "Fleches <- -> Permet de rouler à gauche/droite,");
		Game.slicker.drawString(1040, 120, "Fleches ^ v Permet d'accellerer / freiner.");
		Game.slicker.drawString(1080, 170, "Commandes à pied :");
		Game.slicker.drawString(1100, 220, "Fleche ^ ou espace pour sauter,");
		Game.slicker.drawString(1080, 270, "Fleches <- -> Permet de marcher.");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		boolean down = Game.input.isKeyPressed(Input.KEY_DOWN), up = Game.input.isKeyPressed(Input.KEY_UP),
				left = Game.input.isKeyPressed(Input.KEY_LEFT), right = Game.input.isKeyPressed(Input.KEY_RIGHT),
				enter = Game.input.isKeyPressed(Input.KEY_ENTER);

		if (down)
		{
			choix++;
		} else if (up & choix > 0)
		{
			choix--;
		} else if (up & choix == 0)
		{
			choix = 2;
		}
		choix %= 3;

		if (choix == 0)
		{
			if (right)
			{
				difficulte++;
			} else if (left & difficulte > 0)
			{
				difficulte--;
			} else if (left & difficulte == 0)
			{
				difficulte = 2;
			}
			difficulte %= 3;
		} else if (choix == 1)
		{
			if (right)
			{
				pVolume += 10;
			} else if (left & pVolume > 0)
			{
				pVolume -= 10;
			} else if (left & pVolume == 0)
			{
				pVolume = 90;
			}
			pVolume %= 100;
			gc.setMusicVolume(((float) (pVolume)) / 100);
		}
		if (enter & choix == 2)
		{
			go = true;
			Game.pouet.play();
		}
		if (go & time < 2000)
		{
			time += delta;
			vx += delta;
		} else if (go)
		{
			time = 0;
			go = false;
			vx = 1000;
			Game.state = 100;
			sbg.enterState(100);
		}
	}

	@Override
	public int getID()
	{
		return 1;
	}

}
