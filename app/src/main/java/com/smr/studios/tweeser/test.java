package com.smr.studios.tweeser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        Document document = null;
        List<Data> hrefLinks = new ArrayList<>();
        int count = 1;
        while (count <= 200) {
            try {
                String url = "https://ca.flixable.com/title/80182216";
                document = Jsoup.connect(url).get();
                Data info = new Data();
                Elements table = document.getElementsByTag("meta");
                for (Element link : table) {
                    if (link.attr("property").equals("og:description"))
                        info.setDesc(link.attr("content"));
                    if (link.attr("property").equals("og:url"))
                        info.setPicLink(link.attr("content"));
                }

                Elements tablea = document.getElementsByTag("a");
                for (Element link : tablea) {
                    if (link.attr("href").contains("/genre/"))
                        info.setGenre(link.text());
                }
                info.setId("id");

                hrefLinks.add(info);

            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
 /*           try {
                File file = new File("C:\\Users\\Sabir\\Desktop\\Tamina\\NetFlixDataCanada.txt");
                FileWriter fr = new FileWriter(file, true);
                BufferedWriter br = new BufferedWriter(fr);
                PrintWriter pr = new PrintWriter(br);
                for (Map.Entry<String, String> sabir : filteredData.entrySet()) {
                    String code = sabir.getKey().replace("/", "").replace("title", "");
                    pr.println(code + "|" + sabir.getValue() + "|");
                }
                pr.close();
                br.close();
                fr.close();
            } catch (Exception e) {
            }
            count ++;
            System.out.println(count);*/
        }

    }
}
