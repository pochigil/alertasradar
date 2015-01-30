package gcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import static javax.swing.JOptionPane.showMessageDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

	private final String USER_AGENT = "Mozilla/5.0";
	static String response = null;

        private static final String TAG_USUARIO = "usuario";
        private static final String TAG_PASSWORD = "password";
        
	private static final String TAG_USUARIOS = "usuarios";
	private static final String TAG_REGID = "regid";

	JSONArray jsonIds = null;
	ArrayList<String> arrayId, arrayUsuarios;
	
	public ArrayList<String> ReadRegIDs() throws IOException, ServletException {

		String url = "http://radarmendoza.net63.net/ReadPHP.php";
		arrayId = new ArrayList<String>();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		String jsonStr = response.toString();

		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);

				// Getting JSON Array node
				jsonIds = jsonObj.getJSONArray(TAG_USUARIOS);
				
				// looping through All Users
				for (int i = 0; i < jsonIds.length(); i++) {
					JSONObject c = jsonIds.getJSONObject(i);
					String regid = c.getString(TAG_REGID);
					arrayId.add(regid);
				}
			} catch (JSONException e) {
				System.out.println(e);
                                showMessageDialog(null, e.toString());
			}
		} else {
			System.out.println("Ningun usuario registrado");
		}
               
		return arrayId;
	}
        
        public ArrayList<String> ReadUsers() throws IOException, ServletException {

		String url = "http://radarmendoza.net63.net/ReadUsers.php";
		arrayUsuarios = new ArrayList<String>();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		String jsonStr = response.toString();

		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);

				// Getting JSON Array node
				jsonIds = jsonObj.getJSONArray(TAG_USUARIOS);
				
				// looping through All Users
				for (int i = 0; i < jsonIds.length(); i++) {
					JSONObject c = jsonIds.getJSONObject(i);
					String usuario = c.getString(TAG_USUARIO);
                                        String password = c.getString(TAG_PASSWORD);
					arrayUsuarios.add(usuario+","+password);
				}
			} catch (JSONException e) {
				System.out.println(e);
                                showMessageDialog(null, e.toString());
			}
		} else {
			System.out.println("Ningun usuario registrado");
		}
               
		return arrayUsuarios;
	}

}
