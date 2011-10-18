package controllers;

import com.avaje.ebean.Page;
import models.Person;
import play.core.logger.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.details;
import views.html.index;

/**
 * Handles HTTP requests.
 */
public class Application extends Controller {

   private final static int PAGE_SIZE = 50;

   /**
    * Render one entry.
    */
	public static Result details(final Long id) {
		final Person person = Person.find.byId(id);
		return person == null ? notFound("Invalid ID") : ok(details.render(person));
	}

   /**
    * Render one page of results.
    */
	public static Result index(final int pageNumber) {
      Logger.log(String.format("page %d", pageNumber));
      if (pageNumber < 0) {
         return badRequest();
      }

      final Page<Person> page = Person.page(pageNumber, PAGE_SIZE);
      return ok(index.render(page));
	}

}
