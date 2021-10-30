package uz.pdp.apitest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String corpName;

    @Column(nullable = false,unique = true)
    private String directorName;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;
}
