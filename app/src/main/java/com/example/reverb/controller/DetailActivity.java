package com.example.reverb.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Transition;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reverb.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    // class variables to access news article data coming in via an intent

    private String title;
    private String description;
    private String urlToImage;
    private String url;
    private String content;

    // variables to access the widgets in the detail layout

    private ImageView newsImageView;
    private TextView titleTextView;
    private TextView contentTextView;
    private TextView descriptionTextView;
    private Button newsSourceBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get all the data from the launching activity via an intent

        // TODO: 25-09-2022: News ImageView data is not passed from MainActivity to DetailActivity (while using Retrofit or Glide)
        // TODO: 25-09-2022: Implement a workaround/fix to get the data through intent in DetailActivity

        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        urlToImage = getIntent().getStringExtra("urlToImage");
        url = getIntent().getStringExtra("url");
        content = getIntent().getStringExtra("content");

        // get all the widgets from the layout

        newsImageView = findViewById(R.id.news_ImageView);
        titleTextView = findViewById(R.id.titleTextView_detail_layout);
        contentTextView = findViewById(R.id.contentTextView_detail_layout);
        descriptionTextView  =findViewById(R.id.description_textView);
        newsSourceBtn = findViewById(R.id.news_source_btn);

        Log.d("URLIMG", urlToImage);

        // TODO: 25-09-2022: Fix Pending (ISSUE: News image data not available in DetailActivity via an intent)

        //Picasso.get().load(urlToImage).into(newsImageView);

        Picasso.get().load(urlToImage).into(newsImageView);

        //Glide.with(this).load(urlToImage).into(newsImageView);

        titleTextView.setText(title);
        contentTextView.setText(content);
        descriptionTextView.setText(description);

        newsSourceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newsSourceIntent = new Intent(Intent.ACTION_VIEW);
                newsSourceIntent.setData(Uri.parse(url));
                startActivity(newsSourceIntent);
            }
        });



    }
}