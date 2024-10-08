package com.multi.chap00_miniproject.member.model.dto;

import java.util.List;

public class MemberDTO {

    private int no;											// 회원번호
    private String id;										// 회원아이디
    private String pwd;										// 회원비밀번호
    private String name;									// 회원이름
    private String role;

    private List<MemberDTO> memberRoleList;

    public List<MemberDTO> getMemberRoleList() {
        return memberRoleList;
    }


    public void setMemberRoleList(List<MemberDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    public MemberDTO() {}

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public MemberDTO(int no, String id, String pwd, String name, String role) {
        this.no = no;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.role = role;

    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}









