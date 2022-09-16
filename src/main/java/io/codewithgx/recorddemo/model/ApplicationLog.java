package io.codewithgx.recorddemo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 16/09/2022
 */


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String operation;
    private String endPoint;
    private String method;
    private String params;
    private LocalDateTime requestTime;





    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ApplicationLog that = (ApplicationLog) o;

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null) return false;
        if (getOperation() != null ? !getOperation().equals(that.getOperation()) : that.getOperation() != null) return false;
        if (getEndPoint() != null ? !getEndPoint().equals(that.getEndPoint()) : that.getEndPoint() != null) return false;
        if (getMethod() != null ? !getMethod().equals(that.getMethod()) : that.getMethod() != null) return false;
        if (getParams() != null ? !getParams().equals(that.getParams()) : that.getParams() != null) return false;
        return getRequestTime() != null ? getRequestTime().equals(that.getRequestTime()) : that.getRequestTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getOperation() != null ? getOperation().hashCode() : 0);
        result = 31 * result + (getEndPoint() != null ? getEndPoint().hashCode() : 0);
        result = 31 * result + (getMethod() != null ? getMethod().hashCode() : 0);
        result = 31 * result + (getParams() != null ? getParams().hashCode() : 0);
        result = 31 * result + (getRequestTime() != null ? getRequestTime().hashCode() : 0);
        return result;
    }
}
