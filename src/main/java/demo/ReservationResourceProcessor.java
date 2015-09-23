package demo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReservationResourceProcessor implements ResourceProcessor<Resource<Reservation>> {

	@Override
	public Resource<Reservation> process(Resource<Reservation> resource) {
		Reservation reservation = resource.getContent();
		
		Link link = new Link("http://some-account.com/profile/" + reservation.getName() + "/");
		resource.add(link);
		return resource;
	}
	
}