package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;


import com.sheryians.major.dto.ProductDTO;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
@AllArgsConstructor
public class AdminController {
    //public String uploadDir = "src/main/resources/static/productImages"; 

    
 


    @Autowired
    private final CategoryService categoryService;
    private final ProductService  productService;

    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }
    
    @GetMapping("/admin/categories")
    public String getCategory(Model model) {
        model.addAttribute("categories",categoryService.getAllCategory() );
        return "categories";
    }
    
    @GetMapping("/admin/categories/add")
    public String categoriesAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category) {

        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id) {
        List<Product> products = productService.getAllProductsByCategoryId(id);
        if(products.isEmpty()){
            categoryService.removeCategoryById(id);
            return "redirect:/admin/categories";
        }else{
            return "redirect:/admin/categories";
        }
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id,Model model) {
        Optional<Category> category=categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        }
        else{
            return "404";
        }
    }
    


    //product


    @GetMapping("/admin/products")
    public String getProduct(Model model) {
        model.addAttribute("products",productService.getAllProducts() );
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String productAddGet(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory() );

        return "productsAdd";

    }
    public static String uploadDir= System.getProperty("user.dir") +"/src/main/resources/static/productImages";

   /*  @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file ,
                                 @RequestParam("imgName") String imgName) throws IOException {
        

                  Product product=new Product();
                  product.setId(productDTO.getId());  
                  product.setName(productDTO.getName());  
                  product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
                  product.setPrice(productDTO.getPrice());  
                  product.setAvailableNumber(productDTO.getAvailableNumber());
                  product.setDescription(productDTO.getDescription());  
                  String imageUUID;

                  if(!file.isEmpty()){
                          imageUUID=file.getOriginalFilename();
                          Path fileNameAndPath=Paths.get(uploadDir,imageUUID);
                          Files.write(fileNameAndPath,file.getBytes());
                    
                  }
                  else{
                          imageUUID=imgName;
                  }
                  product.setImageName(imageUUID);
                  productService.addProduct(product);


        return "redirect:/admin/products";
    }*/
    @PostMapping("/admin/products/add")
public String productAddPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                             @RequestParam("productImage") MultipartFile file ,
                             @RequestParam("imgName") String imgName) throws IOException {
    Product product = new Product();
    product.setId(productDTO.getId());  
    product.setName(productDTO.getName());  
    product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
    product.setPrice(productDTO.getPrice());  
    product.setAvailableNumber(productDTO.getAvailableNumber());
    product.setDescription(productDTO.getDescription());  
    String imageUUID;

    if (!file.isEmpty()) {
        imageUUID = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
        createDirectoryIfNotExists(fileNameAndPath.getParent().toString()); // إنشاء المسار إذا لم يكن موجودًا
        Files.write(fileNameAndPath, file.getBytes());
    } else {
        imageUUID = imgName;
    }
    
    product.setImageName(imageUUID);
    productService.addProduct(product);

    return "redirect:/admin/products";
}

public void createDirectoryIfNotExists(String path) throws IOException {
    Path directoryPath = Paths.get(path);
    if (!Files.exists(directoryPath)) {
        Files.createDirectories(directoryPath);
    }
}

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }
    
    
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id,Model model) {

         Product product = productService.getProductById(id).get();
         ProductDTO productDTO=new ProductDTO();
         productDTO.setId(product.getId());
         productDTO.setName(product.getName());
         productDTO.setCategoryId((product.getCategory().getId()));
         productDTO.setPrice(product.getPrice());
         productDTO.setAvailableNumber(product.getAvailableNumber());
         productDTO.setDescription(product.getDescription());
         productDTO.setImageName(product.getImageName());
         
         model.addAttribute("categories", categoryService.getAllCategory());
         model.addAttribute("productDTO", productDTO);

        return "productsAdd";
    }
    
}
