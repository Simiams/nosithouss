package fr.arosaje.nosithouss;

import fr.arosaje.nosithouss.services.TrefleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
        int limit = Arrays.stream(args)
                .filter(arg -> arg.startsWith("-limit="))
                .findFirst()
                .map(arg -> Integer.parseInt(arg.substring("-limit=".length())))
                .orElse(defaultEtlLimit);
        if (ETL)
            trefleService.savePlants(limit);
    }
}
