package com.okelloSoftwarez.Game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class checkersGame extends Application {

    public static final int TILE_SIZE = 90 ;
    public static final int WIDTH = 8 ;
    public static final int HEIGHT = 8 ;
    private  int tracker ;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();
    private checkersTile[][] board = new checkersTile [WIDTH][HEIGHT];
    private checkersMoveResult result;

//    private boolean isKing ;

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
//                    for (y = 1; y < pieceGroup.getChildren().size(); y++){
//
//                        tracker = y;
//                    }
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
//        isKing = false;
        checkersPiece piece = new checkersPiece(type, x, y);
//        isKing = false;

        piece.setOnMouseReleased(e -> {

            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            result = tryMove(piece, newX, newY);

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

                case KING:
//                    piece.move(newX, newY);
//                    board[x0][y0].setPiece(null);
//                    board[newX][newY].setPiece(piece);
//                    piece.setBackgroundKing(piece);
//                    piece.resize(newX, newY);
                    manageKings(piece, x0, y0, newX, newY, piece.getType());
                    break;
            }
        });
        return piece;
    }

    private checkersMoveResult manageKing(checkersPiece piece, int x0, int y0, int newX, int newY, checkersPieceType type) {

        System.out.println(piece.getType() + ": t/f :" + piece.isKing());

        System.out.println("x0 : " + x0 + "newX : " + newX + "y0 : " + y0 + "newY : " + newY);
        if (Math.abs(newX - x0) == 1 && Math.abs(newY - y0) == 1) {
//            piece.move(newX, newY);
//            board[x0][y0].setPiece(null);
//            board[newX][newY].setPiece(piece);
            return new checkersMoveResult(checkersMoveType.NORMAL);
        }

        else if (Math.abs(newX - x0) == 2){
//             else if (Math.abs(newX - x0) == 2 && (newY - y0) == checkersPiece.getType().moveDir * 2) {
                int kx1 = x0 + (newX - x0) / 2;
                int ky1 = y0 + (newY - y0) / 2;

                if (board[kx1][ky1].hasPiece() && board[kx1][ky1].getPiece().getType() != type ) {
                    return new checkersMoveResult(checkersMoveType.KILL, board[kx1][ky1].getPiece());
                }
        }

        return new checkersMoveResult(checkersMoveType.NONE);

    }
    private void manageKings(checkersPiece piece, int x0, int y0, int newX, int newY, checkersPieceType type) {

        System.out.println(piece.getType() + ": t/f :" + piece.isKing());

        System.out.println("x0 : " + x0 + " newX : " + newX + " y0 : " + y0 + " newY : " + newY);
        int absX = Math.abs(newX - x0);
        int absY = Math.abs(newY - y0);
        if (absX == 1 && absY == 1) {
            if (board[newX][newY].hasPiece()) {

                piece.abortMove();
            } else {
                piece.move(newX, newY);
                board[x0][y0].setPiece(null);
                board[newX][newY].setPiece(piece);
//            return new checkersMoveResult(checkersMoveType.NORMAL);
            }
        }
        else if (absX == 2){
//             else if (Math.abs(newX - x0) == 2 && (newY - y0) == checkersPiece.getType().moveDir * 2) {
//            int kx1 = x0 + ( absX / 2 ); kx1 = 2
//            int ky1 = y0 + (absY / 2 ); ky1 = 5


            int kx2 = (newX - x0) / 2 ;

            int ky2 = (newY - y0) / 2 ;

            int kx1 = x0 + kx2 ;
//            int ky1 = y0 + ky2 ;
            int ky1 = y0 + ky2 ;

            if (board[kx1][ky1].hasPiece() ) {
//                checkersPiece otherPiece = board[kx1][ky1].getPiece();
                if (board[kx1][ky1].getPiece().getType() != type) {
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    checkersPiece otherPiece = board[kx1][ky1].getPiece();
                    board[kx1][ky1].setPiece(null);
//                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
//                return new checkersMoveResult(checkersMoveType.KILL, board[kx1][ky1].getPiece());
                }
                else {
                    piece.abortMove();
                }
            }
        }
        else {
            piece.abortMove();
        }

//        return new checkersMoveResult(checkersMoveType.NONE);

    }


    public void setBackgroundKing(checkersPiece piece) {

        Ellipse king = new Ellipse(checkersGame.TILE_SIZE * 0.3125, checkersGame.TILE_SIZE * 0.26);
        king.setFill(Color.DARKGREEN);
        king.setStroke(Color.DARKGREEN);
        king.setStrokeWidth(checkersGame.TILE_SIZE * 0.03);

        king.setTranslateX((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.3125 * 2) / 2 );
        king.setTranslateY((checkersGame.TILE_SIZE - checkersGame.TILE_SIZE * 0.26 * 2) / 2 + checkersGame.TILE_SIZE * 0.07);

//        getChildren().add(king);
    }
    private checkersMoveResult tryMove(checkersPiece checkersPiece, int newX, int newY) {

        if (!checkersPiece.isKing()) {
            System.out.println("Piece : " + tracker);
//        isKing = false;
            if (board[newX][newY].hasPiece() || newX + newY % 2 == 0) {
                return new checkersMoveResult(checkersMoveType.NONE);
            }
            int x0 = toBoard(checkersPiece.getOldX());
            int y0 = toBoard(checkersPiece.getOldY());

//        if (Math.abs(newX - x0) == 1 && (newY - y0) == checkersPiece.getType().moveDir){
//            return new checkersMoveResult(checkersMoveType.NORMAL);
//        }
//        if (!isKing) {
            if (Math.abs(newX - x0) == 1 && Math.abs(newY - y0) == 1) {
                if ((newY - y0) == checkersPiece.getType().moveDir) {
                    System.out.println("ox: " + x0 + " newX :" + newX + " oY: " + y0 + " new Y :" + newY);
                    return new checkersMoveResult(checkersMoveType.NORMAL);
                } else {
                    if (checkersPiece.getType() == checkersPieceType.RED && y0 == 7 || checkersPiece.getType() == checkersPieceType.WHITE && y0 == 0) {

//                    shiftToKing(checkersPiece, newX, newY);
//                            return new checkersMoveResult(checkersMoveType.KING);
//                        isKing = true;
//                    System.out.println("King of B: " + checkersPiece.getType());
                        checkersPiece.setKing(true);
//                        checkersPiece.makeKing(checkersPiece);

//                    System.out.println("King of : " + checkersPiece.getType() + "...Y..." + (newY - y0) + "...X..." + (newX - x0));
//                    if (checkersPiece.getType().equals(checkersPieceType.KING_RED) && Math.abs(newX - x0) == 1 ){
//
//                        System.out.println("ox: " + x0 +" newX :"+ newX + " oY: "+ y0 + " new Y :" + newY);
//
//                        return new checkersMoveResult(checkersMoveType.KING);
//                    }
//                    else if(checkersPiece.getType().equals(checkersPieceType.KING_WHITE) && Math.abs(newX - x0) == 1){
//
//                        System.out.println("ox: " + x0 +" newX :"+ newX + " oY: "+ y0 + " new Y :" + newY);
//                        return new checkersMoveResult(checkersMoveType.KING);
//                    }

//                    System.out.println("ox: " + x0 +" newX :"+ newX + " oY: "+ y0 + " new Y :" + newY);
                        return new checkersMoveResult(checkersMoveType.KING);
                    }
                }
//            System.out.println("ox: " + x0 +" newX :"+ newX + " oY: "+ y0 + " new Y :" + newY);
            } else if (Math.abs(newX - x0) == 2 && (newY - y0) == checkersPiece.getType().moveDir * 2) {
                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != checkersPiece.getType()) {
                    return new checkersMoveResult(checkersMoveType.KILL, board[x1][y1].getPiece());
                }
            }
//        }

//        if (checkersPiece.getType() == checkersPieceType.RED && y0 == 8){
//
//        }
//        else if (checkersPiece.getType() == checkersPieceType.WHITE && y0 == 0){
//
//        }

//        if (isKing){
//            if (Math.abs(newX - x0) == 1 && Math.abs(newY - y0) == 1){
//                return new checkersMoveResult(checkersMoveType.NORMAL);
//            }
//        }
//            return new checkersMoveResult(checkersMoveType.NONE);
        }
        else if (checkersPiece.isKing()){
            return new checkersMoveResult(checkersMoveType.KING);
        }
        return new checkersMoveResult(checkersMoveType.NONE);
    }

    private void shiftToKing(checkersPiece checkersPiece, int newX, int newY) {
        checkersPiece.resize(newX, newY);
    }

    private int toBoard(double pixel){
        return  (int) ( pixel + TILE_SIZE / 2 ) / TILE_SIZE;
    }
}
