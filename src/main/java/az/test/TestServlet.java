package az.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("test get 6 novembre");
		// sendGET("http://10.0.0.9:8081/");
		try {
			String result = sendGET("https://www.google.com");
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(result);
		} catch (URISyntaxException | InterruptedException e) {
			throw new ServletException(e);
		}
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("init");
	}

	private static String sendGET(String url) throws IOException, URISyntaxException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.google.com")).GET().build();
		HttpClient client = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_2)
				.build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		return response.body();
	}

}
