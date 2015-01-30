package gcm;

/**
 *
 * @author Po
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

/**
 * Servlet implementation class GCMBroadcast
 */
@WebServlet("/GCMBroadcast")
public class GCMBroadcast extends HttpServlet {

    private static final long serialVersionUID = 1L;

	// The SENDER_ID here is the "Browser Key" that was generated when I
    // created the API keys for my Google APIs project.
    private static final String SENDER_ID = "AIzaSyB-mamLiHopTf7o8vNRNIuqf0_AP1VIxfo";

	// This is a *cheat* It is a hard-coded registration ID from an Android
    // device
    // that registered itself with GCM using the same project id shown above.
    //private static final String ANDROID_DEVICE = "APA91bHycBJL-e1h8NzxXtdEnfP3J3LwZzlG9JsitSyJWovxcbgVEs8nTZeJxanRv0m-vWL11J-oh_RcGZxJs66AJrJVg1gVrECrqk4_u3q6lEqLkSlSu-yggfS_lnatNh4MK6fSrP0UQps0MC8GNntxA180FMewCYzaRXnO8c-zAkn9a3NcDts";
	// This array will hold all the registration ids used to broadcast a
    // message.
    // for this demo, it will only have the ANDROID_DEVICE id that was captured
    // when we ran the Android client app through Eclipse.
    private final List<String> androidTargets;

    /**
     * @throws IOException
     * @throws javax.servlet.ServletException
     * @see HttpServlet#HttpServlet()
     */
    public GCMBroadcast() throws IOException, ServletException {

        super();

        JsonParser json = new JsonParser();
        androidTargets = json.ReadRegIDs();
        
    }

	// This doPost() method is called from the form in our index.jsp file.
    // It will broadcast the passed "Message" value.

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
        @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String collapseKey = "GCM Broadcast";
        String tipo = "";
        String zona = "";
        String intensidad = "";
        String mensaje = "";

        try {
            tipo = request.getParameter("tipo");
            zona = request.getParameter("zona");
            intensidad = request.getParameter("intensidad");
            mensaje = request.getParameter("mensaje");
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        Sender sender = new Sender(SENDER_ID);

		// This Message object will hold the data that is being transmitted
        // to the Android client devices. For this demo, it is a simple text
        // string, but could certainly be a JSON object.
        Message message = new Message.Builder()
                // If multiple messages are sent using the same .collapseKey()
                // the android target device, if it was offline during earlier
                // message
                // transmissions, will only receive the latest message for that
                // key when
                // it goes back on-line.
                .collapseKey(collapseKey).timeToLive(30).delayWhileIdle(true)
                .addData("tipo", tipo)
                .addData("zona", zona)
                .addData("intensidad", intensidad)
                .addData("mensaje", mensaje).build();

        try {
			// use this for multicast messages. The second parameter
            // of sender.send() will need to be an array of register ids.
            MulticastResult result = sender.send(message, androidTargets, 1);

            if (result.getResults() != null) {
                int canonicalRegId = result.getCanonicalIds();
                if (canonicalRegId != 0) {

                }
            } else {
                int error = result.getFailure();
                System.out.println("Broadcast failure: " + error);
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        // We'll pass the values back to index.jsp, only
        // so
        // we can display it in our form again.
        request.setAttribute("tipo", tipo);
        request.setAttribute("zona", zona);
        request.setAttribute("intensidad", intensidad);
        request.setAttribute("mensaje", mensaje);
        
        request.getRequestDispatcher("alerta.jsp").forward(request, response);

    }

}
