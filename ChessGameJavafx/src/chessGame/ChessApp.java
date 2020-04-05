package chessGame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.stream.StreamSource;

import chessPieceComponents.PieceMoveResult;
import chessPieces.ChessPieceFactory;
import chessPieces.Piece;
import enumTypes.MoveType;
import enumTypes.PieceTeam;
import enumTypes.PieceType;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessApp extends Application {

    private Board chessBoard = new Board();
    
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private boolean isWhiteTurn = true;
    
    private ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    
    private void setOnMouseReleased(final Piece piece) {
        piece.setOnMouseReleased(e -> {
        	
            if(piece.getType() == PieceTeam.BLACK && isWhiteTurn || piece.getType() == PieceTeam.WHITE && !isWhiteTurn) {
            	piece.abortMove();
            	return;
            }
        	
            final int newX = chessBoard.toBoard(piece.getLayoutX()),
                      newY = chessBoard.toBoard(piece.getLayoutY());

            final boolean isMoveOutsideBoard = newX < 0 || newY < 0 || newX >= Board.WIDTH || newY >= Board.HEIGHT;
            PieceMoveResult result = isMoveOutsideBoard 
            		? new PieceMoveResult(MoveType.NONE)
    				: chessBoard.tryMove(piece, newX, newY);


            final int oldX = chessBoard.toBoard(piece.getOldX()),
            		  oldY = chessBoard.toBoard(piece.getOldY());
            
            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    return;
                case NORMAL:
                    piece.moveTo(newX, newY);
                    chessBoard.getTile(oldX, oldY).setPiece(null);
                    chessBoard.getTile(newX, newY).setPiece(piece);
                    break;
                case KILL:
                    piece.moveTo(newX, newY);
                    chessBoard.getTile(oldX, oldY).setPiece(null);
                    chessBoard.getTile(newX, newY).setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    chessBoard.getTile(chessBoard.toBoard(otherPiece.getOldX()), chessBoard.toBoard(otherPiece.getOldY())).setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
            
            if(piece.getType() == PieceTeam.WHITE && newY == 0 || piece.getType() == PieceTeam.BLACK && newY == 7) {
            	Piece newPiece = chessPieceFactory.makePiece(PieceType.QUEEN, piece.getType(), newX, newY);
                chessBoard.getTile(newX, newY).setPiece(newPiece);
                pieceGroup.getChildren().add(newPiece);
                pieceGroup.getChildren().remove(piece);
                setOnMouseReleased(newPiece);
            }
            
            //TODO: Find a way to go through each each opposing piece to check for 'check', maybe observer pattern could help...
            //List<Piece> pieces = pieceGroup.getChildren().stream().map(x -> (Piece) x).collect(Collectors.toList());
            //pieces.forEach(a -> System.out.println(a.getType()));
            
            isWhiteTurn = !isWhiteTurn;
        });
    }
    
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Board.WIDTH * Board.TILE_SIZE, Board.HEIGHT * Board.TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < Board.HEIGHT; y++) {
            for (int x = 0; x < Board.WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                chessBoard.setTile(x, y, tile);

                tileGroup.getChildren().add(tile);

                Piece piece = generatePiece(x, y);

                if (piece != null) {
                	setOnMouseReleased(piece);
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        return root;
    }
    
    private Piece generatePiece(final int x, final int y) { //TODO check param somehow, shouldn't return null
        Piece piece = null;
        
        PieceTeam currentTeam = y == 0 || y == 1 
        		? PieceTeam.BLACK 
				: y == 6 || y == 7 ? PieceTeam.WHITE : null; 

        if(y == 0 || y == 7) {
        	if(x == 0 || x == 7) {
        		piece = chessPieceFactory.makePiece(PieceType.ROOK, currentTeam, x, y);
        	} else if(x == 1 || x == 6) {
        		piece = chessPieceFactory.makePiece(PieceType.KNIGHT, currentTeam, x, y);
        	} else if(x == 2 || x == 5) {
        		piece = chessPieceFactory.makePiece(PieceType.BISHOP, currentTeam, x, y);
        	} else if(x == 3) {
        		piece = chessPieceFactory.makePiece(PieceType.QUEEN, currentTeam, x, y);
        	} else if(x == 4) {
        		piece = chessPieceFactory.makePiece(PieceType.KING, currentTeam, x, y);
        	}
        	
        } else if (y == 1 || y == 6) {
            piece = chessPieceFactory.makePiece(PieceType.PAWN, currentTeam, x, y);
        }

        return piece;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Chess Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
