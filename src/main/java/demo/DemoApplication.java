package demo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

@SpringBootApplication
public class DemoApplication {

	@Autowired 
	ReservationResourceProcessor resourceProcessor;
	
	@Bean
	GraphiteReporter graphiteReport(MetricRegistry registry, 
					@Value("${graphite.host}") String host, 
					@Value("${graphite.port}") int port) {
		GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
				.prefixedWith("reservations")
				.build(new Graphite(host, port));
		
		reporter.start(2, TimeUnit.SECONDS);
		return reporter;
	}
	
    @Bean
    CommandLineRunner runner(ReservationRepository rr) {
        return args -> {
            rr.deleteAll();

            Arrays.asList("Sjaak, Piet, Henk, Kees, Jan".split(","))
                    .forEach(x -> rr.save(new Reservation(x)));
            rr.findAll().forEach(System.out::println);
        };
    }
	
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
