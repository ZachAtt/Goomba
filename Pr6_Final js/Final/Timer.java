package Final;

import javafx.animation.AnimationTimer;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import java.util.Random;

import javax.swing.JOptionPane;

public class Timer extends AnimationTimer
{
  Circle pl;
  Goomba g;
  int cnt;
  int deltaY;
  int deltaX;
  double sum;
  long deltaTime;
  long changeTime;
  int plScore;
  int gScore;
  int numberOfRounds;
  boolean haveWinner;
  boolean matchActive;
  TextField plScoreText;
  TextField gScoreText;
  
  private Random random;
  
  public Timer(Circle pl, TextField plScoreText, Goomba g, TextField gScoreText)
  {
	this.pl = pl;
	this.plScoreText = plScoreText;
	this.g = g;
	this.gScoreText = gScoreText;
	plScore = 0;
	gScore = 0;
	cnt = 0;
	deltaY = 2;
	deltaX = 3;
	sum = pl.getRadius() + g.getRadius();
	deltaTime = 5000000000l;
	changeTime = deltaTime;
	numberOfRounds = 2;
	haveWinner = false;
	matchActive = false;
	
	random = new Random();

  }
  
  protected void move(long now)
  {
	if(now > changeTime)
	{
		if(random.nextInt(2) == 1)
		{
			deltaX = 3;
		}
		else
		{
			deltaX = -3;
		}
		
		if(random.nextInt(2) == 1)
		{
			deltaY = 2;
		}
		else
		{
			deltaY = -2;
		}
	}
	
	if(g.getCenterY() <= g.getRadius())
	{
		deltaY = 2;
	}
	else if(g.getCenterY() >= (325 - g.getRadius()))
	{
		deltaY = -2;
	}
	
	if(g.getCenterX() <= g.getRadius())
	{
		deltaX = 3;
	}
	else if(g.getCenterX() >= (450 - g.getRadius()))
	{
		deltaX = -3;
	}
	
	
	g.setCenterX(g.getCenterX() + deltaX);
	g.setCenterY(g.getCenterY() + deltaY);
  }
  
  protected void changeImage(long now)
  {
	if(now > changeTime)
	{
		cnt = random.nextInt(5);
		switch(cnt)
		{
		case 0:
			g.setFill(g.rockImgPat);
			break;
		case 1:
			g.setFill(g.paperImgPat);
			break;
		case 2:
			g.setFill(g.scissorsImgPat);
			break;
		case 3:
			g.setFill(g.lizardImgPat);
			break;
		case 4:
			g.setFill(g.spockImgPat);
			break;
		default:
			break;
		}
	}
  }
  
  protected boolean checkIntersection()
  {
	double d = Math.sqrt( Math.pow(g.getCenterX() - pl.getCenterX(), 2.0) + Math.pow(g.getCenterY() - pl.getCenterY(), 2.0));
	  
	 
	return (d < sum);
  }
  
  public void start()
  {
	
	if(!haveWinner)
	{
		matchActive = true;
	}
	else
	{
		haveWinner = false;
		plScore = 0;
		gScore = 0;
		plScoreText.setText("0");
		gScoreText.setText("0");
		matchActive = false;
	}
	
	
	super.start();
  }
  
  public void setNumberOfRounds(int numberOfRounds)
  {
	this.numberOfRounds = numberOfRounds;
  }
  
  public boolean getMatchActive()
  {
	return matchActive;
  }
  
  protected void determineWinner()
  {
	if (pl.getFill() != g.getFill() )
	{
		if(pl.getFill() == g.rockImgPat )
		{
			if(g.getFill() == g.scissorsImgPat || g.getFill() == g.lizardImgPat)
			{
				pl.setFill(g.trophyImgPat);
				++plScore;
			}
			else
			{
				g.setFill(g.trophyImgPat);
				++gScore;
			}
		}
		else if(pl.getFill() == g.paperImgPat )
		{
			if(g.getFill() == g.rockImgPat || g.getFill() == g.spockImgPat)
			{
				pl.setFill(g.trophyImgPat);
				++plScore;
			}
			else
			{
				g.setFill(g.trophyImgPat);
				++gScore;
			}
		}
		else if(pl.getFill() == g.scissorsImgPat )
		{
			if(g.getFill() == g.paperImgPat || g.getFill() == g.lizardImgPat)
			{
				pl.setFill(g.trophyImgPat);
				++plScore;
			}
			else
			{
				g.setFill(g.trophyImgPat);
				++gScore;
			}
		}
		else if(pl.getFill() == g.lizardImgPat )
		{
			if(g.getFill() == g.paperImgPat || g.getFill() == g.spockImgPat)
			{
				pl.setFill(g.trophyImgPat);
				++plScore;
			}
			else
			{
				g.setFill(g.trophyImgPat);
				++gScore;
			}
		}
		else //must be Spock
		{
			if(g.getFill() == g.scissorsImgPat || g.getFill() == g.rockImgPat)
			{
				pl.setFill(g.trophyImgPat);
				++plScore;
			}
			else
			{
				g.setFill(g.trophyImgPat);
				++gScore;
			}
		}
		stop();
		
		plScoreText.setText(String.valueOf(plScore));
		gScoreText.setText(String.valueOf(gScore));
		
		if(plScore == numberOfRounds)
		{
			JOptionPane.showMessageDialog(null, "Player wins!");
			pl.setFill(g.crownImgPat);
			haveWinner = true;
		}
		else if(gScore == numberOfRounds)
		{
			JOptionPane.showMessageDialog(null, "Computer wins!");
			g.setFill(g.crownImgPat);
			haveWinner = true;
		}
	}

  }
  
  public void handle(long now)
  {
	
	move(now);
	
	changeImage(now);
	
	if(checkIntersection())
	{
		determineWinner();
	}
	
	if (now > changeTime)
	{
		changeTime = now + (random.nextLong() % deltaTime);
	}
  }//end handle
}//end Timer
