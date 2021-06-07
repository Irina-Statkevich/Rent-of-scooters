package statkevich.scooters.dto;


import javax.validation.constraints.NotBlank;

public class RentDTO {
    private Long id;
    private String scooterModel;
    private String userName;
    private String price;
    @NotBlank
    private String termOfRent;
    @NotBlank
    private String dateStart;
    private String dateEnd;

    public RentDTO() {
    }

    public RentDTO(String scooter_model, String dateStart, String termOfRent) {
        this.scooterModel = scooter_model;
        this.dateStart = dateStart;
        this.termOfRent = termOfRent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScooterModel() {
        return scooterModel;
    }

    public void setScooterModel(String scooterModel) {
        this.scooterModel = scooterModel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTermOfRent() {
        return termOfRent;
    }

    public void setTermOfRent(String priceList_termOfRent) {
        this.termOfRent = priceList_termOfRent;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RentDTO{" +
                "id=" + id +
                ", scooterModel='" + scooterModel +
                ", userName='" + userName+
                ", termOfRent='" + termOfRent +
                ", dateStart=" + dateStart;
    }

    //    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }


}
