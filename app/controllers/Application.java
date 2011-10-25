package controllers;

import com.avaje.ebean.Page;
import com.sun.jmx.snmp.tasks.Task;
import models.Person;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import views.html.helper.form;
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
    * Edit one entry.
    */
	public static Result edit(final Long id) {
		final Person person = Person.find.byId(id);
		return person == null ? notFound("Invalid ID") : ok(edit.render(person, form(Person.class).fill(person)));
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

   /**
    * Update one entry.
    */
	public static Result update(final Long id) {
      final Form<Person> form = form(Person.class).bindFromRequest();
      if(form.hasErrors()) {
         Logger.info("errors");
         final Person person = Person.find.byId(id);
          return badRequest(edit.render(person, form));
      } else {
         Logger.info("update");
          form.get().update(id);
          return redirect(routes.Application.index(1, ""));
      }
	}

}
