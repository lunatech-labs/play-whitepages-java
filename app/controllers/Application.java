package controllers;

import models.Person;
import play.mvc.*;
import views.html.*;

import java.util.List;

public class Application extends Controller {

	public static Result details(final Long id) {
		final Person person = Person.find.byId(id);
		return person == null ? notFound("Invalid ID") : ok(details.render(person));
	}

	public static Result index() {
        final List<Person> list = Person.find.query().order("fileAs").findList();
        return ok(index.render(list));
	}

}
