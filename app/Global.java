import models.Person;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.api.libs.Files;

import java.io.File;

/**
 * Load test data
 */
public class Global extends GlobalSettings {

   /**
    * Load test data from a file of names, with one name per line.
    */
   private void loadTestData() {
      final String data = (String) Files.readFile(new File("conf/test-data.txt"));
      for (String name : data.split("\n")) {
         if (!name.startsWith("#")) {
            // Martin Odersky 0123 456789, Odersky, 2.13, Odersky@example.org
            final Person person = new Person(name);
            person.telephoneNumber = "0" + number(3) + " " + number(6);
            person.fileAs = name.substring(name.lastIndexOf(" ") + 1);
            person.office = number(1) + "." + number(2);
            person.emailAddress = name.replaceAll(" ", ".").toLowerCase() + "@example.org";
            System.out.println(person);
            person.save();
         }
      }
   }

   /**
    * Return a random number with the given number of digits.
    */
   private static String number(final Integer digits) {
      final long limit = new Double(Math.pow(10.0, digits.doubleValue())).longValue();
      return String.format("%0" + digits + ".0f", Math.ceil(Math.random() * (limit - 1)));
   }

   @Override
   public void onStart(Application app) {
      if (Person.find.findRowCount() == 0) {
       loadTestData();
      }
   }

}
