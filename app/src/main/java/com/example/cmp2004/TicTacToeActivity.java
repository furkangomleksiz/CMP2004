package com.example.cmp2004;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class TicTacToeActivity extends AppCompatActivity {
    private Button[] buttons = new Button[9];
    private String currentPlayer = "X";
    private String[] gameBoard = new String[9];
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        for (int i = 0; i < 9; i++) {
            String buttonID = "button_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            gameBoard[i] = "";
        }

        status = findViewById(R.id.status);

        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!((Button) v).getText().toString().equals("")) {
                        return;
                    }
                    ((Button) v).setText(currentPlayer);
                    gameBoard[Integer.parseInt(v.getTag().toString())] = currentPlayer;
                    if (checkWin()) {
                        status.setText("Player " + currentPlayer + " wins!");
                        disableButtons();
                    } else if (checkDraw()) {
                        status.setText("It's a Draw!");
                        disableButtons();
                    } else {
                        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                        if (currentPlayer.equals("O")) {
                            computerMove();
                        }
                    }
                }
            });
        }
    }

    private boolean checkWin() {
        return (checkRow(0, 1, 2) || checkRow(3, 4, 5) || checkRow(6, 7, 8) ||
                checkRow(0, 3, 6) || checkRow(1, 4, 7) || checkRow(2, 5, 8) ||
                checkRow(0, 4, 8) || checkRow(2, 4, 6));
    }

    private boolean checkRow(int a, int b, int c) {
        return (gameBoard[a].equals(gameBoard[b]) && gameBoard[b].equals(gameBoard[c]) && !gameBoard[a].equals(""));
    }

    private boolean checkDraw() {
        for (String cell : gameBoard) {
            if (cell.equals("")) {
                return false;
            }
        }
        return true;
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }

    private void computerMove() {
        int winningMove = getWinningMove("O");
        int blockingMove = getWinningMove("X");

        int index;
        if (winningMove != -1) {
            index = winningMove;
        } else if (blockingMove != -1) {
            index = blockingMove;
        } else {
            Random random = new Random();
            do {
                index = random.nextInt(9);
            } while (!gameBoard[index].equals(""));
        }

        buttons[index].setText("O");
        gameBoard[index] = "O";

        if (checkWin()) {
            status.setText("Computer wins!");
            disableButtons();
        } else if (checkDraw()) {
            status.setText("It's a draw!");
            disableButtons();
        } else {
            currentPlayer = "X";
        }
    }

    private int getWinningMove(String player) {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i].equals("")) {
                gameBoard[i] = player;
                if (checkWin()) {
                    gameBoard[i] = "";  // Reset the cell
                    return i;
                }
                gameBoard[i] = "";  // Reset the cell
            }
        }
        return -1;
    }
}