package com.uday.api.bookws.user;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITIES")
public class AuthoritiesEntity {

    @Column(name = "Authority_Id")
    @Id
    private Integer authorityId;

    @Column(name = "Authority")
    private String authority;

    public AuthoritiesEntity() {
    }

    public AuthoritiesEntity(Integer authorityId, String authority) {
        this.authorityId = authorityId;
        this.authority = authority;
    }

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "Username", nullable = false)
    private UserEntity userEntity;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
