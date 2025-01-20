package ku.shop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private Exception error;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        try {
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);
        }
        catch (Exception e) {
             error = e;
        }
    }
    @Then("error should be thrown")
    public void error_should_be_thrown() {
        assertInstanceOf(IllegalArgumentException.class, error);
    }
    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }
    @And("total of {string} in stock is {int}")
    public void total_of_in_stock_is(String name, int stock) {
        Product prod = catalog.getProduct(name);
        assertEquals(stock, prod.getStock());
    }
}

