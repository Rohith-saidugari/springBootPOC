package com.lettucedream.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lettucedream.api.model.enums.AttendaneStatus;
import com.lettucedream.api.model.enums.Role;
import com.lettucedream.api.model.enums.State;
import com.lettucedream.api.util.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
// Lombok data can be used to autogenerate getter setters
@Data
@NoArgsConstructor
@Table (name ="users")
/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: Model Class user, Each member variable represents an attribute in database table
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
public class User {
    /**
     * A unique primary key is required for every entity
     * @Id is used to dennote primay key in models
     */
    @Id
    @Column(name="user_id")
    /**
     * a custom auto generated user id has been used for this project
     * User ID Format LD_XXXXX
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @GenericGenerator(
            name = "emp_seq",
            strategy = "com.lettucedream.api.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "LD_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String user_id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private long phoneNumber;
    private String password;
    // TO DO add organization (fraternity)
    private String streetAddress;
    private String city;
    private State state;
    private int zipCode;
    /**
     * @CreationTimestamp to log date of creation in database
     */
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;
    private Role role;
    private AttendaneStatus attendaneStatus = AttendaneStatus.COMPLETE;
    private Date lastLogin = new Date(System.currentTimeMillis());
    /**
     * One to many mapping between entiries user and attendance
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    /**
     * @JsonManagedReference Annotation used to indicate that annotated property
     * is part of two-way linkage between fields
     */
    @JsonManagedReference
    private List<Attendance> attandance ;

    /**
     * @Transient if you dont want an attribute to be stored in DB , you can use this annotation
     * I dont want to store image in Database instead i can generate Image using zXingHelper
     *  and inject in the response
     */

    @Transient
    private String base64EncodedImage;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getBase64EncodedImage() {
        return base64EncodedImage;
    }

    public void setBase64EncodedImage(String base64EncodedImage) {
        this.base64EncodedImage = base64EncodedImage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * We dont want to return password in any case . To make api does not return password
     * use JsonIgnore annotation
     * @return
     */
    @JsonIgnore
    @JsonProperty(value = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public List<Attendance> getAttandance() {
        return attandance;
    }

    public void setAttandance(List<Attendance> attandance) {
        this.attandance = attandance;
    }

    public AttendaneStatus getAttendaneStatus() {
        return attendaneStatus;
    }

    public void setAttendaneStatus(AttendaneStatus attendaneStatus) {
        this.attendaneStatus = attendaneStatus;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
