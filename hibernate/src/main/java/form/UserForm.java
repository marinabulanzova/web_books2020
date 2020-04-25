package form;

public class UserForm {
    private Integer id_user;
    private String surname;
    private String first_name;
    private String patronymic;
    private String address;
    private String phone_number;
    private String e_mail;
    private String old_password;
    private String new_password;

    public UserForm() {
    }

    public UserForm(Integer id_user, String surname, String first_name, String patronymic, String address, String phone_number, String e_mail) {
        this.id_user = id_user;
        this.surname = surname;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.address = address;
        this.phone_number = phone_number;
        this.e_mail = e_mail;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
