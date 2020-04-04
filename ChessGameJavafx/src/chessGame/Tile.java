package chessGame;

import chessPiece.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tile extends Rectangle {

    private Piece piece;

    public Tile(boolean light, int x, int y) {
        setWidth(Board.TILE_SIZE);
        setHeight(Board.TILE_SIZE);

        relocate(x * Board.TILE_SIZE, y * Board.TILE_SIZE);

        setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));
    }
    
    public boolean hasPiece() {
    	return piece != null;
	}

}