package com.mrerror.parachut.ui.product;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.mrerror.parachut.R;
import com.squareup.picasso.Picasso;

public class FullImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        ImageView fullScreenImageView = findViewById(R.id.fullScreenImageView);
        Intent callingActivityIntent = getIntent();

            Uri imageUri = callingActivityIntent.getData();

                Picasso.with(this)
                        .load(imageUri)
                        .into(fullScreenImageView);


    }

    public void closeImage(View view) {
        finish();
    }
}
