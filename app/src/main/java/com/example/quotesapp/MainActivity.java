package com.example.quotesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuote;
    private TextView tvAuthor;
    private List<String> quotes;
    private List<String> authors;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "QuotesAppPrefs";
    private static final String FAVORITES_KEY = "favorites";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuote = findViewById(R.id.tvQuote);
        tvAuthor = findViewById(R.id.tvAuthor);
        Button btnRefresh = findViewById(R.id.btnRefresh);
        Button btnShare = findViewById(R.id.btnShare);
        Button btnFavorites = findViewById(R.id.btnFavorites);
        Button btnViewFavorites = findViewById(R.id.btnViewFavorites);
        quotes = new ArrayList<>();
        authors = new ArrayList<>();
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        populateQuotes();
        displayRandomQuote();

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRandomQuote();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = tvQuote.getText().toString();
                String author = tvAuthor.getText().toString();
                String fullQuote = quote + " - " + author;
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, fullQuote);
                startActivity(Intent.createChooser(shareIntent, "Share quote via"));
            }
        });

        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = tvQuote.getText().toString();
                String author = tvAuthor.getText().toString();
                saveToFavorites(quote, author);
            }
        });

        btnViewFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateQuotes() {
        quotes.add("The only way to do great work is to love what you do.");
        authors.add("Steve Jobs");

        quotes.add("The purpose of our lives is to be happy.");
        authors.add("Dalai Lama");

        quotes.add("Life is what happens when you're busy making other plans.");
        authors.add("John Lennon");

        quotes.add("Get busy living or get busy dying.");
        authors.add("Stephen King");

        quotes.add("You only live once, but if you do it right, once is enough.");
        authors.add("Mae West");

        quotes.add("Many of life’s failures are people who did not realize how close they were to success when they gave up.");
        authors.add("Thomas A. Edison");

        quotes.add("If you want to live a happy life, tie it to a goal, not to people or things.");
        authors.add("Albert Einstein");

        quotes.add("Never let the fear of striking out keep you from playing the game.");
        authors.add("Babe Ruth");

        quotes.add("Money and success don’t change people; they merely amplify what is already there.");
        authors.add("Will Smith");

        quotes.add("Your time is limited, don’t waste it living someone else’s life.");
        authors.add("Steve Jobs");

        quotes.add("Not how long, but how well you have lived is the main thing.");
        authors.add("Seneca");

        quotes.add("If life were predictable it would cease to be life, and be without flavor.");
        authors.add("Eleanor Roosevelt");

        quotes.add("The whole secret of a successful life is to find out what is one’s destiny to do, and then do it.");
        authors.add("Henry Ford");

        quotes.add("In order to write about life first you must live it.");
        authors.add("Ernest Hemingway");

        quotes.add("The big lesson in life, baby, is never be scared of anyone or anything.");
        authors.add("Frank Sinatra");

        quotes.add("Sing like no one’s listening, love like you’ve never been hurt, dance like nobody’s watching, and live like it’s heaven on earth.");
        authors.add("Various sources");

        quotes.add("Curiosity about life in all of its aspects, I think, is still the secret of great creative people.");
        authors.add("Leo Burnett");

        quotes.add("Life is not a problem to be solved, but a reality to be experienced.");
        authors.add("Soren Kierkegaard");

        quotes.add("The unexamined life is not worth living.");
        authors.add("Socrates");

        quotes.add("Turn your wounds into wisdom.");
        authors.add("Oprah Winfrey");
    }

    private void displayRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.size());
        String randomQuote = quotes.get(index);
        String randomAuthor = authors.get(index);
        tvQuote.setText(randomQuote);
        tvAuthor.setText(randomAuthor);
    }

    private void saveToFavorites(String quote, String author) {
        Set<String> favorites = sharedPreferences.getStringSet(FAVORITES_KEY, new HashSet<>());
        favorites.add(quote + " - " + author);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(FAVORITES_KEY, favorites);
        editor.apply();
        Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
    }
}


