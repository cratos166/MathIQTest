package com.nbird.mathiqtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nbird.mathiqtest.DATA.LocalData;
import com.nbird.mathiqtest.GAME.GameActivity;
import com.nbird.mathiqtest.MAIN.ADAPTER.ModeAdapter;
import com.nbird.mathiqtest.MAIN.ADAPTER.ProfileSelectorAdapter;
import com.nbird.mathiqtest.MAIN.ADAPTER.RecyclerViewLeaderBoardAdapter;
import com.nbird.mathiqtest.MAIN.MODEL.Modes;
import com.nbird.mathiqtest.MAIN.MODEL.User;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    List<Modes> modeList;
    LocalData localData;
    CircleImageView propic;
    TextView usernameTextView;
    FirebaseStorage storage;
    StorageReference storageReference;
    ProfileSelectorAdapter myAdapter;
    CircleImageView mainActivitPropic;
    Uri imageUri;


    Button play,how_to_play,about_us;
    LottieAnimationView profilebutton;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propic=(CircleImageView) findViewById(R.id.imageView);
        usernameTextView=(TextView) findViewById(R.id.myName);
        play=(Button) findViewById(R.id.play);
        how_to_play=(Button) findViewById(R.id.how_to_play);
        about_us=(Button) findViewById(R.id.about_us);
        profilebutton=(LottieAnimationView) findViewById(R.id.profilebutton);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerDialog();
            }
        });

        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaderBoard();
            }
        });

        how_to_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        getInfo();


        propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });


        ArrayList<User> listARM=new ArrayList();
        myRef.child("LEADERBOARD").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    listARM.add(dataSnapshot.getValue(User.class));
                }

                Collections.sort(listARM, new Comparator<User>() {
                    @Override
                    public int compare(User a1, User a2) {
                        return a1.getScore() - a2.getScore();
                    }
                });


                int totalScore= localData.getMyScore();

                User user;
                if(localData.getAvaInt()==101){
                    user=new User(localData.getMyName(),R.drawable.default_pic,totalScore,localData.getMyUID());
                }else if(localData.getAvaInt()==100){
                    user=new User(localData.getMyName(),R.drawable.default_pic,totalScore,localData.getMyUID());
                }else{
                    user=new User(localData.getMyName(),localData.getAvaFromList(localData.getAvaInt()),totalScore,localData.getMyUID());
                }



                if(listARM.size()<50){

                    myRef.child("LEADERBOARD").child(localData.getMyUID()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });
                }else{
                    for(int i=0;i<listARM.size();i++){


                        if(listARM.get(i).getScore()<localData.getMyScore()){
                            myRef.child("LEADERBOARD").child(listARM.get(listARM.size()-1).getMyUID()).removeValue();
                            myRef.child("LEADERBOARD").child(localData.getMyUID()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            });
                            break;
                        }


                    }

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void leaderBoard(){

        loadingDialog=new Dialog(MainActivity.this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
        try{
            loadingDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }


        ArrayList<User> list=new ArrayList();
        myRef.child("LEADERBOARD").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    list.add(dataSnapshot.getValue(User.class));
                }

                Collections.sort(list, new Comparator<User>() {
                    @Override
                    public int compare(User a1, User a2) {
                        return a1.getScore() - a2.getScore();
                    }
                });
                leaderBoardDialog(list);


                try{loadingDialog.dismiss();}catch (Exception e){}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void leaderBoardDialog(ArrayList<User> list){
        AlertDialog.Builder builderFact=new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(MainActivity.this).inflate(R.layout.leaderboard_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(false);

        AlertDialog alertDialog=builderFact.create();

        RecyclerView recyclerView = (RecyclerView) viewFact.findViewById(R.id.recyclerView);
        Button cancelButton=(Button) viewFact.findViewById(R.id.cancelButton);


        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        try{
            alertDialog.show();
        }catch (Exception e){

        }


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    alertDialog.dismiss();
                }catch (Exception e){

                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewLeaderBoardAdapter categoryAdapter = new RecyclerViewLeaderBoardAdapter(list);
        recyclerView.setAdapter(categoryAdapter);



    }

    public void recyclerDialog(){
        AlertDialog.Builder builderFact=new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_recycler,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(true);

        AlertDialog alertDialog=builderFact.create();

        RecyclerView recyclerView = (RecyclerView) viewFact.findViewById(R.id.recyclerView);



        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        try{
            alertDialog.show();
        }catch (Exception e){

        }

        modeList=new ArrayList<>();
        parto();
        //   timerStarter();

        ModeAdapter myAdapter=new ModeAdapter(this,modeList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdapter);



    }




    public void parto(){

        List<Boolean> levelList = localData.getMyLevelStatus();

        modeList.add(new Modes("Beginner",R.drawable.level1,"UNLOCKED"));



            if(levelList.get(2)){
                modeList.add(new Modes("Intermediate",R.drawable.level2,"UNLOCKED"));
            }else{
                modeList.add(new Modes("Intermediate",R.drawable.level2,"LOCKED"));
            }

            if(levelList.get(3)){
                modeList.add(new Modes("Advanced",R.drawable.level3,"UNLOCKED"));
            }else{
                modeList.add(new Modes("Advanced",R.drawable.level3,"LOCKED"));
            }

            if(levelList.get(4)){
                modeList.add(new Modes("Expert",R.drawable.level4,"UNLOCKED"));
            }else{
                modeList.add(new Modes("Expert",R.drawable.level4,"LOCKED"));
            }

        if(levelList.get(5)){
            modeList.add(new Modes("Grandmaster",R.drawable.level5,"UNLOCKED"));
        }else{
            modeList.add(new Modes("Grandmaster",R.drawable.level5,"LOCKED"));
        }





    }


    public void getInfo(){

        localData = new LocalData(MainActivity.this);

        if(localData.isFirstTime()){
            Random random=new Random();
            String username="Player"+random.nextInt(10000);
            localData.setMyName(username);
            usernameTextView.setText(username);

            start();
//            ProfileSelectDialog profileSelectDialog=new ProfileSelectDialog(MainActivity.this,myrv,propic,usernameTextView);
//            profileSelectDialog.start();
        }else{

            if(localData.getAvaInt()==101){

                Glide.with(MainActivity.this).load(localData.getMyImageURL()).into(propic);


            }else if(localData.getAvaInt()==100){
                propic.setBackgroundResource(0);
                propic.setBackgroundResource(R.drawable.default_pic);
            }else{
                propic.setBackgroundResource(0);
                propic.setBackgroundResource(localData.getAvaFromList(localData.getAvaInt()));
            }

            usernameTextView.setText(localData.getMyName());


        }



    }


    public void start(){

        ///   localData=new LocalData(context);

        AlertDialog.Builder builderRemove=new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);
        View viewRemove1= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_profile_selection,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderRemove.setView(viewRemove1);
        builderRemove.setCancelable(false);
        Button buttonYes=(Button) viewRemove1.findViewById(R.id.buttonYes);



        TextInputEditText username=(TextInputEditText) viewRemove1.findViewById(R.id.username);
        RecyclerView recyclerView=(RecyclerView) viewRemove1.findViewById(R.id.avatarRecyclerView);
        mainActivitPropic=(CircleImageView) viewRemove1.findViewById(R.id.propic);




        myAdapter=new ProfileSelectorAdapter(MainActivity.this,localData.getAvaList(),mainActivitPropic);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
        recyclerView.setAdapter(myAdapter);







        final AlertDialog alertDialog=builderRemove.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }


        username.setText(localData.getMyName());


        username.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:


                            if(username.getText().toString().equals("")){
                                username.setError("Field cannot be empty.");
                            }else if(username.getText().toString().length()>10){
                                username.setError("Field length cannot be more than 10 characters");
                            }else{
                                localData.setMyName(username.getText().toString());
                                try{
                                    alertDialog.dismiss();
                                }catch (Exception e){

                                }

                            }
                            usernameTextView.setText(localData.getMyName());
                            if(localData.getAvaInt()==101){
                                propic.setImageBitmap(null);
                                Glide.with(MainActivity.this).load(localData.getMyImageURL()).into(propic);


                            }else if(localData.getAvaInt()==100){
                                propic.setImageBitmap(null);
                                propic.setBackgroundResource(R.drawable.default_pic);
                            }else{
                                propic.setImageBitmap(null);
                                propic.setBackgroundResource(localData.getAvaFromList(localData.getAvaInt()));
                            }







                    }
                }
                return false;
            }
        });



        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(username.getText().toString().equals("")){
                    username.setError("Field cannot be empty.");
                }else if(username.getText().toString().length()>10){
                    username.setError("Field length cannot be more than 10 characters");
                }else{
                    localData.setMyName(username.getText().toString());


                    usernameTextView.setText(localData.getMyName());
                    if(localData.getAvaInt()==101){

                        //    Toast.makeText(MainActivity.this, localData.getMyImageURL(), Toast.LENGTH_SHORT).show();

                        propic.setImageBitmap(null);
                        Glide.with(MainActivity.this).load(localData.getMyImageURL()).into(propic);


                    }else if(localData.getAvaInt()==100){
                        propic.setImageBitmap(null);
                        propic.setBackgroundResource(0);
                        propic.setBackgroundResource(R.drawable.default_pic);
                    }else{
                        propic.setImageBitmap(null);
                        propic.setBackgroundResource(0);
                        propic.setBackgroundResource(localData.getAvaFromList(localData.getAvaInt()));
                    }

                    try{
                        alertDialog.dismiss();
                    }catch (Exception e){

                    }

                }







            }
        });


        mainActivitPropic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);


        //  Toast.makeText(context, "dfkmdkf", Toast.LENGTH_SHORT).show();


        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
              /*  case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        nav_image.setImageBitmap(selectedImage);
                        uploadImage();
                    }
                    break;*/
                case 1:

                    if (resultCode == RESULT_OK) {

                        try {
                            imageUri = data.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            try {
                                mainActivitPropic.setBackgroundResource(0);
                                mainActivitPropic.setImageBitmap(selectedImage);
                            } catch (Exception e) {

                            }


                            uploadImage();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }




    }


    private void selectImage() {
        final CharSequence[] options = {"Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

               /* if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } */
                if (options[item].equals("Choose from Gallery")) {


                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);


                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }


    private void uploadImage() {


        if (imageUri != null) {

            try {
                myAdapter.setAllLinearLayoutDefault();
            } catch (Exception e) {

            }


            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();


            storageReference = storageReference.child("USER/IMAGE/" + localData.getMyUID());


            // adding listeners on upload
            // or failure of image
            storageReference.putFile(imageUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                    try {

                                        //    StorageReference urlref = storageReference.child("USER/IMAGE/" +localData.getMyUID());
                                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri downloadUrl) {
                                                String imageurl = downloadUrl.toString();
                                                //  Toast.makeText(MainActivity.this, imageurl, Toast.LENGTH_SHORT).show();
                                                localData.setMyImageURL(imageurl);
                                                localData.setAvaInt(101);
                                            }
                                        });

                                    } catch (Exception e) {

                                    }
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(MainActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }
    }


}