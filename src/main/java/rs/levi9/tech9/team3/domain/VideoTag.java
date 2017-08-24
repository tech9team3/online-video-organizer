package rs.levi9.tech9.team3.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
public class VideoTag extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1101550298406481562L;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public VideoTag() {
    }


}
