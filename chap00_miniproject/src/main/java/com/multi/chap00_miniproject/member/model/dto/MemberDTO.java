package com.multi.chap00_miniproject.member.model.dto;

import java.sql.Date;
import java.util.List;

public class MemberDTO {

    private int no;											// 회원번호
    private String id;										// 회원아이디
    private String pwd;										// 회원비밀번호
    private String tempPwdYn;								// 임시비밀번호여부
    private Date pwdChangedDatetime;				// 회원비밀번호변경일시
    private String pwdExpDate;								// 회원비밀번호만료일자
    private String name;									// 회원이름
    private Date registDatetime;					// 회원가입일시
    private int accumLoginCount;							// 누적로그인횟수
    private int loginFailedCount;							// 로그인연속실패횟수
    private String accLockYn;								// 계정잠금여부
    private String accInactiveYn;							// 계정비활성화여부
    private String accExpDate;								// 계정만료일자
    private String accExpYn;								// 계정만료여부
    private Date accSecessionDatetime;				// 계정탈퇴일시
    private String accSecessionYn;							// 계정탈퇴여부

    private List<MemberRoleDTO> memberRoleList;				// 회원별권한리스트 // 1대 다 관계

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

    public String getTempPwdYn() {
        return tempPwdYn;
    }

    public void setTempPwdYn(String tempPwdYn) {
        this.tempPwdYn = tempPwdYn;
    }

    public Date getPwdChangedDatetime() {
        return pwdChangedDatetime;
    }

    public void setPwdChangedDatetime(Date pwdChangedDatetime) {
        this.pwdChangedDatetime = pwdChangedDatetime;
    }

    public String getPwdExpDate() {
        return pwdExpDate;
    }

    public void setPwdExpDate(String pwdExpDate) {
        this.pwdExpDate = pwdExpDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistDatetime() {
        return registDatetime;
    }

    public void setRegistDatetime(Date registDatetime) {
        this.registDatetime = registDatetime;
    }

    public int getAccumLoginCount() {
        return accumLoginCount;
    }

    public void setAccumLoginCount(int accumLoginCount) {
        this.accumLoginCount = accumLoginCount;
    }

    public int getLoginFailedCount() {
        return loginFailedCount;
    }

    public void setLoginFailedCount(int loginFailedCount) {
        this.loginFailedCount = loginFailedCount;
    }

    public String getAccLockYn() {
        return accLockYn;
    }

    public void setAccLockYn(String accLockYn) {
        this.accLockYn = accLockYn;
    }

    public String getAccInactiveYn() {
        return accInactiveYn;
    }

    public void setAccInactiveYn(String accInactiveYn) {
        this.accInactiveYn = accInactiveYn;
    }

    public String getAccExpDate() {
        return accExpDate;
    }

    public void setAccExpDate(String accExpDate) {
        this.accExpDate = accExpDate;
    }

    public String getAccExpYn() {
        return accExpYn;
    }

    public void setAccExpYn(String accExpYn) {
        this.accExpYn = accExpYn;
    }

    public Date getAccSecessionDatetime() {
        return accSecessionDatetime;
    }

    public void setAccSecessionDatetime(Date accSecessionDatetime) {
        this.accSecessionDatetime = accSecessionDatetime;
    }

    public String getAccSecessionYn() {
        return accSecessionYn;
    }

    public void setAccSecessionYn(String accSecessionYn) {
        this.accSecessionYn = accSecessionYn;
    }

    public List<MemberRoleDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    public MemberDTO(int no, String id, String pwd, String tempPwdYn, Date pwdChangedDatetime, String pwdExpDate, String name, Date registDatetime, int accumLoginCount, int loginFailedCount, String accLockYn, String accInactiveYn, String accExpDate, String accExpYn, Date accSecessionDatetime, String accSecessionYn, List<MemberRoleDTO> memberRoleList) {
        this.no = no;
        this.id = id;
        this.pwd = pwd;
        this.tempPwdYn = tempPwdYn;
        this.pwdChangedDatetime = pwdChangedDatetime;
        this.pwdExpDate = pwdExpDate;
        this.name = name;
        this.registDatetime = registDatetime;
        this.accumLoginCount = accumLoginCount;
        this.loginFailedCount = loginFailedCount;
        this.accLockYn = accLockYn;
        this.accInactiveYn = accInactiveYn;
        this.accExpDate = accExpDate;
        this.accExpYn = accExpYn;
        this.accSecessionDatetime = accSecessionDatetime;
        this.accSecessionYn = accSecessionYn;
        this.memberRoleList = memberRoleList;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", tempPwdYn='" + tempPwdYn + '\'' +
                ", pwdChangedDatetime=" + pwdChangedDatetime +
                ", pwdExpDate='" + pwdExpDate + '\'' +
                ", name='" + name + '\'' +
                ", registDatetime=" + registDatetime +
                ", accumLoginCount=" + accumLoginCount +
                ", loginFailedCount=" + loginFailedCount +
                ", accLockYn='" + accLockYn + '\'' +
                ", accInactiveYn='" + accInactiveYn + '\'' +
                ", accExpDate='" + accExpDate + '\'' +
                ", accExpYn='" + accExpYn + '\'' +
                ", accSecessionDatetime=" + accSecessionDatetime +
                ", accSecessionYn='" + accSecessionYn + '\'' +
                ", memberRoleList=" + memberRoleList +
                '}';
    }
}









