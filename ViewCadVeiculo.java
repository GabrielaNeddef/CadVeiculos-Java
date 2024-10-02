import java.util.Comparator;
import java.util.Scanner;

public class ViewCadVeiculo {
    static ServiceVeiculo automoveis = new ServiceVeiculo();
    static Scanner input = new Scanner(System.in);

    public static void limparTela() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void aguardarEnter() {
        System.out.println("Pressione Enter para continuar...");
        input.nextLine();
    }

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número Inteiro");
            }
        } while (!entradaValida);
        return valor;
    }

    private static void listar() {
        limparTela();
        var veiculos = automoveis.pesquisarTodos();
        veiculos.sort(Comparator.comparing(Veiculo::getModelo));

        System.out.println("======== LISTA DE VEICULOS =========");
        int contador = 1;
        for (Veiculo veiculo : veiculos) {
            System.out.println("Veículo " + contador + ": " + veiculo.toString());
            System.out.println("------------------------------------");
            contador++;
        }
        aguardarEnter();
    }

        private static void adicionar() {
            limparTela();
            Veiculo novoVeiculo = null;
            System.out.println("======== ADICIONANDO NOVO VEICULO ========");
            int tipoVeiculo;
            do {
                tipoVeiculo = inputNumerico("Qual o tipo de Veiculo: (1) Carro - (2) Moto: ");
                if (tipoVeiculo == 1) {
                    novoVeiculo = new Carro();
                } else if (tipoVeiculo == 2) {
                    novoVeiculo = new Moto();
                } else {
                    System.out.println("Opção Invalida");
                }
            } while (novoVeiculo == null);

        System.out.print("Informe o modelo: ");
        novoVeiculo.setModelo(input.nextLine());

        System.out.print("Informe a marca: ");
        novoVeiculo.setMarca(input.nextLine());

        System.out.print("Informe o placa: ");
        novoVeiculo.setPlaca(input.nextLine());

        novoVeiculo.setAno(inputNumerico("Informe o ano: "));

        if (tipoVeiculo == 1) {
            int nPortas;
            do {
                nPortas = inputNumerico("Informe o nº de portas: ");
                if (nPortas <= 0) {
                    System.out.println("O número de portas deve ser maior que 0. Tente novamente.");
                }
            } while (nPortas <= 0); 
            ((Carro) novoVeiculo).setnPortas(nPortas);
        } else if (tipoVeiculo == 2) {
            boolean partidaEletrica;
            int escolhaPartida;
            do {
                escolhaPartida = inputNumerico("O veículo possui partida elétrica? (1 - Sim, 0 - Não): ");
                if (escolhaPartida != 1 && escolhaPartida != 0) {
                    System.out.println("Escolha inválida. Digite 1 para Sim ou 0 para Não.");
                }
            } while (escolhaPartida != 1 && escolhaPartida != 0); 
            
            partidaEletrica = (escolhaPartida == 1); 
            ((Moto) novoVeiculo).setpartidaEletrica(partidaEletrica);
        }
        
        try {
            automoveis.adicionar(novoVeiculo);
            System.out.println("veiculo adicionado com Sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        aguardarEnter();
        
    }

    private static void pesquisarPorPlaca() {
        limparTela();
        System.out.println("====== PESQUISAR VEICULOS POR PLACA ======");
        System.out.print("Insira a placa do veículo que desejar: ");
        String placa = input.nextLine();
        var veiculos = automoveis.pesquisar(placa);

        if (veiculos.isEmpty())
            System.out.println("Não foram encontrados veiculos com esta placa.");
        else {
            int contador = 1;
            for (Veiculo veiculo : veiculos) {
                System.out.println("-----------------");
                System.out.println("Lista de veiculos");
                System.out.println("Veículo " + contador + ":");
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Marca: " + veiculo.getMarca());
                System.out.println("Placa:" + veiculo.getPlaca());
                System.out.println("Ano: " + veiculo.getAno());
                contador++;
            }
        }
        aguardarEnter();
    }
    
    private static void removerPorPlaca() {
        limparTela();
        System.out.println("====== EXCLUIR VEICULO ======");
        System.out.println("====== Lista de veiculos ======");
        var veiculos = automoveis.pesquisarTodos();
        veiculos.sort(Comparator.comparing(Veiculo::getModelo));

        int contador = 1;
        for (Veiculo veiculo : veiculos) {
            //System.out.println(livro);
            System.out.println("Veículo " + contador + ": " + veiculo.toString());
            System.out.println("------------------------------------");
            contador++;
        }

        System.out.print("Insira a placa do veiculo: ");
        String placa = input.nextLine();

        try {
            automoveis.removerPorPlaca(placa);
            System.out.println("Veiculo removido com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        aguardarEnter();
    }

    public static void main(String[] args) {

        String menu = """
                SISTEMA DE GERENCIAMENTO DE FROTAS
                Escolha uma das opções:
                1 - Cadastrar novo veiculo;
                2 - Listar todos os veiculo;
                3 - Pesquisar veiculo por placa;
                4 - Remover veiculo;
                0 - Sair;
                """;
        int opcao;
        do {
            // System.out.println(menu);
            // opcao = input.nextInt();
            // input.nextLine(); // limpar buffer
            limparTela();
            opcao = inputNumerico(menu);
            switch (opcao) {
                case 0:
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    adicionar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    pesquisarPorPlaca();
                    break;
                case 4:
                    removerPorPlaca();
                    break;
                default:
                    System.out.println("Opção Inválida!!!");
                    input.nextLine();
                    break;
            }
        } while (opcao != 0);
    }
}