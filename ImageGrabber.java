import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import com.google.gson.Gson;


public class ImageGrabber {
	
	private GoogleResults results;
	String SearchQuery;
	int currentPage;
	
	public ImageGrabber(String SearchQuery){
		//	Constructor .
		this.SearchQuery = SearchQuery;
	}
	
	public Image get (int number){
		int pageNumber = number/4;
		if(currentPage!=pageNumber || results == null){getPage(pageNumber); currentPage = pageNumber;}
		try {
			//System.out.println(results.getResponseData().getResults().get(number%4).getUrl()+"=="+number%4);
			return ImageIO.read(new URL(results.getResponseData().getResults().get(number%4).getUrl()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			get(number+1);
		}
		return null;
	}
	
	private void getPage(int pageNumber) {
		// TODO Auto-generated method stub
		String address = "http://ajax.googleapis.com/ajax/services/search/images?v=2.0&start="+pageNumber+"&q=";
		String charset = "UTF-8";
		URL url;
		try {
			url = new URL(address + URLEncoder.encode(SearchQuery, "UTF-8"));
			Reader is = new InputStreamReader(url.openStream(), charset);
			
//			URLConnection urlConnection = url.openConnection();
//			urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0");
//			Reader is = new InputStreamReader(urlConnection.getInputStream(),charset);
			
			//Reader reader = is;
			results = new Gson().fromJson(is, GoogleResults.class);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		ImageGrabber ig = new ImageGrabber("google");
//		page p1 = new page();
//		for (int i = 100; i < 120; i++) {
//			// TODO Auto-generated method stub
//			p1.add(ig.get(i));
//			System.out.println("got"+i);
//		}
//	}

}
