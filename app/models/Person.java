package models;

import com.avaje.ebean.Page;
import com.avaje.ebean.Query;
import com.sun.jmx.snmp.tasks.Task;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.SortedSet;

/**
 * A directory entry for one person.
 */
@Entity
public class Person extends Model {

   public Person(final String name) {
      this.name = name;
   }

   /** Generated primary key. */
   @Id
   public Long id;

   /** The person’s full name. */
   @Constraints.Required
   public String name;

   public String telephoneNumber;

   /** The key for sorting directory entries, usually the surname. */
   @Constraints.Required
   public String fileAs;

   /** An office or room number. */
   public String office;

   public String emailAddress;

   public static Finder<Long, Person> find = new Finder(Long.class, Person.class);

   /**
    * Returns a single page of directory entries.
    */
   public static Page<Person> page(final int pageNumber, final int pageSize, final String search) {
      final Query<Person> query = find.query().order("fileAs");

      // Single character queries filter by file as letter, otherwise, case insensitive.
      if (search != null) {
         if (search.matches("\\d+")) {
            query.where().contains("telephoneNumber", search);
         }
         else if (search.length() == 1) {
            query.where().istartsWith("fileAs", search);
         } else {
            query.where().icontains("name", search);
         }
      }

      return query.findPagingList(pageSize).getPage(pageNumber);
   }

   @Override
   public String toString() {
      return name + " (" + fileAs + "), tel " + telephoneNumber + ", office " + office + ", " + emailAddress;
   }
}
