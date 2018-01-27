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

	protected static float				VoitureX	= Game.app.getWidth() * .5f + 128f;
	protected static float				VoitureY	= Game.app.getHeight() - 300f;
	protected static Image				VoitureHero, Road;
	protected static ArrayList<Voiture>	Voitures	= new ArrayList<Voiture>();
	protected int						time		= 0;
	public static float					speed		= 2.f;
	private static float				vitesseX	= 0f;

	public PhaseVoiture()
	{
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		VoitureHero = new Image("res/Car.png");
		Road = new Image("res/Route.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		for (int i = 0; i < (int) (Game.app.getHeight() / 128f) + 1; i++)
			{
				g.drawImage(Road, Game.app.getWidth() * .5f + 64f, (float) ((i - 1) * 128. + (.1*speed * time) % 128.));
			}
		Road.rotate(180);
		for (int i = 0; i < (int) (Game.app.getHeight() / 128f) + 1; i++)
			{
				g.drawImage(Road, Game.app.getWidth() * .5f - 320f, (float) ((i - 1) * 128. + (.1*speed * time) % 128.));
			}
		Road.rotate(180);

		g.drawImage(VoitureHero, VoitureX, VoitureY);

		for (Voiture voiture : Voitures)
			{
				voiture.render(g);
			}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		time += delta;
		Input input = Game.input;
		VoitureX += vitesseX * delta;
		vitesseX *= .992;
		if (input.isKeyDown(Input.KEY_LEFT))
			vitesseX -= delta * .01;
		if (input.isKeyDown(Input.KEY_RIGHT))
			vitesseX += delta * .01;
		if (input.isKeyDown(Input.KEY_UP))
			speed += delta * .01;
		if (input.isKeyDown(Input.KEY_DOWN))
			speed -= delta * .01;
		speed = speed<0?0:speed;
		
		Voitures.removeIf((Voiture Voitures) -> (Voitures.update(delta))); // cherche pas c'est magique
		if ((int) (time / 1200) > (int) ((time - delta) / 1200))
			{
				if (Game.random.nextInt(8) <= 1)
					Voitures.add(new VoitureUp());
			}
		if ((int) ( time / 300) > (int) ((time - delta) / 300))
			{
				if (Game.random.nextInt(8) <= 1)
					Voitures.add(new VoitureDown());
			}
	}

	@Override
	public int getID()
	{
		return 0;
	}

}
