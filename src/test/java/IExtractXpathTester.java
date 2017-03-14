import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.XPatherException;
import org.xml.sax.SAXException;

import com.asking.extract.IExtract;
import com.asking.extract.constant.Constant;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;

public class IExtractXpathTester {

	public static void main(String[] args) throws ClientProtocolException, IOException, XPathExpressionException,
			SAXException, ParserConfigurationException, XPatherException, XpathSyntaxErrorException {
		String charset = "UTF-8";

		CloseableHttpClient client = HttpClients.custom().build();

		HttpGet get = new HttpGet("https://item.jd.com/11183230975.html");

		CloseableHttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String html = null;
		if (entity != null) {
			html = EntityUtils.toString(entity, charset);
			String outResult = IExtract
					.extract(html, Constant.EXTRACT_MODE_XPATH, "//div[@class='p-parameter']/allText()").toString();
			System.out.println(outResult);
		}
	}
}
