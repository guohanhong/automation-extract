package com.asking.extract;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.XPatherException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.asking.extract.constant.Constant;
import com.google.common.collect.Lists;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;

//http://www.cnblogs.com/kavlez/p/4049210.html  xpath

public class IExtract {

	private static DocumentBuilderFactory factory = null;

	private static HtmlCleaner htmlCleaner = null;

	static {
		factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		htmlCleaner = new HtmlCleaner();
	}

	public static List<String> css(String html, String css) {
		List<String> extract = Lists.newArrayList();

		org.jsoup.nodes.Document document = Jsoup.parse(html);
		Elements elements = document.select(css);
		for (Element value : elements) {
			extract.add(value.text());
		}

		return extract;
	}

	public static List<String> xpath(String html, String xpath) throws XpathSyntaxErrorException {
		List<String> extract = Lists.<String> newArrayList();

		JXDocument document = new JXDocument(html);
		List<Object> values = document.sel(xpath);

		for (Object value : values) {
			extract.add(value.toString());
		}

		return extract;
	}

	public static List<String> extract(String html, String mode, String place) throws XPathExpressionException,
			IOException, SAXException, ParserConfigurationException, XPatherException, XpathSyntaxErrorException {
		List<String> extract = Lists.<String> newArrayList();
		switch (mode) {
		case Constant.EXTRACT_MODE_CSS: {
			extract = css(html, place);
			break;
		}
		case Constant.EXTRACT_MODE_XPATH: {
			extract = xpath(html, place);
			break;
		}
		}
		return extract;
	}
}
