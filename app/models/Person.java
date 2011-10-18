package models;

import com.avaje.ebean.Page;
import com.sun.jmx.snmp.tasks.Task;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.SortedSet;

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

   public static Finder<Long, Person> find = new Finder(Long.class, Person.class);

   public static Page<Person> page(final int pageNumber, final int pageSize) {
      return find.query().order("fileAs").findPagingList(pageSize).getPage(pageNumber);
   }

   @Override
   public String toString() {
      return name + " (" + fileAs + "), tel " + telephoneNumber + ", office " + office + ", " + emailAddress;
   }
}
