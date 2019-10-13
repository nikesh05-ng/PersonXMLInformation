package com.example.personxmlinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonActivity extends AppCompatActivity {

    private TextView nameTextView = null;
    private Button moreButton = null;
    private ImageView personImageView = null;

    //we have constructed the data so keep it null
    private Person data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);


        Bundle bundle = getIntent().getExtras();

        data = (Person) bundle.getSerializable("key");

        // wire objects with widgets
        nameTextView = findViewById(R.id.textView1);
        moreButton   = findViewById(R.id.button);
        personImageView = findViewById(R.id.imageView);

        // populate textview and image view with data
        nameTextView.setText(data.getName());

        // remove the extension from the name
        String imageName = data.getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));

        int imageId = this.getResources().getIdentifier(
                imageName,
                "drawable",
                this.getPackageName());

        personImageView.setImageResource(imageId);

        //deal with the button
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonActivity.this,DetailsActivity.class);
                Bundle bundle = new Bundle();
                //add data to bundle
                bundle.putSerializable("data", data);
                intent.putExtras(bundle);
                //start activity
                startActivity(intent);
            }
        });
    }
}
