package com.cyrus.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.cyrus.dao.ProductDao;
import com.cyrus.model.Product;

@Controller
public class HomeController {

	private Path path;

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/productList")
	public String getProducts(Model model) {
		List<Product> products = productDao.getAllProduct();
		model.addAttribute("products", products);
		return "productList";
	}

	@RequestMapping("/productList/viewProduct/{productId}")
	public String viewProduct(@PathVariable Long productId, Model model) throws IOException {
		Product product = productDao.getProductById(productId);
		model.addAttribute(product);
		return "viewProduct";
	}

	@RequestMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@RequestMapping("/admin/productInventory")
	public String productInventory(Model model) {
		List<Product> products = productDao.getAllProduct();
		model.addAttribute("products", products);
		return "productInventory";
	}

	@RequestMapping("/admin/productInventory/addProduct")
	public String addProduct(Model model) {
		Product product = new Product();
		product.setProductCategory("Nutrition");
		product.setProductStatus("Available");

		model.addAttribute("product", product);

		return "addProduct";
	}

	@RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {
		productDao.addProduct(product);

		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		System.out.println("ReptD" + rootDirectory);
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\img\\" + product.getProductId() + ".png");

		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product Image Saving Failed", e);
			}
		}
		return "redirect:/admin/productInventory";
	}

	@RequestMapping("/admin/productInventory/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable Long productId, Model model) {
		productDao.deleteProduct(productId);
		return "redirect:/admin/productInventory";
	}
}
