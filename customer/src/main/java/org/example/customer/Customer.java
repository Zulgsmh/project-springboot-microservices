package org.example.customer;

import lombok.*;

import javax.persistence.*;

@Data
@Table
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity(name = "Customer")
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public Customer() {
    }
}
