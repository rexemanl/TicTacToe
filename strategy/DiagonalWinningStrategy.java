package strategy;

import model.Board;
import model.Move;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{

    HashMap<Integer, HashMap<Character, Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Character sym = move.getCell().getSymbol().getSym();

        if(row == col){
            if(!counts.containsKey(0)){
                counts.put(0, new HashMap<>());
            }
            HashMap<Character,Integer> countDiag0 = counts.get(0);

            if(!countDiag0.containsKey(sym)){
                countDiag0.put(sym, 0);
            }

            countDiag0.put(sym, countDiag0.get(sym) + 1);

            if(countDiag0.get(sym) == board.getSize()){
                return true;
            }
        }

         if(row + col == board.getSize() - 1){

            if(!counts.containsKey(1)){
                counts.put(1, new HashMap<>());
            }

            HashMap<Character,Integer> countDiag1 = counts.get(1);

            if(!countDiag1.containsKey(sym)){
                countDiag1.put(sym, 0);
            }

            countDiag1.put(sym, countDiag1.get(sym) + 1);

            if(countDiag1.get(sym) == board.getSize()){
                return true;
            }
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Character sym = move.getCell().getSymbol().getSym();

        if(row == col){
            counts.get(0).put(sym, counts.get(0).get(sym) - 1);
        }
        else if(row + col == board.getSize() - 1){
            counts.get(1).put(sym, counts.get(1).get(sym) - 1);
        }

    }
}
