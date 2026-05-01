package com.managment.library.entity;


import java.io.Serializable;
import java.util.Objects;

public class MemberId implements Serializable {
    private String email;
    private String contactNumber;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MemberId memberId))
            return false;

        return Objects.equals(email, memberId.email) && Objects.equals(contactNumber, memberId.contactNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, contactNumber);
    }
}
