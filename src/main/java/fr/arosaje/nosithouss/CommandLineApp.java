package fr.arosaje.nosithouss;

import fr.arosaje.nosithouss.services.TrefleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnNotWebApplication
@Setter
public class CommandLineApp implements CommandLineRunner {
    private final TrefleService trefleService;

    @Value("${spring.main.etl}")
    private Boolean ETL;

    public CommandLineApp(TrefleService trefleService) {this.trefleService = trefleService;}

    @Override
    public void run(String... args) throws Exception {
        if (ETL)
            trefleService.savePlants();
    }
}
