package com.cn.cly.entity;

/**
 * Created by chen on 2017/6/23.
 * 在单向关系中没有mappedBy,主控方相当于拥有指向另一方的外键的一方。
 * 1.一对一和多对一的@JoinColumn注解的都是在“主控方”，都是本表指向外表的外键名称。
 * 2.一对多的@JoinColumn注解在“被控方”，即一的一方，指的是外表中指向本表的外键名称。
 * 3.多对多中，joinColumns写的都是本表在中间表的外键名称，
 * inverseJoinColumns写的是另一个表在中间表的外键名称。
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@id")
@Table(name="admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id ;
    private String username;
    private String password;
    private Date birthday;
    private String sex ;
    private String address;
    private String realName;
    private String salt;
    private Date lastPwdChange;
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "admin_role", joinColumns = { @JoinColumn(name = "admin_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    private Set<Role> roleList;// 一个用户具有多个角色


    public Set<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="username",unique = true)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }
    public void setState(byte state) {
        this.state = state;
    }

    public Date getLastPwdChange() {
        return lastPwdChange;
    }

    public void setLastPwdChange(Date lastPwdChange) {
        this.lastPwdChange = lastPwdChange;
    }

    /**
     * 密码盐.
     * @return
     */
    @Transient
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", birthday="
                + birthday + ", sex=" + sex + ", address=" + address + "]";
    }

}


