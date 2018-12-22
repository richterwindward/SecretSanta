import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class Mailer {

    static void mail(String to, String subject, String message) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();

        String toEncoded = URLEncoder.encode(to, "UTF-8");
        String subjectEncoded = URLEncoder.encode(subject, "UTF-8");
        String messageEncoded = URLEncoder.encode(message, "UTF-8");

        String uriString = String.format("mailto:%s?subject=%s&body=%s", toEncoded, subjectEncoded, messageEncoded);

        desktop.mail(new URI(uriString));
    }

}
