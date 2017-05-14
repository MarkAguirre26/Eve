package com.android.eve;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity implements RecognitionListener {
    EditText txt_writte_message;
    TextView message_text;
    ImageView img_eve;
    ListView lv_convo;
    String s = "";
    boolean isSearchEmpty = false;
    private Random random;
    private Random random2;
    String[] angryFrustratedSad, angryhigh, content_high, happy_high, drawableString_5, drawableString_6, drawableString_7, drawableString_8, drawableString_9;
    int initialIndex = 0;
    private ArrayList<ChatMessage> i;
    TextToSpeech t1;
    ProgressDialog progressDialog;
    private ToggleButton toggleButton;
    private ProgressBar progressBar;
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "MainActivity";
    LinearLayout linear_emotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tools.setFullScreen(this);
        init();


    }

    private void init() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        // progressDialog.show();
        lv_convo = (ListView) findViewById(R.id.lv_convo);
        txt_writte_message = (EditText) findViewById(R.id.txt_writte_message);
        message_text = (TextView) findViewById(R.id.message_text);
        img_eve = (ImageView) findViewById(R.id.img_eve);
        txt_writte_message.setEnabled(false);

//set Scrol caret to bottom
        random = new Random();
        random2 = new Random();
        lv_convo.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        lv_convo.setStackFromBottom(true);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        //toggleButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {

                    // toggleButton.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(true);
                    speech.startListening(recognizerIntent);
                } else {
                    //toggleButton.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    speech.stopListening();
                }
            }
        });
        drawableString_5 = new String[]{"I think that’s a brave thing to do.", "I think it’s a good thing to do.", "I think that’s one of the best things to do."};
        drawableString_6 = new String[]{"Hope things are good for you today. Know I’m here and I care. :)", "I know life isn’t fair sometimes.\n-I’m here and I care. :)", "Unfortunately, there’s no way around sadness.. But through it, there are people will be there for you and will be whenever you need them. :)", "No matter how tough things are.. \nI have so much faith in you. :)\nYou are amazing and you will be okay. :)", "There is something you must always remember.. \nYou are braver than you believe, stronger than you seem and smarter than you know. :)", "Don’t let yesterday take up too much of today.:)", "Life doesn’t have to be perfect to be wonderful.:)", "Life is never a problem that you need to solve..Rather, it is a present that should always be enjoyed. :)\nMake the best out of the gift of a new day! :)", "Keep smiling and someday life will tire of troubling you! :)", "Cheer up because you are loved and always will be! :)", "Always ask for what you deserve in your prayers instead of what you desire.. Because you deserve a lot. :)", "I’m hoping that you have a great day. I wish you the best and hope everything goes your way! :)", "As soon as you can trust yourself, you can start to live again. :)"};


        angryFrustratedSad = new String[]{"", "I’m here for you. We can talk about it.\nWhy do you think you’re feeling this way?", "I’m sorry to hear that.\nHow are you right now?", "That’s terrible!\nHow do you feel about this?", "I’m sorry about this.\nHow long have you been bullied?", "Do you know who’s bullying you?", "Hmm..\nI think I understand better now.\nWhat do you do whenever you experience this?", drawableString_5[random2.nextInt(2)] + "Did you feel better?", "That’s okay. I’m here to help you feel better.\nHow do you want me to help you?", "I will try my best to help you.There are a lot of ways to respond to the bullying.Let’s find something that will suit you."};
        angryhigh = new String[]{"I will try my best to help you.", "There are different ways to respond to the cyber bullying.Let’s try to figure something that’ll suit you.One, you can ignore it.Two, you can ask for help.Three, you can stand up for yourself.Which one do you like best?", "You can also try ignoring them.", "But know that things will get better.I suggest that you should try to talk to someone.Your parents, your friend, or someone you trust.It will help you feel better.", "Maybe I can do better next time."};
        content_high = new String[]{"Seems like you are feeling well.I think I understand better.What do you do whenever you experience this?", "What did you do today that made you feel         good?", "That’s okay. I’m here to help you feel better.", "But it can help you feel better.", "Did you find this conversation helpful?", "But know that I am here for you."};
        happy_high = new String[]{"It’s good to see that you are feeling positive!", "I’m really happy to hear that! (that I made you feel better)", "I’m very glad I helped you!"};

        initialMessage();


    }

    void voice(final String text) {
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    t1.setLanguage(Locale.ENGLISH);
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }, 100);
                }
            }
        });

    }

    void initialMessage() {
        i = new ArrayList<>();
        initialIndex = 0;
        variables.index = 0;
        voice("Hi! ! I'm Eve, your virtual buddy,I'm here to listen up to you and help you to feel better, How are you feeling right now?");

        setMessage("Eve", "Hi " + userInfo.name + "! I'm Eve, your virtual buddy", 2, 404);//404  means not in range of 0-11
        img_eve.setImageResource(R.drawable.eve_happy_high);
        setMessage("Eve", "I'm here to listen up to you and help you to feel better", 2, 404);
        img_eve.setImageResource(R.drawable.eve_happy_low);
        setMessage("Eve", "How are you feeling right now?", 2, 0);
        img_eve.setImageResource(R.drawable.eve_content_high);
        //progressDialog.dismiss();

        MyAdapter myAdapter = new MyAdapter(this, R.layout.chatbubble, i);
        lv_convo.setAdapter(myAdapter);
    }

    public void cmd_emotion_Clicked(View view) {
        Button b = (Button) view;

        txt_writte_message.setText("");
        txt_writte_message.setText(b.getTag().toString());
    }

    public void cmd_send_Clicked(View view) {
        if (txt_writte_message.getText().length() >= 1) {
            txt_writte_message.setEnabled(true);
            random = new Random();
            String message = txt_writte_message.getText().toString();
            setMessage("", message, 1, 0);
            //  if (i.size() <= 4 || isSearchEmpty == true) {
            setEmotion(message);
            //  }
            setReply();
            hideKeyBoard();
            initialIndex++;
            Log.d("initialIndex", initialIndex + " " + i.get(i.size() - 1).getBody().toString());
            new CountDownTimer(2500, 500) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    img_eve.setImageResource(variables.avatarArray[6]);
                }
            }.start();

        }
    }

    private void hideKeyBoard() {
        InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setEmotion(String message) {
        isSearchEmpty = false;
        if (message.toLowerCase().contains("afraid")) {
            variables.index = 0;
        } else if (message.toLowerCase().contains("little") || message.toLowerCase().contains("bit angry")) {
            variables.index = 1;
        } else if (message.toLowerCase().contains("very content") || message.toLowerCase().contains("calm") || message.toLowerCase().contains("content") || message.toLowerCase().contains("much content") || message.toLowerCase().contains("calm")) {
            variables.index = 2;
        } else if (message.toLowerCase().contains("frustrated") || message.toLowerCase().contains("frustrate") || message.toLowerCase().contains("very") || message.toLowerCase().contains("angry") || message.toLowerCase().contains("very angry") || message.toLowerCase().contains("sad") || message.toLowerCase().contains("lonely") || message.toLowerCase().contains("very sad") || message.toLowerCase().contains("such sad") || message.toLowerCase().contains("sad much") || message.toLowerCase().contains("much sad")) {
            variables.index = 3;
        } else if (message.toLowerCase().contains("very happy") || message.toLowerCase().contains("happy")) {
            variables.index = 4;
        } else if (message.toLowerCase().contains("little happy") || message.toLowerCase().contains("a little bit happy") || message.toLowerCase().contains("happy")) {
            variables.index = 5;
        } else if (message.toLowerCase().contains("normal") || message.toLowerCase().contains("normal")) {
            variables.index = 6;
        } else if (message.toLowerCase().contains("bit sad") || message.toLowerCase().contains("sad")) {
            variables.index = 7;
        } else if (message.toLowerCase().contains("surprised") || message.toLowerCase().contains("shock") || message.toLowerCase().contains("shocked")) {
            variables.index = 8;
        } else {
            variables.index = 9;
            isSearchEmpty = true;
        }
        img_eve.setImageResource(variables.avatarArray[variables.index]);

        Log.d("variableIndex", variables.index + "");
    }

    void byeMessage() {
        setMessage("Eve", "‘Til next time, goodbye!", 2, 404);
    }

    private void setReply() {

        //   Toast.makeText(getApplicationContext(), variables.index + "", Toast.LENGTH_LONG).show();
        new CountDownTimer(1000, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                s = s + ".";
                txt_writte_message.setHint(s.toString());
            }

            @Override
            public void onFinish() {

                s = "";
                txt_writte_message.setHint("write message");
                if (variables.index == 3) {
                    // if (initialIndex < angryFrustratedSad.length) {
                    if (initialIndex == 1) {
                        setMessage("Eve", angryFrustratedSad[initialIndex], 2, 404);
                        img_eve.setImageResource(R.drawable.eve_sad);
                    } else if (initialIndex == 2) {
                        setMessage("Eve", angryFrustratedSad[initialIndex], 2, 2);//old2, 404 = null
                    } else if (initialIndex == 3) {
                        setMessage("Eve", angryFrustratedSad[initialIndex], 2, 3);
                    } else if (initialIndex == 5) {
                        setMessage("Eve", angryFrustratedSad[initialIndex], 2, 4);
                    } else if (initialIndex == 6) {
                        setMessage("Eve", angryFrustratedSad[initialIndex], 2, 6);
                    } else if (initialIndex == 7) {
                        setMessage("Eve", angryFrustratedSad[initialIndex], 2, 5);
                    } else if (initialIndex == 8) {
                        if (i.get(i.size() - 1).getBody().contains("Yes")) {
                            setMessage("Eve", "I’m glad you felt better!", 2, 404);
                            //  byeMessage();
                        } else {
                            setMessage("Eve", angryFrustratedSad[initialIndex], 2, 7);
                        }
                    } else if (initialIndex == 9) {
                        if (i.get(i.size() - 1).getBody().contains("How to stop the bullying?")) {
                            img_eve.setImageResource(R.drawable.eve_content_low);
                            setMessage("Eve", "I will try my best to help you.There are a lot of ways to respond to the bullying.Let’s find something that will suit you.", 2, 8);
                        } else if (i.get(i.size() - 1).getBody().contains("Cheer me up")) {
                            img_eve.setImageResource(R.drawable.eve_happy_high);
                            setMessage("Eve", drawableString_6[random2.nextInt(12)] + "\n\nHave I in any way made you feel better?", 2, 9);
                        } else {
                            setMessage("Eve", angryFrustratedSad[initialIndex], 2, 7);
                        }

                    } else if (initialIndex == 10) {

                        if (i.get(i.size() - 1).getBody().contains("Tell them to stop")) {
                            img_eve.setImageResource(R.drawable.eve_normal);
                            setMessage("Eve", "- Yes, that’s a brave thing to do!", 2, 404);
                            setMessage("Eve", "- Let them know that what they’re\ndoing is hurtful and.. uncool.", 2, 404);
                            setMessage("Eve", "- You can also try ignoring them.", 2, 404);
                            setMessage("Eve", "- Eventually,\nthey will get tired and stop.", 2, 404);
                            img_eve.setImageResource(R.drawable.eve_content_low);
                            setMessage("Eve", " Have I in any way made you feel better?", 2, 9);
                        } else if (i.get(i.size() - 1).getBody().contains("Ignore it")) {
                            img_eve.setImageResource(R.drawable.eve_normal);
                            setMessage("Eve", "That’s a good idea!", 2, 404);
                            setMessage("Eve", "- Cyberbullies like getting responses from their targets.", 2, 404);
                            setMessage("Eve", "- So don’t give it to them..", 2, 404);
                            setMessage("Eve", "- If they don’t get a response from you, eventually, they will get tired and disappear.", 2, 404);
                            setMessage("Eve", "Have I in any way made you feel better?", 2, 9);
                        } else if (i.get(i.size() - 1).getBody().contains("Block the bully")) {
                            img_eve.setImageResource(R.drawable.eve_normal);
                            setMessage("Eve", "That’s a good idea!", 2, 404);
                            setMessage("Eve", "- If the cyberbullies can’t reach you, it will be harder for them to bully you", 2, 404);
                            setMessage("Eve", "Have I in any way made you feel better?", 2, 9);
                        } else if (i.get(i.size() - 1).getBody().contains("Ask for help")) {
                            img_eve.setImageResource(R.drawable.eve_normal);
                            setMessage("Eve", "- That’s a good idea!", 2, 404);
                            setMessage("Eve", "- You can try talking to someone..", 2, 404);
                            setMessage("Eve", "- It can be you parents, your teacher, or a friend you can trust.", 2, 404);
                            setMessage("Eve", "- You shouldn’t keep it to yourself.", 2, 404);
                            setMessage("Eve", "- It’s quite difficult, I know.", 2, 404);
                            setMessage("Eve", "- But it can help you feel better.", 2, 404);
                            setMessage("Eve", "Have I in any way made you feel better?", 2, 9);
                        } else if (i.get(i.size() - 1).getBody().contains("Laugh it off")) {
                            img_eve.setImageResource(R.drawable.eve_normal);
                            setMessage("Eve", "- If someone says something funny about you, try to laugh it off.", 2, 404);
                            setMessage("Eve", "- But if it’s not funny at all and you are really hurt by what the person said..", 2, 404);
                            setMessage("Eve", "- Try to tell them how you feel about it.", 2, 404);
                            setMessage("Eve", "- It can help you feel better.", 2, 404);
                            setMessage("Eve", "Have I in any way made you feel better?", 2, 9);
                        } else {
                            setMessage("Eve", angryFrustratedSad[initialIndex], 2, 7);
                        }

                    } else if (initialIndex == 11) {
                        if (i.get(i.size() - 1).getBody().contains("Yes")) {
                            img_eve.setImageResource(R.drawable.eve_happy_high);
                            setMessage("Eve", "I’m really happy to hear that! :)", 2, 404);
                            setMessage("Eve", "It was nice talking to you", 2, 404);
                            // byeMessage();
                        } else if (i.get(i.size() - 1).getBody().contains("No")) {
                            img_eve.setImageResource(R.drawable.eve_sad_low);
                            setMessage("Eve", "Aww.\n\nIt’s okay to feel low sometimes.", 2, 404);
                            byeMessage();
                        } else if (i.get(i.size() - 1).getBody().contains("Kinda")) {
                            img_eve.setImageResource(R.drawable.eve_content_low);
                            setMessage("Eve", "But know that things will get better. :)", 2, 404);
                            setMessage("Eve", "I suggest that you should try to talk to someone..\nYour parents, \nyour friend, of someone you can trust.\nIt will help you to feel better.", 2, 404);
                            setMessage("Eve", "Did you find this conversation helpful?", 2, 9);
                        } else {
                            byeMessage();
                        }

                    } else if (initialIndex == 12) {
                        if (i.get(i.size() - 1).getBody().contains("Yes")) {
                            setMessage("Eve", "I’m glad I helped you!\n\nIt was nice talking to you", 2, 404);
                            byeMessage();
                        } else if (i.get(i.size() - 1).getBody().contains("No") || i.get(i.size() - 1).getBody().contains("Kinda")) {
                            setMessage("Eve", "It was nice talking to you", 2, 404);
                            byeMessage();
                        } else {
                            byeMessage();
                        }
                    } else {
                        setMessage("Eve", angryFrustratedSad[initialIndex], 2, 404);
                    }


                } else if (variables.index == 1) {
                    if (initialIndex < angryhigh.length) {
                        setMessage("Eve", angryhigh[initialIndex], 2, 404);
                    } else if (initialIndex == angryhigh.length) {
                        byeMessage();
                    }
                } else if (variables.index == 2) {
                    if (initialIndex == 4) {
                        setMessage("Eve", content_high[initialIndex], 2, 4);
                    } else {
                        if (initialIndex < content_high.length) {
                            setMessage("Eve", content_high[initialIndex], 2, 404);
                        } else if (initialIndex == content_high.length) {
                            byeMessage();
                        }
                    }


                } else if (variables.index == 4) {
                    if (initialIndex < happy_high.length) {
                        setMessage("Eve", happy_high[initialIndex], 2, 404);
                    } else if (initialIndex == happy_high.length) {
                        byeMessage();
                    }
                } else {
                    initialIndex = 0;
                    setMessage("Eve", "How are you feeling right now?", 2, 1);
                }

            }
        }.start();


    }


    public void cmd_ask3_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_bully_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_someone_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_emotion2_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_yesno1_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_tellthem_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_yesno2_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_stoAsk_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_suggest_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    public void cmd_ask4_Clicked(View view) {
        Button b = (Button) view;
        txt_writte_message.setText(b.getText().toString());
    }

    private void setMessage(String sender, String Body, int isMine, int isASk) {
        if (!Body.equalsIgnoreCase("")) {
            String currentDateandTime = DateFormat.getDateTimeInstance().format(new Date());
            i.add(new ChatMessage(currentDateandTime, sender, Body, "" + random.nextInt(1000), isMine, isASk));
            txt_writte_message.setText("");
            MyAdapter myAdapter = new MyAdapter(this, R.layout.chatbubble, i);
            lv_convo.setAdapter(myAdapter);
        }
    }

    public void cmd_back_Clicked(View view) {
        back();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    private void back() {
        if (t1.isSpeaking()) {
            t1.stop();
            t1.shutdown();
        }
        // startActivity(new Intent(getApplicationContext(), Splash.class));
        overridePendingTransition(0, 0);
        finish();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (speech != null) {
            speech.destroy();
            Log.i(LOG_TAG, "destroy");
        }

    }


    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
        progressBar.setIndeterminate(false);
        progressBar.setMax(10);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        progressBar.setIndeterminate(true);
        toggleButton.setChecked(false);


    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        // returnedText.setText(errorMessage);
        toggleButton.setChecked(false);

    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        toggleButton.setVisibility(View.VISIBLE);
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        txt_writte_message.setText(matches.get(0).toString());
       /* for (String result : matches) {
            try {
                Thread.sleep(200);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }*/

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
        progressBar.setProgress((int) rmsdB);
    }


    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

}
