package com.wadhavekar.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MoreInformation extends AppCompatActivity {
    TextView pokemon,baseexp,ab,stats,type;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);
        pokemon = findViewById(R.id.tv_putnameOfPokemon);
        baseexp = findViewById(R.id.tv_baseExperience);
        iv = findViewById(R.id.iv_moreInfo);
        ab = findViewById(R.id.tv_ability);
        stats = findViewById(R.id.tv_stats);
        type = findViewById(R.id.tv_types);

        type.setText(getIntent().getStringExtra("Types"));

        stats.setText(getIntent().getStringExtra("Stats"));

        getIncomingIntent();


    }
    private void getIncomingIntent(){
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("base_experience") && getIntent().hasExtra("pokemon_name")){
            String imageUrl = getIntent().getStringExtra("image_url");
            String pokemonName = getIntent().getStringExtra("pokemon_name");
            String baseExperience = getIntent().getStringExtra("base_experience");
            String abilities = getIntent().getStringExtra("Abilities");

            setToWidgets(imageUrl,pokemonName,baseExperience,abilities);
        }
    }
    private void setToWidgets(String imageUrl, String pokemonName,String baseExperience,String abilities){
        pokemon.setText(pokemonName);
        baseexp.setText(baseExperience);
        ab.setText(abilities);
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this));
        builder.build().load(imageUrl).into(iv);
    }
}
