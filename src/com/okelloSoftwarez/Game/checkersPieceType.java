package com.okelloSoftwarez.Game;

public enum checkersPieceType {
    RED(1), WHITE(-1);

    final int moveDir;

    checkersPieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}
