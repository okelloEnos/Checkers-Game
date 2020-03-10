package com.okelloSoftwarez;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tiles extends Rectangle {

    public Tiles(boolean light, int x, int y){
        setHeight(Checker.TILE_SIDE_LENGTH);
        setWidth(Checker.TILE_SIDE_LENGTH);

        //    Placing the Tiles in their respective location
        relocate(x * Checker.TILE_SIDE_LENGTH, y * Checker.TILE_SIDE_LENGTH);

//        Place colors after relocation
        setFill(light ? Color.valueOf("#FFFFFF") : Color.valueOf("#D2691E"));
    }
}
