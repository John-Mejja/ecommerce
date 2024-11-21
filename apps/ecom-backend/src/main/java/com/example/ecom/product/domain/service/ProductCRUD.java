package com.example.ecom.product.domain.service;

import com.example.ecom.product.domain.aggregate.Product;
import com.example.ecom.product.domain.repository.ProductRepository;
import com.example.ecom.product.domain.vo.PublicId;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductCRUD {
  private final ProductRepository productRepository;

  public ProductCRUD(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product save(Product newProduct) {
    newProduct.initDefaultFields();
    return productRepository.save(newProduct);
  }

  public Page<Product> findAll(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  public PublicId delete(PublicId id) {
    int nbOfRowsDeleted = productRepository.delete(id);
    if (nbOfRowsDeleted != 1) {
      throw new EntityNotFoundException(String.format("No Product deleted with id %s", id));
    }
    return id;
  }
  public Optional<Product> findOne(PublicId publicId) {
    return productRepository.findOne(publicId);
  }

   public List<Product> findAllByPublicIdIn(List<PublicId> publicIds) {
     return productRepository.findByPublicIds(publicIds);
   }
}