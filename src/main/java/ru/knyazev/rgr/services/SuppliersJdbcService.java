package ru.knyazev.rgr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import ru.knyazev.rgr.models.Inventory;
import ru.knyazev.rgr.models.Product;
import ru.knyazev.rgr.models.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class SuppliersJdbcService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SuppliersJdbcService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Supplier> findAll() {
        return jdbcTemplate.query("SELECT * FROM supplier", new SupplierRowMapper());
    }

    public Supplier findOne(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE id = ?", new Object[]{id}, new SupplierRowMapper());
    }

    public List<Product> getProductsBySupplierId(int id) {
        String sql = "SELECT * FROM product WHERE supplier_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new ProductRowMapper());
    }

    public void save(Supplier supplier) {
        jdbcTemplate.update("INSERT INTO supplier (name, contact_info, address) VALUES (?, ?, ?)", supplier.getName(), supplier.getContactInfo(), supplier.getAddress());
    }

    public void update(int id, Supplier updatedSupplier) {
        jdbcTemplate.update("UPDATE supplier SET name = ?, SET contact_info = ?, SET address = ?  WHERE id = ?", updatedSupplier.getName(), updatedSupplier.getContactInfo(), updatedSupplier.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM supplier WHERE id = ?", id);
    }

    // RowMapper для Supplier
    private static class SupplierRowMapper implements RowMapper<Supplier> {
        @Override
        public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
            Supplier supplier = new Supplier();
            supplier.setId(rs.getInt("id"));
            supplier.setName(rs.getString("name"));
            supplier.setContactInfo(rs.getString("contact_info"));
            supplier.setAddress(rs.getString("address"));
            return supplier;
        }
    }

    // RowMapper для Product
    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getInt("price"));
            product.setQuantityAvailable(rs.getInt("quantity_available"));
            return product;
        }
    }
}
