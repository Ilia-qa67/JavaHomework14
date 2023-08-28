import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    @Test
    public void ShouldRemoveItem() {
        ShopRepository repo = new ShopRepository();

        repo.add(new Product(10, "Курица", 325));
        repo.add(new Product(3, "Хлеб", 40));
        repo.add(new Product(88, "Пельмени", 230));

        repo.remove(3);

        Assertions.assertEquals(null, repo.findById(3));
    }

    @Test
    public void RemovingNotExistItem() {
        ShopRepository repo = new ShopRepository();

        repo.add(new Product(10, "Курица", 325));
        repo.add(new Product(3, "Хлеб", 40));
        repo.add(new Product(88, "Пельмени", 230));

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(28);
        });
    }

    @Test
    public void ShouldNotAddItemWithSameID() {
        ShopRepository repo = new ShopRepository();

        repo.add(new Product(10, "Курица", 325));

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(new Product(10, "Хлеб", 29));
        });
    }

    @Test
    public void ShouldAddItem() {
        ShopRepository repo = new ShopRepository();

        Product product1 = new Product(
                10,
                "Курица",
                325
        );

        Product product2 = new Product(
                12,
                "Хлеб",
                25
        );

        Product product3 = new Product(
                1,
                "Яйцо",
                10
        );

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected,actual);
    }
}
