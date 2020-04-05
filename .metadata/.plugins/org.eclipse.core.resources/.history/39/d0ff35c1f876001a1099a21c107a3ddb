package chessPieces;

import chessGame.Board;
import chessPieceComponents.PieceMoveResult;
import enumTypes.MoveType;
import enumTypes.PieceTeam;
import javafx.scene.paint.Color;

public class King extends Piece{

	private boolean isFirstMove;
	
	public King(PieceTeam type, int x, int y) {
		super(type, x, y, Color.DARKBLUE);
		isFirstMove = true;
	}

	@Override
	public PieceMoveResult move(int newX, int newY, Board board) {
		final int oldX = (int)(this.getOldX() + 100 / 2) / 100,
				  oldY = (int)(this.getOldY() + 100 / 2) / 100;      
        
        final boolean isVerticle = newX == oldX && newY != oldY && Math.abs(newY - oldY) == 1,
					  isHorizontal = newX != oldX && newY == oldY && Math.abs(newX - oldX) == 1,
					  isDiagonal = Math.abs(newX - oldX) == Math.abs(newY - oldY) && Math.abs(newY - oldY) == 1,
					  isLegalMovement = (isVerticle || isHorizontal || isDiagonal),
					  isPieceThere = board.getTile(newX, newY).hasPiece(),
					  isAllyPieceThere = isPieceThere ? board.getTile(newX, newY).getPiece().getType() == this.getType() : false;

        if (isLegalMovement && !isAllyPieceThere) {
        	
            final boolean isEnemyPieceThere = isPieceThere 
            		? board.getTile(newX, newY).getPiece().getType() != this.getType() 
            		: false;
            
    		isFirstMove = false;		
            		
        	return isEnemyPieceThere 
        			? new PieceMoveResult(MoveType.KILL, board.getTile(newX, newY).getPiece())
        			: new PieceMoveResult(MoveType.NORMAL);
        } else if(isFirstMove && newX != oldX && newY == oldY && Math.abs(newX - oldX) == 2) {
        	//
        	Piece possibleQueensRook = board.getTile(0, newY).getPiece();
        	if(newX == 2 && possibleQueensRook instanceof Rook && ((Rook) possibleQueensRook).isFirstMove()
        			&& possibleQueensRook.getType() == this.getType()) {

        		board.getTile(0, newY).getPiece().moveTo(3, newY);
                board.getTile(0, newY).setPiece(null);
            	isFirstMove = false;
                return new PieceMoveResult(MoveType.NORMAL);
        	}
        	Piece possibleKingsRook = board.getTile(7, newY).getPiece();
        	if(newX == 6 && possibleKingsRook instanceof Rook && ((Rook) possibleKingsRook).isFirstMove()
        			&& possibleKingsRook.getType() == this.getType()) {

        		board.getTile(7, newY).getPiece().moveTo(5, newY);
                board.getTile(7, newY).setPiece(null);
                isFirstMove = false;
                return new PieceMoveResult(MoveType.NORMAL);
        	}
        	
        }
        return new PieceMoveResult(MoveType.NONE);
	}

}
