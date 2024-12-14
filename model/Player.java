package model;

import java.util.Scanner;

public abstract class Player {
    private long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner sc = new Scanner(System.in);

    public Player(String name, long id, Symbol sym, PlayerType playerType) {
        this.name = name;
        this.id = id;
        this.symbol = sym;
        this.playerType = playerType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        System.out.println("Enter Row for the move");
        int r = sc.nextInt();
        System.out.println("Enter Column for the move");
        int c = sc.nextInt();

        return new Move(new Cell(r,c),this);
    }
}
