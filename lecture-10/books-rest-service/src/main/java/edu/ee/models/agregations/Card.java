package edu.ee.models.agregations;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parent;

import javax.persistence.*;

@Entity
@Table(name = "CARDS")
@PrimaryKeyJoinColumn
public class Card {

  @Id
  @GeneratedValue(generator = "one_id_gen")
  @GenericGenerator(name = "one_id_gen", strategy = "foreign", parameters = {
          @org.hibernate.annotations.Parameter(name = "property", value = "owner")
  })
  private int id;

  @OneToOne
  private User owner;

  private String number;

  private String emitter;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getEmitter() {
    return emitter;
  }

  public void setEmitter(String emitter) {
    this.emitter = emitter;
  }
}
