package com.example.uimockupdesign;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Patterns;
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
				+ "\n\n The @browser will remove extra spaces and extra #lines when the page is #displayed. "
				+ "\n\n  Any number of lines count #9o0-9023jdlas-_ "
				+ "\n\n  #Displays text to the @user and optionally allows them to @edit it. "
				+ "\n\n       A @TextView is a complete text editor, however the basic class is #configured to not allow editing; "
				+ "\n\n       see @EditText for a          subclass that #configures the text view for #editing. ";

		Pattern mentionPattern = Pattern.compile("(@[A-Za-z0-9_-]+)");
		Pattern hashtagPattern = Pattern.compile("(#[A-Za-z0-9_-]+)");
		Pattern urlPattern = Patterns.WEB_URL;
		Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Pattern newLinePattern = Pattern.compile("(?m)^.*$");

		StringBuffer sb = new StringBuffer(a.length());
		Matcher nl = newLinePattern.matcher(a);
		
		while (nl.find()) {
		    nl.appendReplacement(sb, nl.group() + "<br>");
		}
		nl.appendTail(sb);

		Matcher o = hashtagPattern.matcher(sb.toString());
		sb = new StringBuffer(sb.length());
		
		while (o.find()) {
		    o.appendReplacement(sb, "<strong><font color=\"#673ab7\">" + o.group(1) + "</font></strong>");
		}
		o.appendTail(sb);

		Matcher n = mentionPattern.matcher(sb.toString());
		sb = new StringBuffer(sb.length());

		while (n.find()) {
		    n.appendReplacement(sb, "<strong><font color=\"#00695c\">" + n.group(1) + "</font></strong>");
		}
		n.appendTail(sb);

		Matcher m = urlPattern.matcher(sb.toString());
		sb = new StringBuffer(sb.length());

		while (m.find()) {
		    m.appendReplacement(sb, "<strong><font color=\"#0277bd\"><a href =" + m.group(1) +"\">" + m.group(1) + "</a></font></strong>");
		}
		m.appendTail(sb);
		
		Matcher e =  emailPattern.matcher(sb.toString());
		sb = new StringBuffer(sb.length());
		
		while(e.find()) {
			e.appendReplacement(sb, "<strong><font color=\"#0277bd\">"+e.group(1)+"</font></strong>");
		}
		e.appendTail(sb);

		text.setText(Html.fromHtml(sb.toString()));
	}

}
