package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Id;

public class Person extends Model {

   @Id
   public Long id;

   @Constraints.Required
   public String name;

   public String telephoneNumber;

   public String fileAs;

   public String office;

   public String emailAddress;
}
