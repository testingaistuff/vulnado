package com.scalesec.vulnado;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.logging.Logger;
import java.net.*;

private LinkLister() {}
  private LinkLister() {}
public class LinkLister {
  public static List<String> getLinks(String url) throws IOException {
    List<String> result = new ArrayList<>();
    Document doc = Jsoup.connect(url).get();
    Elements links = doc.select("a");
    for (Element link : links) {
      result.add(link.absUrl("href"));
    }
    return result;
  }

  public static List<String> getLinksV2(String url) throws BadRequest {
    try {
      URL aUrl= new URL(url);
      String host = aUrl.getHost();
      logger.info(host);