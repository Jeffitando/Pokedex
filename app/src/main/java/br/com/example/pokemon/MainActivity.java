package br.com.example.pokemon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView myPokemonName;
    private Button myButtonParse;
    private ImageView mySprite;
    private EditText mySearch;
    private RequestQueue myQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPokemonName = findViewById(R.id.textView);
        myButtonParse = findViewById(R.id.button);
        mySprite = findViewById(R.id.imageView);
        mySearch = findViewById(R.id.editTextTextPersonName);

        myQueue = Volley.newRequestQueue(this);


    }

    public void searchButton(View v){
        getRequest();
    }


    private void getRequest(){
        String temp_url = mySearch.getText().toString();
        String url = "https://pokeapi.co/api/v2/pokemon/"+temp_url;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {

                        String myPokemon = response.getString("name");
                        JSONObject myPokeSprite = response.getJSONObject("sprites");
                        String sprite_url = myPokeSprite.getString("front_default");

                        myPokemonName.setText(myPokemon);
                        Picasso.with(MainActivity.this).load(sprite_url).into(mySprite);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());

        myQueue.add(request);

    }


}