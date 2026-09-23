import java.util.Locale;

public abstract class Product implements Vendavel {
    private final String nome;
    private double preco;
    private int quantidade;

    protected Product(String nome, double preco, int quantidade)
            throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException("O preço não pode ser negativo.");
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("A quantidade não pode ser negativa.");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format(Locale.US, "%s | Preço: R$ %.2f | Quantidade: %d",
                nome, preco, quantidade);
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada <= 0) {
            throw new ProdutoIndisponivelException("A quantidade de venda deve ser positiva.");
        }
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException(
                    "Estoque insuficiente para o produto " + nome + ". Disponível: " + quantidade);
        }
        quantidade -= quantidadeDesejada;
    }

    public void aplicarDesconto(double percentual) {
        validarPercentual(percentual);
        preco *= 1 - percentual / 100;
    }

    public void aplicarDesconto(double percentual, double descontoMaximo) {
        validarPercentual(percentual);
        validarPercentual(descontoMaximo);
        preco *= 1 - Math.min(percentual, descontoMaximo) / 100;
    }

    private void validarPercentual(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("O percentual deve estar entre 0 e 100.");
        }
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
