package com.okelloSoftwarez.Game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class checkersGame extends Application {

    public static final int TILE_SIZE = 90 ;
    public static final int WIDTH = 8 ;
    public static final int HEIGHT = 8 ;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private checkersTile[][] board = new checkersTile [WIDTH][HEIGHT];


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContext());
        stage.setTitle("Checkers Game");
        stage.setScene(scene);
        stage.show();
    }

    private Parent createContext(){
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0 ; y < HEIGHT ; y++){
            for (int x = 0 ; x < WIDTH ; x++){
                checkersTile tile = new checkersTile((y + x) % 2 == 0, x, y );

                board [x][y] = tile;

                tileGroup.getChildren().add(tile);

                checkersPiece piece = null;
                if ( y <= 2 && (x + y) % 2 != 0){
                    piece = makePiece(checkersPieceType.RED, x, y);
                }

                if (y >= 5 && (x +y) % 2 != 0){
                    piece = makePiece(checkersPieceType.WHITE, x, y);
                }

                if (piece != null){
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                  }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private checkersPiece makePiece(checkersPieceType type, int x, int y){
        checkersPiece piece = new checkersPiece(type, x, y);

        piece.setOnMouseReleased(e -> {

            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            checkersMoveResult result = tryMove(piece, newX, newY);

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            switch (result.getType()){
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    checkersPiece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);

                    break;

                case NONE:
                    piece.abortMove();
                    break;

                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    break;
            }
        });
        return piece;
    }

    private checkersMoveResult tryMove(checkersPiece checkersPiece, int newX, int newY){
        if (board[newX][newY].hasPiece() || newX + newY % 2 == 0){
            return new checkersMoveResult(checkersMoveType.NONE);
        }
        int x0 = toBoard(checkersPiece.getOldX());
        int y0 = toBoard(checkersPiece.getOldY());

        if (Math.abs(newX - x0) == 1 && (newY - y0) == checkersPiece.getType().moveDir){
            return new checkersMoveResult(checkersMoveType.NORMAL);
        }
        else if (Math.abs(newX - x0) == 2 && (newY - y0) == checkersPiece.getType().moveDir * 2){
            int x1 = x0 + (newX - x0) / 2 ;
            int y1 = y0 + (newY - y0) / 2 ;

            if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != checkersPiece.getType()){
                return new checkersMoveResult(checkersMoveType.KILL, board[x1][y1].getPiece());
            }
        }
        return new checkersMoveResult(checkersMoveType.NONE);
    }

    private int toBoard(double pixel){
        return  (int) ( pixel + TILE_SIZE / 2 ) / TILE_SIZE;
    }
}
