package com.okelloSoftwarez;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Checker extends Application {
    public static final int TILE_SIDE_LENGTH= 90;
    public static final int NUMBER_OF_TILES = 8;
    private Group tileGroup = new Group();
    private Tiles [][] checkerBoard = new Tiles[NUMBER_OF_TILES][NUMBER_OF_TILES];

private Parent createContext(){
    Pane root = new Pane();

//    Setting the size of the checkers board width and height
    root.setPrefSize(TILE_SIDE_LENGTH * NUMBER_OF_TILES, TILE_SIDE_LENGTH * NUMBER_OF_TILES);

    root.getChildren().addAll(tileGroup);

    for (int y = 0; y < NUMBER_OF_TILES ; y++){
        for (int x = 0; x < NUMBER_OF_TILES; x++) {
            Tiles tile = new Tiles((x + y) % 2 == 0, x, y);
            checkerBoard[x][y] = tile ;
            tileGroup.getChildren().add(tile);
        }
    }

    return root;
}

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContext());
        stage.setTitle("ENOS CHECKERS");
        stage.setScene(scene);
        stage.show();
    }
}
