package rest.versioning;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	@GetMapping("v1/person")  //called uri versioning.used by twitter
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("v2/person") //called uri versioning.advantage is caching,easy to browse-no technical knowledge required,documentation is easy.
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(value = "/person/param", params = "version=1")//called request-parameter versioning.advantage is caching,easy to browse-no technical knowledge required,documentation is easy.
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");//used by amazon
	}

	@GetMapping(value = "/person/param", params = "version=2")//called request-parameter versioning.
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=1") //called header versioning.advantage-polluting less uri space.disadvantage-misuse of http headers,required a technical knowledge to request data.
	public PersonV1 headerV1() {			//used by microsoft
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(value = "/person/header", headers = "X-API-VERSION=2")//called header versioning.
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")//called media type versioning or accept header or content negotiation.
	public PersonV1 producesV1() {				//used by github
		return new PersonV1("Bob Charlie");//advantage-polluting less uri space.disadvantage-misuse of http headers,required a technical knowledge to request data.
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")//called media type versioning or accept header or content negotiation.
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

}