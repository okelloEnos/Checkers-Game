package com.okelloSoftwarez.Game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class checkersPiece extends StackPane {
    private checkersPieceType type;
    private double mouseX, mouseY;
    private double oldX, oldY;
    Ellipse background, ellipse, kingPiece;
    checkersPiece checkersPieced;
    private boolean isKing;

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public checkersPieceType getType() {
        return type;
    }

    public void setType(checkersPieceType type) {
        this.type = type;
    }

    public checkersPiece(checkersPieceType type, int x, int y){
        this.type = type;

        move(x , y );

        background = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
        background.setFill(Color.BLACK);
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);

        background.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
        background.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);


        ellipse = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
        ellipse.setFill(type == checkersPieceType.RED ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);

        ellipse.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
        ellipse.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 );

//        Ellipse king = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
//        king.setFill(Color.DARKGREEN);
//        king.setStroke(Color.DARKGREEN);
//        king.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);
//
//        king.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
//        king.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);


//        if (type == checkersPieceType.RED && y == 7 || type == checkersPieceType.WHITE && y == 0){
//            getChildren().addAll(background, ellipse, king);
//        }
//        else {
        getChildren().addAll(background, ellipse);
//        }
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });

    }
    public checkersPiece(checkersPieceType type, int x, int y, boolean king){
        this.type = type;

        move(x , y );

        background = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
        background.setFill(Color.BLACK);
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);

        background.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
        background.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);


        ellipse = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
        ellipse.setFill(type == checkersPieceType.RED ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);

        ellipse.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
        ellipse.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 );

//        Ellipse king = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
//        king.setFill(Color.DARKGREEN);
//        king.setStroke(Color.DARKGREEN);
//        king.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);
//
//        king.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
//        king.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);


//        if (type == checkersPieceType.RED && y == 7 || type == checkersPieceType.WHITE && y == 0){
//            getChildren().addAll(background, ellipse, king);
//        }
//        else {
            getChildren().addAll(background, ellipse);
//        }
        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();

        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });

    }

    public void move(int x, int y){
        oldX = x * checkersGame.TILE_SIZE;
        oldY = y * checkersGame.TILE_SIZE;

        relocate(oldX, oldY);
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public void abortMove(){
        relocate(oldX, oldY);
    }

    public void makeKing(checkersPiece piece){
//        isKing = true;
        if (piece.getType().equals(checkersPieceType.RED)){
            piece.setType(checkersPieceType.KING_RED);
        }
        else if (piece.getType().equals(checkersPieceType.WHITE)){
            piece.setType(checkersPieceType.KING_WHITE);
        }
    }
//    public  void king(){
//        kingPiece = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
//        kingPiece.setFill(Color.BLACK);
//        kingPiece.setStroke(Color.BLACK);
//        kingPiece.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);
//
//        kingPiece.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
//        kingPiece.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);
//
//return true;
//         getChildren().addAll(background, ellipse, kingPiece);
//        return kingPiece;
//    }
//    public void setBackgroundKing(checkersPiece piece) {
//
//        Ellipse king = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
//        king.setFill(Color.DARKGREEN);
//        king.setStroke(Color.DARKGREEN);
//        king.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);
//
//        king.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
//        king.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);
//
//        getChildren().add(king);
//    }
}
