package testingWeb.models.search_product;

import testingWeb.models.TheWebElement;
import testingWeb.models.login.Principal;


import static testingWeb.services.utils.TheJsonModels.getWebElementFromJSON;




public class SearchProduct extends Principal{
    
    private TheWebElement inputProduct;

    public SearchProduct(){
        this.inputProduct = getWebElementFromJSON("inputProduct");
    }

    public TheWebElement getInputProduct() {
        return this.inputProduct;
    }
}
