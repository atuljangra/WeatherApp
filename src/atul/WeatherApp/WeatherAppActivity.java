package atul.WeatherApp;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherAppActivity extends Activity {
	public  class getdata extends AsyncTask<String , Void, String>{

		@Override
		protected String  doInBackground(String... params) {
			
			try{
			Log.d("URL", params[0]);
			URL website = new URL(params[0]);
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			HandlingXMLStuff doingWork = new HandlingXMLStuff();
			xr.setContentHandler(doingWork);
			xr.parse(new InputSource(website.openStream()));
			String information = doingWork.getInfo();

			return information;
			}catch (Exception e) {
				return "Error"+e ;
				// TODO: handle exception
			}
			
		}

		@Override
		protected  void onPostExecute(String result) {
			tv.setText(result);
		}
		
		
	}
    TextView tv ;
    EditText city,state;
    static final String BaseURL = "http://www.google.com/ig/api?weather=";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        city = (EditText)findViewById(R.id.City);
        state = (EditText)findViewById(R.id.State);
        tv=(TextView)findViewById(R.id.Weather);
//        Button b= (Button)findViewById(R.id.button);
//       b.setOnClickListener(this);
    }

	public void Calc(View v) {
		String c = city.getText().toString();
		String s = city.getText().toString();
		StringBuilder URL = new StringBuilder(BaseURL);
		String finalUrl = URL.append(c+","+s).toString();
		getdata test = new getdata();
		try {
			test.execute(finalUrl);
			
		} catch (Exception e) {
			city.setText("Error"+e);
			
		}
	}
}