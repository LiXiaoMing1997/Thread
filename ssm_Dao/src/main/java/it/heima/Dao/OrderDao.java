package it.heima.Dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import it.heima.Domain.Orders;
import it.heima.Domain.Product;


public interface OrderDao {

	@Select("select * from orders")
	@Results({
		@Result(column="productid",property="product",
				javaType=Product.class,
				one=@One(select ="it.heima.Dao.ProductDao.findById"))
	})
	
	List<Orders> findAll();

	
	
	
}
