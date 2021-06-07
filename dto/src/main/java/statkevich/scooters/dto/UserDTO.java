package statkevich.scooters.dto;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String pass;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public RoleDTO getRoleDTO() {        return roleDTO;    }
//    public void setRoleDTO(RoleDTO roleDTO) {        this.roleDTO = roleDTO;    }

    @Override
    public String toString() {
        return "User: " +
                " id=" + id +
                " name=" + name +
                //", role="+roleDTO.getTitle()+
                ", role=" + role +
                ", phone number= " + phoneNumber +
                ", pass= " + pass;
    }
}
