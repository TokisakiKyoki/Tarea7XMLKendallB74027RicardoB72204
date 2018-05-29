
package Domain;

import java.util.Date;


public class Person {
    private String idParent;
    private String idPerson;
    private String name;
    private String lastName1;
    private String birthDate;
    private String country;
    private String children;
    

    public Person(String idPerson, String name, String lastName1, String birthDate, String country, String idParent, String children) {
        this.idPerson = idPerson;
        this.name = name;
        this.lastName1 = lastName1;
        this.birthDate = birthDate;
        this.country = country;
        this.idParent = idParent;
        this.children = children;
    }

    public Person() {
        this.idPerson = "";
        this.name = "";
        this.lastName1 = "";
        this.birthDate = "";
        this.country = "";
        this.idParent = "";
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Person{" + "idParent=" + idParent + ", idPerson=" + idPerson + ", name=" + name + ", lastName1=" + lastName1 + ", birthDate=" + birthDate + ", country=" + country + ", children=" + children + '}';
    }

}
