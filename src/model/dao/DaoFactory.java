package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();

		// SellerDao sellerDao = DaoFactory.createSellerDao();
		// Desta forma o programa não conhece a implementação, mas somente a interface
		// SellerDao sellerDao = new SellerDaoJDBC(); - Forma comum.
	}
}
