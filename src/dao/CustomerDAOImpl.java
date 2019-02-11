package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.CustomerDTO;
import enums.CustomerSQL;
import enums.Vendor;
import factory.DatabaceFactory;

public class CustomerDAOImpl implements CustomerDAO {
	private static CustomerDAOImpl instance = new CustomerDAOImpl();
	private CustomerDAOImpl() {
		dao = CustomerDAOImpl.getInstance();	}
	public static CustomerDAOImpl getInstance() {return instance;}
	CustomerDAO dao;
	
	/*
	 * customerID, customerName, password, address, city, postalcode, ssn;
	 */
	@Override
	public void insertCustomer(CustomerDTO cus) {
		System.out.println("===CustomerDAO Impl 입장 ====");
		try {
			String sql = String.format(CustomerSQL.SIGNUP.toString());
			System.out.println("slq::::"+sql);
			Connection conn = DatabaceFactory
					.createDatabase(Vendor.ORACLE).getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cus.getCustomerID());
			ps.setString(2, cus.getCustomerName());
			ps.setString(3, cus.getPassword());
			ps.setString(4, cus.getSsn());
			ps.setString(5, cus.getPhone());
			ps.setString(6, cus.getPhoto());
			ps.setString(7, cus.getCity());
			ps.setString(8, cus.getAddress());
			ps.setString(9, cus.getPostalcode());
			
			
			int rs = ps.executeUpdate();
			System.out.println((rs==1)?"입력성공":"입력실패");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CustomerDTO> selectAllCustomersList() {
		List<CustomerDTO> list =  new ArrayList<>();
		try {
		
		PreparedStatement stmt = DatabaceFactory
			.createDatabase(Vendor.ORACLE)
			.getConnection().prepareStatement(CustomerSQL.LIST.toString());
			
			ResultSet rs = stmt.executeQuery();
			CustomerDTO cust = null;
			while(rs.next()) {
				cust = new CustomerDTO();
				cust.setCustomerID(rs.getString("CUSTOMER_ID"));
				cust.setCustomerName(rs.getString("CUSTOMER_NAME"));
				cust.setSsn(rs.getString("SSN"));
				cust.setPhone(rs.getString("PHONE"));
				cust.setPhoto(rs.getString("PHOTO"));
				cust.setCity(rs.getString("CITY"));
				cust.setAddress(rs.getString("ADDRESS"));
				cust.setPostalcode(rs.getString("POSTAL_CODE"));
				list.add(cust);
			}
			System.out.println("1번회원:"+list.get(0).getCustomerName());
			System.out.println("1번 주소:"+list.get(0).getPostalcode());
			System.out.println("2번회원:"+list.get(1).getCustomerName());
			System.out.println("3번회원:"+list.get(2).getCustomerName());
			System.out.println("4번회원:"+list.get(3).getCustomerName());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<CustomerDTO> selectCustomers(String searchWord) {
		List<CustomerDTO> list =  new ArrayList<CustomerDTO>();
		
		try {
			String sql = "";
			PreparedStatement stmt = DatabaceFactory
									.createDatabase(Vendor.ORACLE)
									.getConnection().prepareStatement(sql);
									 stmt.setString(1, "");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					list.add(null);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public CustomerDTO selectCustomer(CustomerDTO cus) {
		CustomerDTO dto = null;
		
		try {
			
			PreparedStatement stmt = DatabaceFactory.createDatabase(Vendor.ORACLE)
			.getConnection().prepareStatement(CustomerSQL.SIGNIN.toString());
			stmt.setString(1,cus.getCustomerID());
			stmt.setString(2, cus.getPassword());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				dto = new CustomerDTO();
				dto.setAddress(rs.getString("ADDRESS"));
				dto.setCity(rs.getString("CITY"));
				dto.setCustomerID(rs.getString("CUSTOMER_ID"));
				dto.setCustomerName(rs.getString("CUSTOMER_NAME"));
				dto.setPassword(rs.getString("PASSWORD"));
				dto.setPostalcode(rs.getString("POSTALCODE"));
				dto.setSsn(rs.getString("SSN"));
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int countCustomer() {
		int count = 0;
		
		try {
			String sql = "";
			PreparedStatement stmt = 
					DatabaceFactory.createDatabase(Vendor.ORACLE)
					.getConnection().prepareStatement(sql);
			stmt.setString(1, "");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public boolean existsCustomerID(CustomerDTO cus) {
		boolean ok = false;
		try {
		
			PreparedStatement ps = DatabaceFactory
					.createDatabase(Vendor.ORACLE)
					.getConnection().prepareStatement(CustomerSQL.SIGNIN.toString());
			System.out.println("sql??:::"+CustomerSQL.SIGNIN.toString());
			System.out.println("id:"+cus.getCustomerID());
			System.out.println("ps:"+cus.getPassword());
			
			ps.setString(1, cus.getCustomerID());
			ps.setString(2, cus.getPassword());
			ResultSet rs = ps.executeQuery();
			System.out.println("rs:::"+rs);
			if(rs.next()) {ok = true;}
			System.out.println("==existsCustomerDAO!!!!===");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok??::"+ok);
		return ok;
	}

	@Override
	public void updateCustomer(CustomerDTO cus) {
		
		try {
			String sql = "";
			PreparedStatement stmt = 
					DatabaceFactory.createDatabase(Vendor.ORACLE)
					.getConnection().prepareStatement(sql);
			stmt.setString(1, "");
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(CustomerDTO cus) {

		String sql = "";
		try {
			PreparedStatement stmt = DatabaceFactory.createDatabase(Vendor.ORACLE)
									.getConnection().prepareStatement(sql);
			stmt.setString(1, "");
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
