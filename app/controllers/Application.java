package controllers;

import com.avaje.ebean.Page;
import models.Person;
import play.core.logger.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.details;
import views.html.index;
import views.html.indexList;

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
	public static Result index(final int pageNumber, final String search) {
      if (pageNumber < 1) {
         return badRequest();
      }

      final Page<Person> page = Person.page(pageNumber - 1, PAGE_SIZE, search);
      return ok(index.render(page, search));
	}

   public static Result list(final int pageNumber, final String search) {
      if (pageNumber < 1) {
         return badRequest();
      }

      final Page<Person> page = Person.page(pageNumber - 1, PAGE_SIZE, search);
      return ok(indexList.render(page, search));
   }

}
