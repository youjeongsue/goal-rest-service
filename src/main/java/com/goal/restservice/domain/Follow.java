package com.goal.restservice.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(
        access =
                AccessLevel
                        .PROTECTED) // with this, instance will be generated only through builder pattern.
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="master")
    private User master;


    @ManyToOne
    @JoinColumn(name="slave")
    private User slave;
}
