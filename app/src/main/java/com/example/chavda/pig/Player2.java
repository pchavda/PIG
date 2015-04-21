package com.example.chavda.pig;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.FrameLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.w3c.dom.Text;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int score,p2score,p1score,intRoundTotal,p1old,p2old;
    private TextView p1,p2,textroundtotal;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);
        p1 = (TextView)findViewById(R.id.p1);
        p2 = (TextView)findViewById(R.id.p2);
        textroundtotal = (TextView)findViewById(R.id.round);

        Intent intent = getIntent();
     p1old=   p1score= intent.getIntExtra("P1",0);
       p2old= p2score = intent.getIntExtra("P2",0);
        p1.setText("P1: "+ String.valueOf(p1score));
        p2.setText("P2: "+ String.valueOf(p2score));

        //Toast.makeText(this, "The score is: " + score, Toast.LENGTH_LONG).show();

        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  rollDice();
                if(p2score<100)
                {
                    rollDice();
                }
                else
                {
                    //intRoundTotal = intRoundTotal+p1round;
                    //p2.setText("P2: "+ String.valueOf(intRoundTotal));
                    //return;
                }

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p2score >= 100) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                    alertDialog.setTitle("Player 2 Won!");
                    alertDialog.setMessage("Play again!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return;
                }

                Intent intent = new Intent(Player2.this,MainActivity.class);
                intent.putExtra("P2", p2score);
                intent.putExtra("P1", p1score);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                startActivity(intent);


            }
        });



        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }
int roundt=0;
    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());

        if(val1 == 1 || val2 == 1)
        {
            Intent intent = new Intent(Player2.this,MainActivity.class);
            intent.putExtra("P2", p2old);
            intent.putExtra("P1", p1score);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            startActivity(intent);
        } else{
            if(p2score <= 100) {
                p2score += val1 + val2;
            }}

        roundt = val1 + val2 + roundt;
        textroundtotal.setText("Round :"+ String.valueOf(roundt));
        setDie(val1, die1);
        setDie(val2, die2);

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/