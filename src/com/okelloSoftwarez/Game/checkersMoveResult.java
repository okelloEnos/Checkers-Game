package com.okelloSoftwarez.Game;

public class checkersMoveResult {
    private checkersMoveType type;
    private checkersPiece piece;

    public checkersPiece getPiece() {
        return piece;
    }

    public checkersMoveType getType() {
        return type;
    }

    public checkersMoveResult(checkersMoveType type){
        this(type, null);
    }
    public checkersMoveResult(checkersMoveType type, checkersPiece piece) {
        this.type = type;
        this.piece = piece;
    }
}
