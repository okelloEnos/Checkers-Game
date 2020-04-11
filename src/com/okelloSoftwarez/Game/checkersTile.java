package com.okelloSoftwarez.Game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class checkersTile extends Rectangle {

    checkersPiece piece;

    public checkersPiece getPiece() {
        return piece;
    }

    public void setPiece(checkersPiece piece) {
        this.piece = piece;
    }

    public boolean hasPiece(){
        return piece != null ;
    }

    public checkersTile(boolean light, int x, int y){
        setWidth(checkersGame.TILE_SIZE);
        setHeight(checkersGame.TILE_SIZE);

        relocate(x * checkersGame.TILE_SIZE, y * checkersGame.TILE_SIZE);
        setFill(light ? Color.valueOf("#feb") : Color.valueOf("#582"));
    }
}
