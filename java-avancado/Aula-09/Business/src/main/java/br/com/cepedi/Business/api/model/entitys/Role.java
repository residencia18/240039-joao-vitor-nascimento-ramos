package br.com.cepedi.Business.api.model.entitys;

import br.com.cepedi.Business.api.model.records.role.input.DataRegisterRole;
import br.com.cepedi.Business.api.model.records.role.input.DataUpdateRole;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private Boolean activated;


    public Role(DataRegisterRole data){
        this.name = data.name();
        this.activated = true;
    }

    public void updateData(DataUpdateRole data) {

        if(data.name() != null && !name.isEmpty()){
            this.name = data.name();
        }

    }

    public void logicalDelete() {
        this.activated = false;
    }
}
