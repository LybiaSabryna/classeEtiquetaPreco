import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface EtiquetaPreco {
}

class Produto implements EtiquetaPreco {
    protected String nome;
    protected Double preco;

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String etiquetaPreco() {
        return nome + " R$ " + String.format("%.2f", preco);
    }
}

class ProdutoImportado extends Produto {

    public ProdutoImportado(String nome, Double preco) {
        super(nome, preco);
    }

}

class ProdutoUsado extends Produto {

    public ProdutoUsado(String nome, Double preco) {
        super(nome, preco);
    }

}

public class Principal {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Produto> produtos = new ArrayList<>();

        System.out.print("Entre com o número de produtos: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Dados do produto #" + (i + 1) + ":");
            System.out.print("Produto comum, usado ou importado (c/u/i)? ");
            char tipo = sc.next().charAt(0);

            System.out.print("Nome: ");
            sc.nextLine();
            String nome = sc.nextLine();
            System.out.print("Preço: ");
            double preco = sc.nextDouble();

            if (tipo == 'i') {
                produtos.add(new ProdutoImportado(nome, preco));
            } else if (tipo == 'u') {
                System.out.print("Data de fabricação (dd/MM/yyyy): ");
                sc.nextLine();
                try {
                    produtos.add(new ProdutoUsado(nome, preco));
                } catch (Exception _) {
                }
            } else {
                produtos.add(new Produto(nome, preco));
            }
        }

        System.out.println();
        System.out.println("ETIQUETAS DE PREÇO:");
        for (Produto produto : produtos) {
            System.out.println(produto.etiquetaPreco());
        }

        sc.close();
    }
}
