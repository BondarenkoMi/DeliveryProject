package ru.delivery_project.services;

import jakarta.servlet.http.HttpServletRequest;
import ru.delivery_project.db.dao.Catalog;
import ru.delivery_project.db.dao.Category;
import ru.delivery_project.db.dao.Product;

import java.util.List;

public class CatalogService {
    public static Catalog getCatalog(HttpServletRequest req){
        String sortBy = req.getParameter("sort");
        String category = req.getParameter("category");
        Integer categoryId = CategoryService.getCategoryIdByName(category);
        List<Category> categories = CategoryService.getCategories();
        Catalog catalog = new Catalog();
        catalog.setCategories(categories);
        if (categoryId == null) {
            List<Product> products = ProductService.getProducts();
            ProductService.sortProducts(products, sortBy);
            catalog.setProducts(products);
        } else {
            List<Product> products = ProductService.getProductsByCategoryId(categoryId);
            ProductService.sortProducts(products, sortBy);
            catalog.setProducts(products);
        }
        return catalog;
    }
}
