package kunanets.tutors.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "student")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Responses> responses = new HashSet<>();

}
