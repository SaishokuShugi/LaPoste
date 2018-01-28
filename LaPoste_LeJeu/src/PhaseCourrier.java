import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
//la fatigue
public class PhaseCourrier extends BasicGameState
{
	private String				propriétairePorte;
	private Image				boite;
	private ArrayList<Image>	enveloppes		= new ArrayList<Image>();
	private int					lettrevisible	= 0;
	private int					compteur		= 0, timep = 0;

	@Override
	public void init(GameContainer gc, StateBasedGame sgb) throws SlickException
	{
		// TODO Auto-generated method stub
		System.out.println(PhasePlateform.porteOpen);
		propriétairePorte = PhasePlateform.Destinataire.get(PhasePlateform.porteOpen);
		System.out.println(propriétairePorte);
		boite = new Image("res/Boite aux Lettres.png");
		for (int i = 0; i < Game.nlettres; i++)
			{
				enveloppes.add(new Image("res/enveloppe zoom2.png"));
			}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		// TODO Auto-generated method stub

		boite.draw(800, 0);
		Game.slicker.drawString(1300, 450, propriétairePorte);
		//for (int i = 0; i < lettrevisible; i++)
		//	{
		int i = lettrevisible;
				enveloppes.get(i).draw(0, 0);
		if (i<PhasePlateform.Lettre.size())
				Game.slicker.drawString(225, 300, PhasePlateform.Lettre.get(i));
		//	}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		// TODO Auto-generated method stub
		propriétairePorte = PhasePlateform.Destinataire.get(PhasePlateform.porteOpen);
		timep += delta;
		if (Game.input.isKeyPressed(Input.KEY_UP))
			{
				if (lettrevisible < PhasePlateform.Lettre.size() - 1 & compteur > 120)
					{
						lettrevisible++;
						compteur = 0;
					}
			}
		if (Game.input.isKeyPressed(Input.KEY_DOWN))
			{
				if (lettrevisible > 0 & compteur > 120)
					{
						lettrevisible--;
						compteur = 0;
					}
			}
		compteur+=delta;
		if (Game.input.isKeyPressed(Input.KEY_ENTER) && timep > 120)
			{
				timep = 0;
				System.out.println(PhasePlateform.Lettre.size());
				
				
				if (lettrevisible >= 0)
					if(PhasePlateform.Lettre.get(lettrevisible)==propriétairePorte)
						Game.score+=1*10000000f/(System.currentTimeMillis()-Game.timeinit);
				if 	(PhasePlateform.Lettre.size()!=0)
					PhasePlateform.Lettre.remove(lettrevisible);
				if (lettrevisible>0)
					lettrevisible--;
				if (PhasePlateform.Lettre.size()<=0) {
					sbg.enterState(8);}
			}
		if(Game.input.isKeyPressed(Input.KEY_ESCAPE)&& timep > 120)
			{
				timep =0;
				sbg.enterState(5);
			}
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 7;
	}

}
