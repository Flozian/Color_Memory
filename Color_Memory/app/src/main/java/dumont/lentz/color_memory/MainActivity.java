package dumont.lentz.color_memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonFacile, buttonMoyen, buttonDifficile, buttonChrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonFacile = (Button) findViewById(R.id.buttonFacile);
        buttonMoyen = (Button) findViewById(R.id.buttonMoyen);
        buttonDifficile = (Button) findViewById(R.id.buttonDifficile);
        buttonChrono = (Button) findViewById(R.id.buttonChrono);

        buttonFacile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }



}
