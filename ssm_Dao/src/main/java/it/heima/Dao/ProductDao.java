package it.heima.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import it.heima.Domain.PageBean;
import it.heima.Domain.Product;

public interface ProductDao {

	@Select("select * from product")
	public List<Product>findAll();

	@Insert("insert into product (productNum, productName, cityName, departureTime, productPrice, productDesc, productStatus) "
			+ "values (#{productNum}, #{productName}, #{cityName}, #{departureTime}, #{productPrice}, #{productDesc}, #{productStatus})")
	public void save(Product product);

	@Select("select * from product where id = #{id}")
	public Product findById(String id);

	@Update("update product set productNum = #{productNum}"
			+ ", productName = #{productName}"
			+ ", cityName = #{cityName}"
			+ ", departureTime = #{departureTime}"
			+ ", productPrice = #{productPrice}"
			+ ", productDesc = #{productDesc}"
			+ ", productStatus = #{productStatus}"
			+ "where id = #{id}")
	public void update(Product product);

	@Select("select count(1) from product")
	public int findTotalCount();

	@Select("SELECT * FROM"
			+ "(SELECT ROWNUM rn,t.* from "
			+ "(select * from PRODUCT ) t WHERE	ROWNUM <= #{begin})"
			+ "WHERE rn >= #{end}")
	public List<Product> findPageText(@Param("begin")int begin,@Param("end") int end);

	
}
