package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private int id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(int id, String name, List<Member> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public Team(String name) {
        this.name = name;
    }
}
