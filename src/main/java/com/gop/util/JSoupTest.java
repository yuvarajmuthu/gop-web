package com.gop.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

public class JSoupTest {

	public static List<String> getStringsFromUrl(String url, String cssQuery) throws IOException {
        Document document = Jsoup.parse(url);
        Elements elements = StringUtil.isBlank(cssQuery)
                ? document.getElementsByTag("script")
                : document.select(cssQuery);
        List<String> strings = new ArrayList<String>();
        elements.traverse(new TextNodeExtractor(strings));
        return strings;
    }

    private static class TextNodeExtractor implements NodeVisitor {
        private final List<String> strings;

        public TextNodeExtractor(List<String> strings) {
            this.strings = strings;
        }

        @Override
        public void head(Node node, int depth) {
            if (node instanceof DataNode) {
            	DataNode textNode = ((DataNode) node);
                String text = textNode.getWholeData();
                if (!StringUtil.isBlank(text)) {
                    strings.add(text);
                }
            }
        }

        @Override
        public void tail(Node node, int depth) {}
    }
}
