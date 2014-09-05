package com.example.uimockupdesign;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class TextviewPattern extends Activity {

	private TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_textview_pattern);
			
		text =(TextView)findViewById(R.id.text);
		
		String a = "This is a #sample #twitter text of @tom_cruise with a link  http://www.google.com \n"
				+ "\n The @browser will remove extra spaces and extra #lines when the page is #displayed. Any number of lines count"
				+ "\n\n As #one line, and any number of spaces @count as one #space. \n\n disfrutarz@gmail.com"
				+ "\n\n  The @browser will remove extra spaces and extra #lines when the page is #displayed. "
				+ "\n\nAny number of lines count #9o0-9023jdlas-_ "
				+ "\n\n  #Displays text to the @user and opt"
				+ "ionally allows them to @edit it. "
				+ "\n\n       A @TextView is a complete text editor, however the basic class is #configured to not allow editing "
				+ "\n\n       see @EditText for a          subclass that #configures the text view for #editing. ";	
		 
		SpannableStringBuilder spannable = new SpannableStringBuilder( a );
		
		Pattern mentionPattern = Pattern.compile("(@[A-Za-z0-9_-]+)");
		Pattern hashtagPattern = Pattern.compile("(#[A-Za-z0-9_-]+)");

		Matcher o = hashtagPattern.matcher(spannable);	
		
		while (o.find()) {
		   
			spannable.setSpan(new StyleSpan(Typeface.BOLD), o.start(), o.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		    spannable.setSpan(new ForegroundColorSpan(Color.rgb(103, 58,  183)), o.start(), o.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
	
		Matcher n = mentionPattern.matcher(spannable);

		while (n.find()) {
			 spannable.setSpan(new StyleSpan(Typeface.BOLD), n.start(), n.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			 spannable.setSpan(new ForegroundColorSpan(Color.rgb(0, 105,  92)), n.start(), n.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

		text.setText(spannable);
		
	}

}
