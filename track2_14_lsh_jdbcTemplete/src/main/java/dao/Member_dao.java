package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import common.CommonTemplate;

public class Member_dao {
	private JdbcTemplate tem = CommonTemplate.getTempl();
	
    public String encryptSHA256(String value) throws NoSuchAlgorithmException{ //암호화
        String encryptData ="";
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(value.getBytes());
 
        byte[] digest = sha.digest();
        for (int i=0; i<digest.length; i++) {
            encryptData += Integer.toHexString(digest[i] &0xFF).toUpperCase();
        }
        return encryptData;
    }
    public String getCheckLogin(String id, String pw) { //로그인 
    	String query = " select name from track2_14_member "+
    				   " where id='"+id+"' "+
    				   " and pw='"+pw+"' ";
    	String name = "";
    	try {
    		name = tem.queryForObject(query, String.class);
    	} catch(EmptyResultDataAccessException e) {
    		name = "";
    	}
    	return name;
    }
}