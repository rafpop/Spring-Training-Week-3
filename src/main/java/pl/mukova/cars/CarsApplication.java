package pl.mukova.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
    }

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /*

    Tydzień 3

    Zadanie podstawowe:
Napisz REST API dla listy pojazdów. Pojazd będzie miał pola: id, mark, model, color.
API które będzie obsługiwało metody webowe:

    do pobierania wszystkich pozycji
    do pobierania elementu po jego id
    do pobierania elementów w określonym kolorze (query)
    do dodawania pozycji
    do modyfikowania pozycji
    do modyfikowania jednego z pól pozycji
    do usuwania jeden pozycji

Przy starcie aplikacji mają dodawać się 3 pozycje.

—————————

Dla ambitnych:

    rozbuduj aplikacje o możliwość zwracania danych w postaci XML
    dodaj obsługę Swgger UI
    zaimplementuj HATEOAS

Tydzień 4:

    Rozbuduj swoją aplikacje z poprzedniego tygodnia o interfejs graficzny, który umożliwi:

    wyświetlanie wszystkich pozycji
    pobieranie elementu po jego id
    dodawanie pozycji
    modyfikowanie pozycji
    modyfikowanie jednego z pól pozycji
    usuwania jednej pozycji */

}
