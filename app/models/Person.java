package models;

import com.sun.jmx.snmp.tasks.Task;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person extends Model {

   public Person(final String name) {
      this.name = name;
   }

   @Id
   public Long id;

   @Constraints.Required
   public String name;

   public String telephoneNumber;

   public String fileAs;

   public String office;

   public String emailAddress;

	public static Finder<Long,Person> find = new Finder(Long.class, Person.class);

   @Override
   public String toString() {
      return name + " (" + fileAs + "), tel " + telephoneNumber + ", office " + office + ", " + emailAddress;
   }
}
