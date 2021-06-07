package statkevich.scooters.dto;

public class RoleDTO {
    private Long id;
    private String title;

    public RoleDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
