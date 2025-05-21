package edu.niu.cs.z1904531.portfolio8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[][] gameButtons;

    private TicTacToe game;

    private TextView gameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //create the tic tac toe game
        game = new TicTacToe();

        buildGui();
    }//end onCreate

    private void buildGui() {

        //find width of the screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        //calculate the width
        int buttonWidth = size.x / TicTacToe.SIDE;

        //set up the grid layout
        GridLayout gridLayout = new GridLayout(this);

        //set up the number of rows and columns in the layout
        gridLayout.setRowCount(TicTacToe.SIDE + 1);
        gridLayout.setColumnCount(TicTacToe.SIDE);

        //create the two dimensioal array of buttons
        gameButtons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];

        //create the button handler
        ButtonHandler buttonHandler = new ButtonHandler();

        //create the buttons for the game and put them on the grid
        for(int row = 0; row < TicTacToe.SIDE; row++){

            for (int col = 0; col < TicTacToe.SIDE; col++){

                //create a single button
                gameButtons[row][col] = new Button(this);

                //add the button handler to the button
                gameButtons[row][col].setOnClickListener(buttonHandler);

                //set the text size for whatever is displayed on the button
                gameButtons[row][col].setTextSize(buttonWidth * 0.2f);

                //add the button to the layout
                gridLayout.addView(gameButtons[row][col], buttonWidth, buttonWidth);

            }//inner for

        }//outer for

        //create the textview for the game status
        gameStatus = new TextView(this);

        //set up how the textview will be displayed
        GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE, 1),
                        colSpec = GridLayout.spec(0, TicTacToe.SIDE);

        //set up the layout parameters for the textview
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, colSpec);

        //attach the layout parameters to the textview
        gameStatus.setLayoutParams(layoutParams);

        //format the textview
        gameStatus.setWidth(TicTacToe.SIDE * buttonWidth);
        gameStatus.setHeight(buttonWidth);

        //set color and location
        gameStatus.setGravity(Gravity.CENTER);
        gameStatus.setBackgroundColor(Color.GREEN);
        gameStatus.setTextSize(buttonWidth * 0.15f);

        //add the text to the textview
        gameStatus.setText(game.results());

        //add the textview to the grid
        gridLayout.addView(gameStatus);


        //display the grid layout
        setContentView(gridLayout);

        //display the grid layout
        setContentView(gridLayout);

    }//end buildGui



    //method that handles updating a single button
    public void updateButton(int row, int col){

        //display a character on the clicked button

        //determine the current player
        int currentPlayer = game.play(row, col);

        if (currentPlayer == 1){
            gameButtons[row][col].setText("X");

        }
        else if (currentPlayer == 2){
            gameButtons[row][col].setText("O");

        }

        //is the game over?
        if(game.isGameOver()){
            //disable all of the buttons
            enableButtons(false);

            //change the background color of textview
            gameStatus.setBackgroundColor(Color.MAGENTA);

            //display the updated text
            gameStatus.setText(game.results());

            //display the dialog box
            showGameDialog();
        }

    }//end updateButton



    //mehtod that enables/disables the buttons
    public void enableButtons(boolean enabled){

        for(int row = 0; row < TicTacToe.SIDE; row++){

            for (int col = 0; col < TicTacToe.SIDE; col++){

                gameButtons[row][col].setEnabled(enabled);

            }//inner for

        }//outer for

    }//end enableButtons


    //method to reset the buttons
    private void resetButton(){
        for(int row = 0; row < TicTacToe.SIDE; row++){

            for (int col = 0; col < TicTacToe.SIDE; col++){

                gameButtons[row][col].setText("");

            }//inner for

        }//outer for

    }//end resetButton


    //method to display a dialog box to continue game
    public void showGameDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(R.string.dialog_title);
        alert.setMessage(R.string.dialog_message);

        //set up options for response
        //yes user wants to continue game
        alert.setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //reset the game
                game.resetGame();

                enableButtons(true);
                resetButton();

                //reset the textview
                gameStatus.setBackgroundColor(Color.GREEN);
                gameStatus.setText(game.results());

            }//end onClick

        });//end OnClickListener

        //no option
        alert.setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //close the application
                finish();

            }//end onClick

        });//end OnClickListener

        //display the dialog box
        alert.show();

    }//end showGameDialog




    //inner class to handle the button clicks
    private class ButtonHandler implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            //find the button that was clicked
            for(int row = 0; row < TicTacToe.SIDE; row++){

                for (int col = 0; col < TicTacToe.SIDE; col++){

                    if(view == gameButtons[row][col]){

                        updateButton(row,col);
                    }

                }//inner for

            }//outer for

        }//end onClick

    }//end ButtonHandler class

}//end MainActivity