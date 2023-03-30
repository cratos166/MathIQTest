package com.nbird.mathiqtest.GAME;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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
import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nbird.mathiqtest.DATA.Constants;
import com.nbird.mathiqtest.DATA.GameData;
import com.nbird.mathiqtest.DATA.Level_1_Data;
import com.nbird.mathiqtest.DATA.LocalData;
import com.nbird.mathiqtest.GAME.MODEL.GameHolder;
import com.nbird.mathiqtest.LEVEL.LevelActivity;
import com.nbird.mathiqtest.MainActivity;
import com.nbird.mathiqtest.R;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    int level;
    GameHolder gameHolder;

    LinearLayout hintButton,rewatchHint;
    ImageView imageView;

    TextInputEditText editText;
    Button checkAnswer;

    int hintNumber=1;
    TextView hintText,pointTextView,levelName;

    String hintSuperText="";
    String levelNameStr;

    LottieAnimationView party_popper;

    int score=200,mainLevel;

    GameData gameData;

    LinearLayout linearLayoutPoint;

    LocalData localData;
    CountDownTimer countDownTimer;


    RewardedAd rewardedAd;
    private void rewardAdsLoader(){

        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(GameActivity.this, Constants.REWARD_ADS,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.

                        //   Toast.makeText(MainActivity.this, loadAdError.toString(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, loadAdError.toString());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        // Toast.makeText(context, "Ad was loaded", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Ad was loaded.");
                    }
                });


        try{
            rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdClicked() {
                    // Called when a click is recorded for an ad.
                    Log.d(TAG, "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.




                    Log.d(TAG, "Ad dismissed fullscreen content.");
                    rewardedAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    // Called when ad fails to show.
                    Toast.makeText(GameActivity.this, "Ads failed to load", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Ad failed to show fullscreen content.");
                    rewardedAd = null;
                }

                @Override
                public void onAdImpression() {
                    // Called when an impression is recorded for an ad.



                    Log.d(TAG, "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when ad is shown.
                    Log.d(TAG, "Ad showed fullscreen content.");
                }
            });
        }catch (Exception e){

        }



    }


    private void adShow(){
        AdView mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.VISIBLE);
        countDownTimer=new CountDownTimer(1000*20,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                try{mAdView.destroy();}catch (Exception e){}
                 adShow();
            }
        }.start();





//        MobileAds.initialize(GameActivity.this);
//        AdLoader adLoader = new AdLoader.Builder(GameActivity.this, Constants.NATIVE_ADS)
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        ColorDrawable cd = new ColorDrawable(0x393F4E);
//
//                        NativeTemplateStyle styles = new NativeTemplateStyle.Builder().withMainBackgroundColor(cd).build();
//                        TemplateView template = findViewById(R.id.my_template);
//                        template.setStyles(styles);
//                        template.setNativeAd(nativeAd);
//                        template.setVisibility(View.VISIBLE);
//
//                    }
//                })
//                .build();
//
//        adLoader.loadAd(new AdRequest.Builder().build());

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        adShow();
        rewardAdsLoader();



        level=getIntent().getIntExtra("LEVEL",1);
        levelNameStr=getIntent().getStringExtra("LEVEL_NAME");
        mainLevel=getIntent().getIntExtra("MAIN_LEVEL",1);

            Level_1_Data level_1_data=new Level_1_Data();
            gameHolder=level_1_data.start(level);


        hintButton=(LinearLayout) findViewById(R.id.bb);
        rewatchHint=(LinearLayout) findViewById(R.id.rewatchHint);
        imageView=(ImageView) findViewById(R.id.imageView);
        editText=(TextInputEditText) findViewById(R.id.answer);
        checkAnswer=(Button) findViewById(R.id.checkAnswer);
        hintText=(TextView) findViewById(R.id.hintText);
        party_popper=(LottieAnimationView) findViewById(R.id.party_popper);
        pointTextView=(TextView) findViewById(R.id.pointTextView);
        levelName=(TextView) findViewById(R.id.levelName);
        linearLayoutPoint=(LinearLayout) findViewById(R.id.linearLayoutPoint);


        levelName.setText(levelNameStr);

        imageView.setImageResource(gameHolder.getImage());

        gameData=new GameData(GameActivity.this);
        localData=new LocalData(GameActivity.this);


        if(gameData.levelPermissionGetter(level).equals("LOCKED")){
            gameData.levelPermissionSetter(level,"UNLOCKED");
        }else if(gameData.levelPermissionGetter(level).equals("SUCCESSFUL")||gameData.levelPermissionGetter(level).equals("FAILED")){
            editText.setText(gameHolder.getCorrectAnswer());
            checkAnswer.setEnabled(false);
            checkAnswer.setVisibility(View.INVISIBLE);
            editText.setEnabled(false);
            hintButton.setVisibility(View.INVISIBLE);
            linearLayoutPoint.setVisibility(View.INVISIBLE);
            rewatchHint.setVisibility(View.INVISIBLE);
        }






        score=gameData.pointGetter(level);
        pointTextView.setText("Points : "+score);
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            answerChecker();

                    }
                }
                return false;
            }
        });

        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerChecker();
            }
        });


        rewatchHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hintDialog();
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hintNumber==1){
                    hintSuperText="HINT 1 : "+gameHolder.getHint1();
                    hintNumber++;
                    hintDialog();

                    gameData.pointSetter(level,hintNumber);
                    hintText.setText("Hint : 2");
                    score=gameData.pointGetter(level);
                }else if(hintNumber==2){
                    hintSuperText=hintSuperText+"\nHINT 2 : "+gameHolder.getHint2();
                    hintNumber++;
                    hintDialog();

                    gameData.pointSetter(level,hintNumber);
                    hintText.setText("Hint : 3");
                    score=gameData.pointGetter(level);
                }else if(hintNumber==3){
                    hintSuperText=hintSuperText+"\nHINT 3 : "+gameHolder.getHint3();
                    hintNumber++;
                    hintDialog();

                    gameData.pointSetter(level,hintNumber);
                    hintText.setText("GET ANSWER");
                    score=gameData.pointGetter(level);
                }else if(hintNumber==4){

                        gameData.levelPermissionSetter(level+1, "UNLOCKED");

                    if(!gameData.levelPermissionGetter(level).equals("SUCCESSFUL")){
                        gameData.levelPermissionSetter(level,"FAILED");
                    }
                    hintNumber++;
                    hintDialog();

                    gameData.pointSetter(level,hintNumber);
                    score=gameData.pointGetter(level);
                    hintSuperText=hintSuperText+"\nANSWER : "+gameHolder.getCorrectAnswer();

                }
                pointTextView.setText("Points : "+score);
            }
        });


    }

    private void answerChecker(){
        if(editText.getText().toString().trim().equals(gameHolder.getCorrectAnswer())){
            party_popper.setVisibility(View.VISIBLE);
            party_popper.playAnimation();

            gameData.levelPermissionSetter(level,"SUCCESSFUL");
            gameData.levelPermissionSetter(level+1, "UNLOCKED");


            localData.setMyScore(score);

            new CountDownTimer(1000*3,1000){

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                     winDialog("You earned "+score+" points");
                }
            }.start();

        }else{
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
            editText.setText("");
            pointTextView.setText("Points : "+score);

        }
    }



    public void hintDialog(){
        AlertDialog.Builder builderFact=new AlertDialog.Builder(GameActivity.this, R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(GameActivity.this).inflate(R.layout.hint_dialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(false);

        AlertDialog alertDialog=builderFact.create();

        Button okayButton = (Button) viewFact.findViewById(R.id.buttonYes);
        TextView hint1Text=(TextView) viewFact.findViewById(R.id.hint1Text);
        TextView hint2Text=(TextView) viewFact.findViewById(R.id.hint2Text);
        TextView hint3Text=(TextView) viewFact.findViewById(R.id.hint3Text);
        TextView answerText=(TextView) viewFact.findViewById(R.id.answerText);
        LinearLayout layout1=(LinearLayout) viewFact.findViewById(R.id.layout1);
        LinearLayout layout2=(LinearLayout) viewFact.findViewById(R.id.layout2);
        LinearLayout layout3=(LinearLayout) viewFact.findViewById(R.id.layout3);
        LinearLayout layout4=(LinearLayout) viewFact.findViewById(R.id.layout4);


        if(hintNumber==2){
            layout1.setVisibility(View.VISIBLE);
            hint1Text.setText(gameHolder.getHint1());
        }else if(hintNumber==3){
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            hint1Text.setText(gameHolder.getHint1());
            hint2Text.setText(gameHolder.getHint2());
        }else if(hintNumber==4){
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            hint1Text.setText(gameHolder.getHint1());
            hint2Text.setText(gameHolder.getHint2());
            hint3Text.setText(gameHolder.getHint3());
        }else if(hintNumber==5){
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            hint1Text.setText(gameHolder.getHint1());
            hint2Text.setText(gameHolder.getHint2());
            hint3Text.setText(gameHolder.getHint3());
            answerText.setText(gameHolder.getCorrectAnswer());
            okayButton.setText("NEXT");
        }

//        textTitle.setText(hintSuperText);



        MobileAds.initialize(GameActivity.this);
        AdLoader adLoader = new AdLoader.Builder(GameActivity.this, Constants.NATIVE_ADS)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        ColorDrawable cd = new ColorDrawable(0x393F4E);
                        NativeTemplateStyle styles = new NativeTemplateStyle.Builder().withMainBackgroundColor(cd).build();
                        TemplateView template = viewFact.findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);
                        template.setVisibility(View.VISIBLE);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());


        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        try{
            alertDialog.show();
        }catch (Exception e){

        }

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    alertDialog.dismiss();
                }catch (Exception e){

                }



                if(hintNumber==5){


                    if (rewardedAd != null) {

                        rewardedAd.show(GameActivity.this, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                Intent intent=new Intent(GameActivity.this,GameActivity.class);
                                intent.putExtra("LEVEL",level+1);
                                startActivity(intent);
                                finish();

                            }
                        });
                    } else {
                        Intent intent=new Intent(GameActivity.this,GameActivity.class);
                        intent.putExtra("LEVEL",level+1);
                        startActivity(intent);
                        finish();

                    }





                }else{
                    if (rewardedAd != null) {

                        rewardedAd.show(GameActivity.this, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                rewardedAd=null;
                                rewardAdsLoader();
                            }
                        });
                    } else {

                        rewardedAd=null;
                        rewardAdsLoader();
                    }
                }




            }
        });





        }


    public void winDialog(String text){
        AlertDialog.Builder builderFact=new AlertDialog.Builder(GameActivity.this, R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(GameActivity.this).inflate(R.layout.dialog_model,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(false);

        AlertDialog alertDialog=builderFact.create();

        Button okayButton = (Button) viewFact.findViewById(R.id.buttonYes);
        TextView textTitle=(TextView) viewFact.findViewById(R.id.textTitle);


        textTitle.setText(text);


        MobileAds.initialize(GameActivity.this);
        AdLoader adLoader = new AdLoader.Builder(GameActivity.this, Constants.NATIVE_ADS)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        ColorDrawable cd = new ColorDrawable(0x393F4E);
                        NativeTemplateStyle styles = new NativeTemplateStyle.Builder().withMainBackgroundColor(cd).build();
                        TemplateView template = viewFact.findViewById(R.id.my_template);
                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);
                        template.setVisibility(View.VISIBLE);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());


        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        try{
            alertDialog.show();
        }catch (Exception e){

        }


        okayButton.setText("NEXT");

        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    alertDialog.dismiss();
                }catch (Exception e){

                }


                if (rewardedAd != null) {

                    rewardedAd.show(GameActivity.this, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
                            Intent intent=new Intent(GameActivity.this,GameActivity.class);
                            intent.putExtra("LEVEL",level+1);
                            startActivity(intent);
                            finish();

                        }
                    });
                } else {
                    Intent intent=new Intent(GameActivity.this,GameActivity.class);
                    intent.putExtra("LEVEL",level+1);
                    startActivity(intent);
                    finish();

                }






            }
        });


    }



    public void onBackPressed(){


        if (rewardedAd != null) {

            rewardedAd.show(GameActivity.this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.

                    Intent intent=new Intent(GameActivity.this, LevelActivity.class);
                    intent.putExtra("LEVEL",mainLevel);
                    startActivity(intent);
                    finish();

                }
            });
        } else {
            Intent intent=new Intent(GameActivity.this, LevelActivity.class);
            intent.putExtra("LEVEL",mainLevel);
            startActivity(intent);
            finish();

        }



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        try{
            countDownTimer.cancel();
        }catch (Exception e){

        }
        Runtime.getRuntime().gc();

    }

}