package enumTypes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PieceTeam {
    BLACK(1), 
    WHITE(-1);
	
    private final int moveDir;
}