import java.util.Locale;

public class EstoqueApp {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Estoque estoque = new Estoque();

        try {
            Product arroz = new ProdutoComum("Arroz 5kg", 25.90, 10);
            Product cafe = new ProdutoComum("Café 500g", 18.50, 8);
            Product leite = new ProdutoPerecivel("Leite", 5.90, 12, 2);
            Product queijo = new ProdutoPerecivel("Queijo", 32.00, 5, 15);

            estoque.adicionarProduto(arroz);
            estoque.adicionarProduto(cafe);
            estoque.adicionarProduto(leite);
            estoque.adicionarProduto(queijo);

            cafe.aplicarDesconto(10.0);
            queijo.aplicarDesconto(30.0, 15.0);

            System.out.println("Produtos cadastrados:");
            estoque.listarProdutos();

            estoque.venderProduto(0, 3);
            System.out.println("Venda realizada: 3 unidades de Arroz 5kg.");

            try {
                estoque.venderProduto(0, 20);
            } catch (ProdutoIndisponivelException e) {
                System.out.println("Exceção capturada: " + e.getMessage());
            }

            System.out.printf("Valor total do estoque: R$ %.2f%n",
                    estoque.calcularValorTotalEstoque());
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        try {
            new ProdutoComum("Produto inválido", 10.0, -1);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Exceção capturada ao cadastrar: " + e.getMessage());
        }
    }
}
