package com.codecool.enterprise.overcomplicated.model;

import java.util.ArrayList;
import java.util.List;

public class TictactoeGame {

    private String lastMovement = "";
    public boolean isGameFinished = false;
    public boolean isGameTied = false;

    private String oSign = "fa fa-circle-o";
    private String xSign = "fa fa-times";

    List<String> fields;

    public TictactoeGame() {
        resetGameMap();
    }


    public String getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(String lastMovement) {
        this.lastMovement = lastMovement;
    }

    public List<String> getFields() {
        return fields;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void resetGameMap(){
        fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add("");
        }
    }

    public void updateField(int move) {
        if(isGameFinished) {
            resetGameMap();
            isGameFinished = false;
            isGameTied = false;
        } else {
            if (fields.get(move).equals("")) {
                if (lastMovement.equals(oSign)) {
                    fields.set(move, xSign);
                } else {
                    fields.set(move, oSign);
                }
            }
        }
        changeLastMovement();
        if(checkGameOver() || checkForWin()) {
            isGameFinished = true;
            if(!checkForWin()) {
                isGameTied = true;
            }
        }
    }

    public void changeLastMovement() {
        if (lastMovement.equals(oSign)) {
            lastMovement = xSign;
        } else {
            lastMovement = oSign;
        }
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public boolean checkGameOver() {
        return !fields.contains("");
    }


    // Returns true if there is a win, false otherwise.
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Loop through rows and see if any are winners.
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(fields.get(i * 3), fields.get(1 + i * 3), fields.get(2 + i * 3))) {
                return true;
            }
        }
        return false;
    }


    // Loop through columns and see if any are winners.
    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(fields.get(i), fields.get(3 + i), fields.get(6 + i))) {
                return true;
            }
        }
        return false;
    }

        // Check the two diagonals to see if either is a win. Return true if either wins.
    private boolean checkDiagonalsForWin() {
        return (checkRowCol(fields.get(0), fields.get(4), fields.get(8)) || (checkRowCol(fields.get(2), fields.get(4), fields.get(6))));
    }

    // Check to see if all three values are the same (and not empty) indicating a win.
    private boolean checkRowCol(String field1, String field2, String field3) {
        return ((!field1.equals("")) && (field1.equals(field2)) && (field2.equals(field3)));
    }

    public String generateTableToAiMove(){
        StringBuilder resultTable = new StringBuilder();
        for (String field: fields) {
            if (field.equals("")) {
                resultTable.append("-");
            } else if (field.equals(oSign)) {
                resultTable.append("O");
            } else {
                resultTable.append("X");
            }
        }
        return String.valueOf(resultTable);
    }

}
