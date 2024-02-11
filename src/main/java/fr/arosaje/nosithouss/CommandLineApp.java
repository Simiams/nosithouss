package fr.arosaje.nosithouss;

import fr.arosaje.nosithouss.services.TrefleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ConditionalOnNotWebApplication
@Setter
public class CommandLineApp implements CommandLineRunner {
    private final TrefleService trefleService;

    @Value("${spring.main.etl}")
    private Boolean ETL;
    private int defaultEtlLimit = 0; //todo env
    public CommandLineApp(TrefleService trefleService) {this.trefleService = trefleService;}

    @Override
    public void run(String... args) throws Exception {
        if (ETL) {
            AtomicInteger limit = new AtomicInteger(defaultEtlLimit);
            Arrays.stream(args).filter(arg -> arg.matches("-limit=\\d*$")).findFirst().ifPresent(arg -> limit.set(Integer.parseInt(arg.substring("-limit=".length()))));
            trefleService.savePlants(limit.get() < 0 ? defaultEtlLimit : limit.get());
        }
    }
}
