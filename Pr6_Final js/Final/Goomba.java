package Final;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Goomba extends Circle
{
	private static Image rockImg = new Image("Rock.jpg");
	private static Image paperImg = new Image("Paper.jpg");
	private static Image scissorsImg = new Image("Scissors.jpg");
	private static Image lizardImg = new Image("Lizard.jpg");
	private static Image spockImg = new Image("Spock.jpg");
	private static Image trophyImg = new Image("Trophy.jpg");
	private static Image crownImg = new Image("Crown.jpg");
	public static ImagePattern rockImgPat = new ImagePattern(rockImg);
	public static ImagePattern paperImgPat = new ImagePattern(paperImg);
	public static ImagePattern scissorsImgPat = new ImagePattern(scissorsImg);
	public static ImagePattern lizardImgPat = new ImagePattern(lizardImg);
	public static ImagePattern spockImgPat = new ImagePattern(spockImg);
	public static ImagePattern trophyImgPat = new ImagePattern(trophyImg);
	public static ImagePattern crownImgPat = new ImagePattern(crownImg);
	
	public Goomba(double x, double y, double r)
	{
		super(x, y, r);
	}

}
