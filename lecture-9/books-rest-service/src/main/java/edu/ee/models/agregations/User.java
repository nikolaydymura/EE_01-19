package edu.ee.models.agregations;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Sort;

import javax.persistence.*;
import java.util.*;

@Entity
@SecondaryTable(name = "PRIVATE_CARDS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ElementCollection
  @JoinTable(name = "CARDS", joinColumns = {@JoinColumn(name = "USER_ID")})
  private SortedSet<Card> cardNumbers = new TreeSet<>();

  @ElementCollection
  @JoinTable(name = "CARDS", joinColumns = {@JoinColumn(name = "USER_ID")})
  private List<Card> cardOrderedNumber = new ArrayList<>();


  @ElementCollection
  @JoinTable(name = "CARDS", joinColumns = {@JoinColumn(name = "USER_ID")})
  @MapKeyColumn(name = "NUMBER")
  private Map<String, Card> cardGroupedNumbers = new HashMap<>();

  @OneToOne
  @JoinColumn(table = "CARDS", name = "OWNER_ID")
  private Card singleCard;

  @OneToOne
  @JoinColumn(table = "PRIVATE_CARDS", name = "OWNER_ID")
  private Card privateCard;
}
