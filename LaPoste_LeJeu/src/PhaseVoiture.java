import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PhaseVoiture extends BasicGameState
{

	protected static float				VoitureX	= Game.app.getWidth() * .5f + 128f;
	protected static float				VoitureY	= Game.app.getHeight() - 300f;
	protected static Image				VoitureHero, Road;
	protected static SpriteSheet		Trottoirs;
	protected static ArrayList<Voiture>	Voitures	= new ArrayList<Voiture>();
	protected int						time		= 0;
	public static float					speed		= 2.f;
	protected static float				posroad		= 0;
	private static float				vitesseX	= 0f;
	private int							bordsSolg[]	= new int[6];
	private int							bordsSold[]	= new int[6];

	public PhaseVoiture()
	{
	}

	public void initBords()
	{
		for (int i = 0; i < 5; i++)
			{
				bordsSold[i] = Game.random.nextInt(3);
				bordsSolg[i] = Game.random.nextInt(3);

			}
	}

	public void updateTrot()
	{
		for (int j = 4; j >= 0; j--)
			{
				bordsSold[j + 1] = bordsSold[j];
				bordsSolg[j + 1] = bordsSolg[j];
			}
		bordsSold[0] = Game.random.nextInt(3);
		bordsSolg[0] = Game.random.nextInt(3);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		VoitureHero = new Image("res/Car.png");
		Road = new Image("res/Route.png");
		Trottoirs = new SpriteSheet("res/SpriteRoute.png", 256, 256);
		initBords();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		for (int i = 0; i < (int) (Game.app.getHeight() / 128f) + 1; i++)
			{
				g.drawImage(Road, Game.app.getWidth() * .5f + 64f, (float) ((i - 1) * 128. + posroad % 128.));
			}
		Road.rotate(180);
		for (int i = 0; i < (int) (Game.app.getHeight() / 128f) + 1; i++)
			{
				g.drawImage(Road, Game.app.getWidth() * .5f - 320f, (float) ((i - 1) * 128. + posroad % 128.));
			}
		Road.rotate(180);

		for (int i = 0; i < (int) (Game.app.getHeight() / 256f) + 2; i++)
			{
				float dep = (float) ((i - 1) * 256. + posroad % 256.);
				Image trott = Trottoirs.getSprite(bordsSold[i] % 2, bordsSold[i] / 2);
				g.drawImage(trott, Game.app.getWidth() * .5f + 320f, dep);
				trott = Trottoirs.getSprite(bordsSolg[i] % 2, bordsSolg[i] / 2);
				trott.rotate(180);
				g.drawImage(trott, Game.app.getWidth() * .5f - 576f, dep);
			}

		g.drawImage(VoitureHero, VoitureX, VoitureY);

		for (Voiture voiture : Voitures)
			{
				voiture.render(g);
			}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = Game.input;
		if (input.isKeyDown(Input.KEY_ESCAPE) || input.isKeyDown(Input.KEY_P))
			sbg.enterState(4);
		time += delta;
		VoitureX += vitesseX * delta;
		vitesseX *= Math.pow(.992, delta);
		if (input.isKeyDown(Input.KEY_LEFT))
			vitesseX -= delta * .001 * speed;
		if (input.isKeyDown(Input.KEY_RIGHT))
			vitesseX += delta * .001 * speed;
		speed *= Math.pow(.9997, delta);
		if (input.isKeyDown(Input.KEY_UP))
			speed += delta * .004;
		if (input.isKeyDown(Input.KEY_DOWN))
			speed -= delta * .01;

		speed = speed < 1.5 ? 1.5f : speed;
		// posroad += .1 * speed * delta;

		Voitures.removeIf((Voiture Voitures) -> (Voitures.update(delta))); // cherche pas c'est magique
		if ((int) ((speed - Voiture.v0) * time / 6000) > (int) ((speed - Voiture.v0) * (time - delta) / 6000))
			{
				if (Game.random.nextInt(3) <= 1)
					Voitures.add(new VoitureUp());
			}
		if ((int) ((speed + Voiture.v0) * time / 6000) > (int) ((speed + Voiture.v0) * (time - delta) / 6000))
			{
				if (Game.random.nextInt(3) <= 1)
					Voitures.add(new VoitureDown());
			}
		if ((int) (posroad / 256f) != (int) ((posroad += .1 * speed * delta) / 256f))
			updateTrot();
	}

	@Override
	public int getID()
	{
		return 0;
	}

}
