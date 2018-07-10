package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
/*
    1. Dzięki pakietowaniu po funkcjonalnościach - nic nie musi być publiczne.
    2. De facto nie powinniśmy opierać się bezpośrednio na encjach - tylko na obiektach Dto :
    https://stackoverflow.com/questions/39397147/java-difference-between-entity-and-dto
    Jednak w przypadku tak małego projektu nie ma to sensu.
    3. Poczytaj o stremach w javie.
    4. Testy pakietujemy tak samo jak aplikację - i odpowiednio je piszemy.
    5. Pakiet runner nie ma sensu - robimy data loader per pakiet.
    6. Spring nie wymaga korzystania z adnotacji Autowired (nie dotyczy testów), wystarczy adnotacja
    @RequiredArgsConstructor i finalne pola w klasie.
    7.
 */
