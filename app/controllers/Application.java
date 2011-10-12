package controllers;

import models.Person;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	public static Result details(final Long id) {
		final Person person = Person.find.byId(id);
		return person == null ? notFound("Invalid ID") : ok(details.render(person));
	}

	public static Result index() {
		return ok(index.render(Person.find.all()));
	}

}
