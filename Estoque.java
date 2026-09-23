import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private final List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product produto) {
        produtos.add(produto);
    }

    public void venderProduto(int indice, int quantidade)
            throws ProdutoIndisponivelException {
        obterProduto(indice).vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Product produto : produtos) {
            total += produto.calcularValorTotal();
        }
        return total;
    }

    public void listarProdutos() {
        for (int indice = 0; indice < produtos.size(); indice++) {
            System.out.println(indice + " - " + produtos.get(indice).getDescricao());
        }
    }

    private Product obterProduto(int indice) {
        if (indice < 0 || indice >= produtos.size()) {
            throw new IndexOutOfBoundsException("Índice de produto inválido: " + indice);
        }
        return produtos.get(indice);
    }
}
