import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bizportalServlet")
public class BizportalServlet extends HttpServlet {

    private static final long serialVersionUID = -7060758261496829905L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>bizportalServlet.java:doGet(): Servlet code!</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>This is a simple java servlet.</h1>");
            String sURL = "https://www.quandl.com/api/v3/datasets/YAHOO/TA_BIG.json?auth_token=-xM-scKsXUTvTspiprdx"; //just a string

            // Connect to the URL using java's native library
            URL url = new URL(sURL);
            HttpURLConnection aaa = (HttpURLConnection) url.openConnection();
            aaa.connect();

            // Convert to a JSON object to print data
       //     JsonParser jp = new JsonParser(); //from gson
     //       JsonElement root = jp.parse(new InputStreamReader((InputStream) aaa.getContent())); //Convert the input stream to a json element
   //         JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.

            InputStream in = new URL(sURL).openStream();
            JSONObject json = new JSONObject(IOUtils.toString(in, Charset.forName("UTF-8").displayName()));
 //           Iterator<?> keys = json.keys();

            for(Iterator iterator = json.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                int start = 34;
                int end = start + 47;
                String[] split = json.get(key).toString().split("],");
                writer.println("<h4>" + split[0].substring(start, end)+ "</h4>");

                for (int i = 1; i < split.length; i++)
                {
                    writer.println("<h4>" + split[i].substring(1)+ "</h4>");
                }
                /*while (start < 500)
                {
                    writer.println("<h4>" + json.get(key).toString().substring(start, end) + "</h4></br>");
                    start += 50;
                    end += 50;
                }
                */
            }


            writer.println("</body>");
            writer.println("</html>");
        }
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    }
}
