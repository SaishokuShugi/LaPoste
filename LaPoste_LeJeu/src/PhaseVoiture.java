import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class PhaseVoiture extends BasicGameState
{

	protected static float						VoitureX		= Game.app.getWidth() * .5f + 128f;
	protected static float						VoitureY		= Game.app.getHeight() - 300f;
	protected static Image						VoitureHero, Road, Progression, minicar;
	protected static Rectangle					VoitureHitbox;
	protected static SpriteSheet				Trottoirs, Toits, CentreSprite;
	protected static ArrayList<Voiture>			Voitures		= new ArrayList<Voiture>();
	protected static ArrayList<Lampadaire>		lampes			= new ArrayList<Lampadaire>();
	protected static ArrayList<PaperParticle>	lastPerte		= new ArrayList<PaperParticle>();
	public static int							time			= 0, timeP = 0, timerp = -999999;;
	public static float							speed			= 2.f;
	public static boolean						iscarhit		= false;
	protected static float						posroad			= 0, postoit = 0;
	protected Animation							perte;
	private static float						vitesseX		= 0f;
	private int									bordsSolg[]		= new int[6];
	private int									bordsSold[]		= new int[6];
	private int									bordsToitg[]	= new int[6];
	private int									bordsToitd[]	= new int[6];
	private int									centreTrot[]	= new int[6];

	public PhaseVoiture()
	{
	}

	public void initBords()
	{
		for (int i = 0; i < 5; i++)
		{
			bordsSold[i] = Game.random.nextInt(6);
			bordsSolg[i] = Game.random.nextInt(6);

			bordsToitd[i] = Game.random.nextInt(6);
			bordsToitg[i] = Game.random.nextInt(6);

			centreTrot[i] = Game.random.nextInt(4);
		}
	}

	public void updateTrot()
	{
		for (int j = 4; j >= 0; j--)
		{
			bordsSold[j + 1] = bordsSold[j];
			bordsSolg[j + 1] = bordsSolg[j];
		}
		bordsSold[0] = Game.random.nextInt(6);
		bordsSolg[0] = Game.random.nextInt(6);
	}

	public void updateCentre()
	{
		for (int j = 4; j >= 0; j--)
		{
			centreTrot[j + 1] = centreTrot[j];
		}
		centreTrot[0] = Game.random.nextInt(4);
	}

	public void updateToit()
	{
		for (int j = 4; j >= 0; j--)
		{
			bordsToitd[j + 1] = bordsToitd[j];
			bordsToitg[j + 1] = bordsToitg[j];
		}
		bordsToitd[0] = Game.random.nextInt(6);
		bordsToitg[0] = Game.random.nextInt(6);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		gc.setMouseGrabbed(true);
		VoitureHero = new Image("res/Car.png");
		Road = new Image("res/Route.png");
		Progression = new Image("res/BarreDeProgression.png");
		minicar = new Image("res/MiniCar.png");
		Trottoirs = new SpriteSheet("res/SpriteRoute.png", 256, 256);
		Toits = new SpriteSheet("res/SpriteToit.png", 256, 256);
		CentreSprite = new SpriteSheet("res/SpriteCenter.png", 128, 256);
		VoitureHitbox = new Rectangle(VoitureX + VoitureHero.getWidth() / 4f, VoitureY + VoitureHero.getWidth() / 6f, VoitureHero.getWidth() / 2f, VoitureHero.getHeight() * 2f / 3f);
		initBords();
		perte = new Animation(new SpriteSheet("res/PerteCourrier.png", 128, 128), 50);
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
			Image trott = CentreSprite.getSprite(centreTrot[i] % 2, centreTrot[i] / 2);
			g.drawImage(trott, Game.app.getWidth() * .5f - 64f, dep);
		}

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

		Voitures.removeIf((Voiture Voitures) -> (Voitures.render(g)));
		for (Lampadaire l : lampes)
		{
			l.render(g);
		}
		for (int i = 0; i < (int) (Game.app.getHeight() / 256f) + 2; i++)
		{
			float dep = (float) ((i - 1) * 256. + postoit % 256.);
			Image trott = Toits.getSprite(bordsToitd[i] % 2, bordsToitd[i] / 2);
			g.drawImage(trott, Game.app.getWidth() * .5f + 320f, dep);
			trott = Toits.getSprite(bordsToitg[i] % 2, bordsToitg[i] / 2);
			trott.rotate(180);
			g.drawImage(trott, Game.app.getWidth() * .5f - 576f, dep);
		}

		for (PaperParticle pp : lastPerte)
		{
			if ((time - pp.t0) < 10 * perte.f)
				perte.draw(pp.x, pp.y, g, time - pp.t0);
		}
		lastPerte.removeIf((PaperParticle lastPerte) -> (time - lastPerte.t0 > 10 * perte.f));
		Progression.draw(400, 0);
		minicar.draw(400 + posroad / 100, 0);
		Game.shuriken.draw(250, 0);
		Game.slicker.drawString(270, 5, " X " + Integer.toString(Game.nlettres));

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{

		if ((Game.input.isKeyDown(Input.KEY_ESCAPE) || Game.input.isKeyDown(Input.KEY_P)) & timeP > 250)
		{
			timeP = 0;
			sbg.enterState(4);
		}
		time += delta;
		timeP += delta;
		VoitureX += vitesseX * delta;
		if ((VoitureX + 64 < 480 | VoitureX + 64 > Game.app.getWidth() - 480) && time - timerp > 100)
			iscarhit = true;
		VoitureX = VoitureX + 64 < 400 ? 400 - 64 : VoitureX + 64 > Game.app.getWidth() - 400 ? Game.app.getWidth() - 400 - 64 : VoitureX;
		vitesseX *= Math.pow(.992, delta);
		if (Game.input.isKeyDown(Input.KEY_LEFT))
			vitesseX -= delta * .001 * speed;
		if (Game.input.isKeyDown(Input.KEY_RIGHT))
			vitesseX += delta * .001 * speed;
		speed *= Math.pow(.9997, delta);
		if (Game.input.isKeyDown(Input.KEY_UP))
			speed += delta * .004;
		if (Game.input.isKeyDown(Input.KEY_DOWN))
			speed -= delta * .01;

		if (Game.nlettres <= 0)
		{
			sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
		}

		speed = speed < 1.5 ? 1.5f : speed;

		VoitureHitbox.setLocation(VoitureX + VoitureHero.getWidth() / 4f, VoitureY + VoitureHero.getWidth() / 6f);

		Voitures.removeIf((Voiture Voitures) -> (Voitures.update(delta))); // cherche pas c'est magique
		lampes.removeIf((Lampadaire lampes) -> (lampes.update(delta)));
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
		if ((int) (speed * time / 6000) > (int) (speed * (time - delta) / 6000))
		{
			if (Game.random.nextInt(3) <= 1)
				lampes.add(new Lampadaire());
		}
		if ((int) (posroad / 256f) != (int) ((posroad += .1 * speed * delta) / 256f))
		{
			updateTrot();
			updateCentre();
		}
		if ((int) (postoit / 256f) != (int) ((postoit += .103 * speed * delta) / 256f))
			updateToit();

		if (iscarhit)
		{
			System.out.println(Game.nlettres);
			Game.nlettres--;
			System.out.println(Game.nlettres);
			lastPerte.add(new PaperParticle());
			timerp = time;
		}
		iscarhit = false;

		if (posroad > 77000)
		{
			speed -= 0.015 * delta;
			VoitureX += 0.01 * delta;
			if (VoitureX >= 1045)
			{
				VoitureX = 1045;
				speed = 0;
				sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
			}
		}
	}

	@Override
	public int getID()
	{
		return 0;
	}

}
