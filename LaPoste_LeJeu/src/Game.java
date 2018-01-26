import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
	public Game(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		//
		
		gc.setTargetFrameRate(144);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppGameContainer app;
		try {
		app = new AppGameContainer(new Game("jeu"));
		app.setDisplayMode(800, 600, false);
		app.setShowFPS(true);
		app.start();
		} catch(SlickException e){
		e.printStackTrace();
		}
	}
	
	
}
