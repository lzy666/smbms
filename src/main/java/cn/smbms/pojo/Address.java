package cn.smbms.pojo;

import java.util.Date;

public class Address {
    private  int id;
    private  String contact;
    private String addressDesc;
    private  int postCode;
    private String tel;
    private int createBy;
    private Date creationDate;
    private int userId;
    public int getId() {
        return id;
    }

    public String getContact() {
        return contact;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getTel() {
        return tel;
    }

    public int getCreateBy() {
        return createBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", addressDesc='" + addressDesc + '\'' +
                ", postCode=" + postCode +
                ", tel='" + tel + '\'' +
                ", createBy=" + createBy +
                ", creationDate=" + creationDate +
                ", userId=" + userId +
                '}';
    }
}
