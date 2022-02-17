package Final;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;

import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainClass extends Application
{
	Pane p;
	int imageIndex;
	boolean isRoundsActive;
  public static void main(String[] args)
  {
	launch(args);
  }
  
  public void init(Circle pl, Goomba g)
  {
	pl.setStroke(Color.BLUE);
	pl.setStrokeWidth(2);
	pl.setFill(g.spockImgPat);
	g.setStroke(Color.RED);
	g.setStrokeWidth(2);
	g.setFill(g.spockImgPat);
	imageIndex = 5;
	isRoundsActive = false;
	
	pl.setCenterX(pl.getRadius() + 10);
	pl.setCenterY(pl.getRadius() + 10);
	g.setCenterX(200);
	g.setCenterY(200);
  }

  public void start(Stage stage)
  {
	p = new Pane();
	p.setMinSize(400, 325);
	
	p.setBackground(new Background(new BackgroundFill( Color.rgb(190, 220, 190), null, null) ));
	
	// Circles
	Circle pl = new Circle(100, 100, 32);
	Goomba g = new Goomba(200, 200, 24);
	
	init(pl, g);
	
	p.getChildren().addAll(pl, g);
	
	TextField plScoreV = new TextField("0");
	TextField gScoreV = new TextField("0");
	TextField rounds = new TextField("2");
	
	plScoreV.setEditable(false);
	gScoreV.setEditable(false);
	rounds.setEditable(false);
	
	plScoreV.setFocusTraversable(false);
	gScoreV.setFocusTraversable(false);
	rounds.setFocusTraversable(false);
	
	plScoreV.setDisable(true);
	gScoreV.setDisable(true);
	rounds.setDisable(true);
	
	Timer t = new Timer(pl, plScoreV, g, gScoreV);
	
	
	Button btnRun = new Button("Run");
	btnRun.setFocusTraversable(false);
	btnRun.setOnAction((ActionEvent e)-> {
		rounds.setEditable(false);
		rounds.setDisable(true);
	  init(pl, g);
	  t.start();
	});
	
	Button btnStop = new Button("Stop");
	btnStop.setFocusTraversable(false);
	btnStop.setOnAction((ActionEvent e)-> {
	  t.stop();
	});
	
	Button btnHelp = new Button("Help");
	btnHelp.setFocusTraversable(false);
	btnHelp.setOnAction((ActionEvent e)-> {
	  System.out.println("Use the arrow keys to move the Player (blue) circle.");
	  System.out.println("Press the spacebar or number keys 1, 2, 3, 4, and 5 to switch between the rock, paper, scissors, lizard, and spock images for the Player circle.\n");
	  System.out.println("Actions for number keys 1, 2, 3, 4, and 5:");
	  System.out.println("Press 1 for Rock.");
	  System.out.println("Press 2 for Paper.");
	  System.out.println("Press 3 for Scissors.");
	  System.out.println("Press 4 for Lizard.");
	  System.out.println("Press 5 for Spock.");
	});
	
	Button btnRounds = new Button("Rounds");
	btnRounds.setFocusTraversable(false);
	btnRounds.setOnAction((ActionEvent e)-> {
	  if(isRoundsActive)
	  {
		isRoundsActive = false;
		rounds.setEditable(false);
		rounds.setDisable(true);
		t.setNumberOfRounds(Integer.parseInt(rounds.getText()));
	  }
	  else if(!t.getMatchActive() || pl.getFill() == g.crownImgPat || g.getFill() == g.crownImgPat)
	  {
		isRoundsActive = true;
		rounds.setEditable(true);
		rounds.setDisable(false);
	  }
	});
		
	GridPane gp = new GridPane();
	gp.setBackground(new Background(new BackgroundFill( Color.rgb(150, 200, 150), null, null) ));
	gp.setHgap(10);
	
	Label plScoreL = new Label("Player");
	plScoreV.setMaxWidth(50);
	
	Label gScoreL = new Label("Computer");
	gScoreV.setMaxWidth(50);
	
	rounds.setMaxWidth(50);
	
	gp.add(btnRun, 0, 0, 1, 1);
	gp.add(btnHelp, 1, 0, 1, 1);
	gp.add(plScoreL, 3, 0, 1, 1);
	gp.add(plScoreV, 4, 0, 1, 1);
	gp.add(btnStop, 0, 1, 1, 1);
	gp.add(btnRounds, 1, 1, 1, 1);
	gp.add(rounds, 2, 1, 1, 1);
	gp.add(gScoreL, 3, 1, 1, 1);
	gp.add(gScoreV, 4, 1, 1, 1);

	// Create vertical box
	VBox vb = new VBox();
	vb.setPadding(new Insets(15,15,15,15));
	vb.setSpacing(15);
	vb.setBackground(new Background(new BackgroundFill( Color.rgb(100, 100, 100), null, null) ));
	
	vb.getChildren().addAll(p, gp);
	
	Scene scene = new Scene(vb);
	
	scene.setOnKeyPressed(new EventHandler<KeyEvent>()
	{
		public void handle(KeyEvent event)
		{
			switch(event.getCode())
			{
			case UP:
			pl.setCenterY(pl.getCenterY() - 10);
			break;
			case DOWN:
			pl.setCenterY(pl.getCenterY() + 10);
				break;
			case LEFT:
			pl.setCenterX(pl.getCenterX() - 10);
				break;
			case RIGHT:
				pl.setCenterX(pl.getCenterX() + 10);
				break;
			case DIGIT1:
				imageIndex = 1;
				pl.setFill(g.rockImgPat);
				break;
			case DIGIT2:
				imageIndex = 2;
				pl.setFill(g.paperImgPat);
				break;
			case DIGIT3:
				imageIndex = 3;
				pl.setFill(g.scissorsImgPat);
				break;
			case DIGIT4:
				imageIndex = 4;
				pl.setFill(g.lizardImgPat);
				break;
			case DIGIT5:
				imageIndex = 5;
				pl.setFill(g.spockImgPat);
				break;
			case SPACE:
				++imageIndex;
				if (imageIndex == 6)
				{
					imageIndex = 1;
				}
				
				switch(imageIndex)
				{
					case 1:
						pl.setFill(g.rockImgPat);
						break;
					case 2:
						pl.setFill(g.paperImgPat);
						break;
					case 3:
						pl.setFill(g.scissorsImgPat);
						break;
					case 4:
						pl.setFill(g.lizardImgPat);
						break;
					case 5:
					default:
						imageIndex = 5;
						pl.setFill(g.spockImgPat);
						break;
				}
			default:
				break;
			}
		}
	});
	
	stage.setScene(scene);	
	stage.setTitle("Rock, Paper, Scissors, Lizard, Spock");
	stage.setWidth(500.0);
	stage.setHeight(460.0);
	
	
	stage.show();  
  }
}