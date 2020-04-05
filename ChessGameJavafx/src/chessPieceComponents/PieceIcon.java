package chessPieceComponents;

import chessGame.Board;
import enumTypes.PieceTeam;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import lombok.Getter;

public class PieceIcon extends Ellipse
{
	@Getter private Ellipse shadow;
	@Getter private PieceTeam type;
	
	public PieceIcon(PieceTeam type) {
		super(Board.TILE_SIZE * 0.3125, Board.TILE_SIZE * 0.26);
		this.type = type;
		
		
		this.setFill(type == PieceTeam.BLACK
		      ? Color.valueOf("#000000") : Color.valueOf("#fff9f4"));
		
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(Board.TILE_SIZE * 0.03);
		
		this.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * 0.3125 * 2) / 2);
		this.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * 0.26 * 2) / 2);
		
		this.shadow = new Ellipse(Board.TILE_SIZE * 0.3125, Board.TILE_SIZE * 0.26);
		this.shadow.setFill(Color.BLACK);

		this.shadow.setStroke(Color.BLACK);
		this.shadow.setStrokeWidth(Board.TILE_SIZE * 0.03);

		this.shadow.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * 0.3125 * 2) / 2);
		this.shadow.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * 0.26 * 2) / 2 + Board.TILE_SIZE * 0.07);
	}
	
	public PieceIcon(PieceTeam type, Color shadowColor) {
		super(Board.TILE_SIZE * 0.3125, Board.TILE_SIZE * 0.26);
		this.type = type;
		
		
		this.setFill(type == PieceTeam.BLACK
		      ? Color.valueOf("#000000") : Color.valueOf("#fff9f4"));
		
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(Board.TILE_SIZE * 0.03);
		
		this.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * 0.3125 * 2) / 2);
		this.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * 0.26 * 2) / 2);
		
		this.shadow = new Ellipse(Board.TILE_SIZE * 0.3125, Board.TILE_SIZE * 0.26);
		this.shadow.setFill(shadowColor);

		this.shadow.setStroke(Color.BLACK);
		this.shadow.setStrokeWidth(Board.TILE_SIZE * 0.03);

		this.shadow.setTranslateX((Board.TILE_SIZE - Board.TILE_SIZE * 0.3125 * 2) / 2);
		this.shadow.setTranslateY((Board.TILE_SIZE - Board.TILE_SIZE * 0.26 * 2) / 2 + Board.TILE_SIZE * 0.07);
	}
}