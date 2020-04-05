package chessGame;

import chessPiece.PieceMoveResult;
import chessPiece.ChessPieceFactory;
import chessPiece.Piece;
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
    
    private ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    
    private void setOnMouseReleased(final Piece piece) {
        piece.setOnMouseReleased(e -> {
            final int newX = chessBoard.toBoard(piece.getLayoutX()),
                      newY = chessBoard.toBoard(piece.getLayoutY());

            final boolean isMoveOutsideBoard = newX < 0 || newY < 0 || newX >= Board.WIDTH || newY >= Board.HEIGHT;
            PieceMoveResult result = isMoveOutsideBoard 
            		? new PieceMoveResult(MoveType.NONE)
    				: chessBoard.tryMove(piece, newX, newY);


            final int x0 = chessBoard.toBoard(piece.getOldX()),
            		  y0 = chessBoard.toBoard(piece.getOldY());

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.moveTo(newX, newY);
                    chessBoard.getTile(x0, y0).setPiece(null);
                    chessBoard.getTile(newX, newY).setPiece(piece);
                    break;
                case KILL:
                    piece.moveTo(newX, newY);
                    chessBoard.getTile(x0, y0).setPiece(null);
                    chessBoard.getTile(newX, newY).setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    chessBoard.getTile(chessBoard.toBoard(otherPiece.getOldX()), chessBoard.toBoard(otherPiece.getOldY())).setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
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
    
    private Piece generatePiece(final int x, final int y) {
        Piece piece = null;

        if(y == 0) {
        	if(x == 0) {
        		piece = chessPieceFactory.makePiece(PieceType.ROOK, PieceTeam.BLACK, x, y);
        	}
        }
        else if (y == 1) {
            piece = chessPieceFactory.makePiece(PieceType.PAWN, PieceTeam.BLACK, x, y);
        }
        else if (y == 6) {
        	piece = chessPieceFactory.makePiece(PieceType.PAWN, PieceTeam.WHITE, x, y);
        }
        else if (y == 7) {
        	if(x == 0 || x == 7) {
        		piece = chessPieceFactory.makePiece(PieceType.ROOK, PieceTeam.WHITE, x, y);
        	} else if(x == 1 || x == 6) {
        		piece = chessPieceFactory.makePiece(PieceType.BISHOP, PieceTeam.WHITE, x, y);
        	} else if(x == 2 || x == 5) {
        		piece = chessPieceFactory.makePiece(PieceType.KNIGHT, PieceTeam.WHITE, x, y);
        	}
 
        	else if(x == 3) {
        		piece = chessPieceFactory.makePiece(PieceType.QUEEN, PieceTeam.WHITE, x, y);
        	}  else if(x == 4) {
        		piece = chessPieceFactory.makePiece(PieceType.KING, PieceTeam.WHITE, x, y);
        	}
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
