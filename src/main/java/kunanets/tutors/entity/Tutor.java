package kunanets.tutors.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString

@Entity
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Integer price;
    private String photo;

    @OneToOne(mappedBy = "tutor")
    private User user;

    @ManyToMany
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "tutor")
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "tutor")
    private Set<Responses> responses = new HashSet<>();


}
