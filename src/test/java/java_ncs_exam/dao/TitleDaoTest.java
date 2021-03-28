package java_ncs_exam.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java_ncs_exam.dao.impl.TitleDaoImpl;
import java_ncs_exam.dto.Title;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	private static TitleDao dao= TitleDaoImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	@Test
	public void test01SelectTitleByAll() {
		System.out.printf("%s()%n","test01SelectTitleByAll");
		List<Title> titleList = dao.selectTitleByAll();
		Assert.assertNotNull(titleList);
		for(Title t : titleList) {
			System.out.println(t);
		}
	}
	

	@Test
	public void test03SelectTitleByNo() {
		System.out.printf("%s()%n","test03SelectTitleByNo");  
		Title title = new Title(2);  
		Title searchtitle = dao.selectTitleByNo(title);
		Assert.assertNotNull(searchtitle);
		System.out.println(searchtitle); 
	}

	@Test
	public void test02InsertTitle() {
		System.out.printf("%s()%n","test02insertTitle");  
		Title newTitle = new Title (2,"인턴");
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(newTitle));
	}

	@Test
	public void test04UpdateTitle() {
		System.out.printf("%s()%n","test04updateTitle"); 
		Title updateTitle= new Title(2, "사원");
		int res = dao.updateTitle(updateTitle);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(updateTitle));
	}

	@Test
	public void test05DeleteTitle() {
		System.out.printf("%s()%n","test05DeleteTitle");  
		int res = dao.deleteTitle(2);
		Assert.assertEquals(1, res);
		dao.selectTitleByAll();
	}

}
