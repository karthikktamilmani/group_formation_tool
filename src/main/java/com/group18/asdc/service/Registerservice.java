package com.group18.asdc.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group18.asdc.dao.Daoimpl;
import com.group18.asdc.entities.Registerbean;


@Service
public class Registerservice {

	public String registeruser(Registerbean bean) {
		String banner=null;
		Boolean flag=false;
		try {
			
			if(!bean.getBannerid().matches("B00(.*)")) {
				System.out.println("The bannerid is not valid");
				return "invalidbannerid";
			}
			else if(bean.getBannerid().length()!=9) {
				System.out.println("The bannerid is not valid");
				return "invalidbannerid2";
			}
			
			
			if(!bean.getEmailid().matches("(.*)@dal.ca")) {
				System.out.println("The emailid is not valid");
				return "invalidemailid";
			}
			if(bean.getPassword().length()<=8) {
				System.out.println("The password is less than 8 characters");
				return "shortpassword";
			}
			
			PreparedStatement pst3=Daoimpl.getConnection().prepareStatement("select * from user where emailid=?");
			pst3.setString(1, bean.getEmailid());
			ResultSet rs3=pst3.executeQuery();
			while(rs3.next()) {
				String z=rs3.getString("emailid");
				if(z.equalsIgnoreCase(bean.getEmailid())) {
					flag=true;
					System.out.println("user account already exists");
					return "alreadycreatedemail";
				}
				break;
			}
			
			PreparedStatement pst2=Daoimpl.getConnection().prepareStatement("select * from user where bannerid=?");
			pst2.setString(1, bean.getBannerid());
			ResultSet rs2=pst2.executeQuery();
			while(rs2.next()) {
				String s=rs2.getString("bannerid");
				if(s.equals(bean.getBannerid())){
					flag=true;
					System.out.println("user account already exists");
					return "alreadycreated";
				}
				
				break;
			}
			if(!flag) {
				PreparedStatement pst=Daoimpl.getConnection().prepareStatement("insert into user values(?,?,?,?,?)");
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String hashedPassword = passwordEncoder.encode(bean.getPassword());
				System.out.println("encrypted password is" +hashedPassword);
				pst.setString(1, bean.getBannerid());
				pst.setString(2, bean.getLastname());
				pst.setString(3, bean.getFirstname());
				pst.setString(4, bean.getEmailid());
				pst.setString(5, hashedPassword);

				int rs=pst.executeUpdate();
				if(rs>0) {
					System.out.println("account created");
					PreparedStatement ps=Daoimpl.getConnection().prepareStatement("select * from user where bannerid=?");
					ps.setString(1, bean.getBannerid());
					ResultSet rss=ps.executeQuery();
					while(rss.next()) {
						 banner=rss.getString("bannerid");
					}
					
				}
				PreparedStatement pst7=Daoimpl.getConnection().prepareStatement("insert into systemrole(roleid,bannerid) values(?,?)");
				pst7.setInt(1, 2);
				pst7.setString(2, banner);
				int rs5=pst7.executeUpdate();
				if(rs5>0) {
					System.out.println("The role is addded as a guest user");
				}
			}
			
			}catch(Exception ee) {
					ee.printStackTrace();
				}
			
		return "success";
			
		}
	}
	
