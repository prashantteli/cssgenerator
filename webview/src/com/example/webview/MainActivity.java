package com.example.webview;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
 
@SuppressLint("JavascriptInterface")
public class MainActivity extends Activity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //WebView Object
        WebView browser;
        browser=(WebView)findViewById(R.id.webkit);
        //Enable Javascript
        browser.getSettings().setJavaScriptEnabled(true);
        //Inject WebAppInterface methods into Web page by having Interface name 'Android' 
        browser.addJavascriptInterface(new WebAppInterface(this), "Android");
        //Load URL inside WebView
        browser.loadUrl("file:///android_asset/index.html");
    }
   
    //Class to be injected in Web page
    public class WebAppInterface {
        Context mContext;
 
        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }
 
        /**
         * Show Toast Message
         * @param toast
         */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
 
        /**
         * Show Dialog 
         * @param dialogMsg
         */
        @JavascriptInterface
        public void showDialog(String dialogMsg){
            AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
 
            // Setting Dialog Title
            alertDialog.setTitle("JS triggered Dialog");
 
            // Setting Dialog Message
            alertDialog.setMessage(dialogMsg);
 
            // Setting alert dialog icon
            //alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
 
            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(mContext, "Dialog dismissed!", Toast.LENGTH_SHORT).show();
                }
            });
 
            // Showing Alert Message
            alertDialog.show();
        }
 
        /**
         * Intent - Move to next screen
         */
    }
}