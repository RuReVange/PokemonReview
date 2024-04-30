package itmo.dev.pokemonreview.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            // здесь user_id столбец в новой таблице соединения, а referencedColumnName -> имя поля, значение которого
            // пойдет в столбец user_id
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
            // также с inverseJoinColumns, только для класса RoleModel -> role_id = название столбца в новой таблице соединения,
            // referencedColumnName = "id" -> название поля в классе RoleModel, которое будет его заполнять
    )
    private List<RoleModel> rolesList;
}
