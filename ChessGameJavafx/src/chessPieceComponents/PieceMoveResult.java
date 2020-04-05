package chessPieceComponents;

import chessPieces.Piece;
import enumTypes.MoveType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class PieceMoveResult {

    private MoveType type;
    private Piece piece;
    
    public PieceMoveResult(MoveType type) {
        this(type, null);
    }

}
