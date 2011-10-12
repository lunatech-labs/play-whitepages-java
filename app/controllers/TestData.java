package controllers;

import models.Person;
import play.api.libs.Files;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.File;

/**
 * Manage test data
 */
public class TestData extends Controller {

   /**
    * Load test data from a file of names, with one name per line.
    */
   public static Result load() {
      System.out.println("load");
      final String data = (String) Files.readFile(new File("conf/test-data.txt"));
      for(String name : data.split("\n")) {
         // Martin Odersky 0123 456789, Odersky, 2.13, Odersky@example.org
         final Person person = new Person(name);
         person.telephoneNumber = "0" + number(3) + " " + number(6);
         person.fileAs = name.substring(name.lastIndexOf(" ") + 1);
         person.office = number(1) + "." + number(2);
         person.emailAddress = name.replaceAll(" ", ".").toLowerCase() + "@example.org";
         System.out.println(person);
         person.save();
      }

      return redirect(routes.Application.index());
   }

   /**
    * Return a random number with the given number of digits.
    */
   private static String number(final Integer digits) {
      final long limit = new Double(Math.pow(10.0, digits.doubleValue())).longValue();
      return String.format("%0" + digits + ".0f", Math.ceil(Math.random() * (limit - 1)));
   }
}
