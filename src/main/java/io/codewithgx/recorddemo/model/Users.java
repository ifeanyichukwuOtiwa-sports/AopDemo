package io.codewithgx.recorddemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 16/09/2022
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Users users = (Users) o;

        if (getFirstName() != null ? !getFirstName().equals(users.getFirstName()) : users.getFirstName() != null) return false;
        if (getLastName() != null ? !getLastName().equals(users.getLastName()) : users.getLastName() != null) return false;
        return getDateOfBirth() != null ? getDateOfBirth().equals(users.getDateOfBirth()) : users.getDateOfBirth() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getDateOfBirth() != null ? getDateOfBirth().hashCode() : 0);
        return result;
    }
}
