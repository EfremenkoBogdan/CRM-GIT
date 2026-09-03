package api.data;

import api.pojo.request.ProductPost;
import api.pojo.response.productList.Product;

public class CreateProduct {

    public static ProductPost getProduct () {
        return new ProductPost (1, "Заголовок", 100.00, "Описание", "Категория", "Картинка");
    }
}
