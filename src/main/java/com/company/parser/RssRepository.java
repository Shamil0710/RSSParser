package com.company.parser;

import com.company.Property;
import com.company.abstraction.AbstractRssRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RssRepository extends AbstractRssRepository {
    //TODO Как здесь реализовать конструкцию "try-with-resources"
    public static List<Element> connectAndGet(RSSComponent rssComponent) throws IOException {
        return Jsoup.connect(rssComponent.getuRL()).get().getElementsByTag(Property.ITEM);
    }

    /* Фильтрация и сортировка стримами */
    public List<RSSElement> sort(List<RSSElement> rssElements, String fieldName) {
        switch (fieldName) {
            case ("publicationDate"): {
                rssElements = rssElements.stream()
                        .sorted((o1, o2) -> -o1.compareTo(o2))
                        .collect(Collectors.toList());
                break;

            }
            case ("url"): {
                rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getPublicationDate))
                        .collect(Collectors.toList());
                break;

            }
            case ("title"): {
                rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getTitle))
                        .collect(Collectors.toList());
                break;
            }
            default: {
                System.out.println("Некорректное поле сортировки");
            }
        }
    }

    /**
     * Фильтрация по полям Title и Url
     *
     * @param request   Аргумен по которому происходит фидьтрация
     * @param fieldName Поле в котором должен содержатся аргумент для фильтрации
     */

    public void filter(String request, String fieldName) {
        switch (fieldName) {

            //TODO стоит ли добавить фильтрацию как по полному так и по частичному совпадению или оставить только один?

            //Фильтрация по полному совпажению

//            case ("title"): {
//
//                rssElements = rssElements.stream()
//                        .filter(rssElements -> rssElements.getTitle().equals(request))
//                        .collect(Collectors.toList());
//
//                break;
//
//            }

            //Фильтрация по частичному совпадению
            case ("title"): {
                rssElements = rssElements.stream()
                        .filter(rssElements -> rssElements.getTitle().contains(request))
                        .collect(Collectors.toList());
                break;

            }
            case ("url"): {
                rssElements = rssElements.stream()
                        .filter(rssElements -> rssElements.getUrl().contains(request))
                        .collect(Collectors.toList());
                break;
            }

            default: {
                System.out.println("Некорректное поле фильтрации");
            }
        }
    }

    //Филтрация по DataTime
    public void filterByDateAndTime(String request, String dataFormat) {
        rssElements = rssElements.stream()
                .filter(rssElements -> rssElements.getPublicationDate().equals(parseDate(request, dataFormat)))
                .collect(Collectors.toList());
    }

    public void filterByDate(String request, String dataFormat) {
        rssElements = rssElements.stream()
                .filter(rssElements -> rssElements.getPublicationDate().toLocalDate().equals(parseDate(request, dataFormat).toLocalDate()))
                .collect(Collectors.toList());
    }

    private static java.io.InputStream readOfFile(String directory) {
        try {
            InputStream inputStream;
            return new BufferedInputStream(Files.newInputStream(Path.of(directory)));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return null;
    }
}
