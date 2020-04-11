package com.okelloSoftwarez.Game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class checkersPiece extends StackPane {

    private checkersPieceType type;
    private double mouseX, mouseY;
    private double oldX, oldY;

    public checkersPieceType getType() {
        return type;
    }

    public checkersPiece(checkersPieceType type, int x, int y){
        this.type = type;

        move(x , y );

        Ellipse background = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
        background.setFill(Color.BLACK);
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);

        background.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
        background.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);


        Ellipse ellipse = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
        ellipse.setFill(type == checkersPieceType.RED ? Color.valueOf("#c40003") : Color.valueOf("#fff9f4"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);

        ellipse.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
        ellipse.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 );

        getChildren().addAll(background, ellipse);

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
}
