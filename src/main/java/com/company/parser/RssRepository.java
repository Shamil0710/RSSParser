package com.company.parser;

import com.company.Property;
import com.company.abstraction.AbstractRssRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RssRepository extends AbstractRssRepository {
    //TODO Как здесь реализовать конструкцию "try-with-resources"
    public static List<Element> connectAndGet(RSSComponent rssComponent) throws IOException {
        return Jsoup.connect(rssComponent.getuRL()).get().getElementsByTag(Property.ITEM);
    }

    /* Фильтрация и сортировка стримами */
    public static List<RSSElement> sort(List<RSSElement> rssElements, String fieldName) {
        switch (fieldName) {
            case ("publicationDate"): {
                return rssElements = rssElements.stream()
                        .sorted((o1, o2) -> -o1.compareTo(o2))
                        .collect(Collectors.toList());
            }
            case ("url"): {
                return rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getPublicationDate))
                        .collect(Collectors.toList());
            }
            case ("title"): {
                return rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getTitle))
                        .collect(Collectors.toList());
            }
            default: {
                System.out.println("Некорректное поле сортировки");
            }
        }
        return null;
    }

    /**
     * Фильтрация по полям Title и Url
     *
     * @param request   Аргумен по которому происходит фидьтрация
     * @param fieldName Поле в котором должен содержатся аргумент для фильтрации
     */
    public static List<RSSElement> filter(String request, String fieldName, List<RSSElement> rssElements) {
        switch (fieldName) {
            //TODO стоит ли добавить фильтрацию как по полному так и по частичному совпадению или оставить только один?
//          //Фильтрация по полному совпажению
//            case ("title"): {
//               return rssElements = rssElements.stream()
//                        .filter(element -> element.getTitle().equals(request))
//                        .collect(Collectors.toList());
//            }
            //Фильтрация по частичному совпадению
            case (Property.TITLE): {
               return rssElements = rssElements.stream()
                        .filter(element -> element.getTitle().contains(request))
                        .collect(Collectors.toList());

            }
            case (Property.LINK): {
               return rssElements = rssElements.stream()
                        .filter(element -> element.getUrl().contains(request))
                        .collect(Collectors.toList());
            }
            default: {
                System.out.println("Некорректное поле фильтрации");
            }
        }
        return rssElements;
    }
//    //Филтрация по DataTime
//    public void filterByDateAndTime(String request, String dataFormat) {
//        rssElements = rssElements.stream()
//                .filter(rssElements -> rssElements.getPublicationDate().equals(parseDate(request, dataFormat)))
//                .collect(Collectors.toList());
//    }
//
//    public void filterByDate(String request, String dataFormat) {
//        rssElements = rssElements.stream()
//                .filter(rssElements -> rssElements.getPublicationDate().toLocalDate().equals(parseDate(request, dataFormat).toLocalDate()))
//                .collect(Collectors.toList());
//    }
}


