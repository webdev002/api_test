package uz.pdp.apitest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    private Department department;
}
