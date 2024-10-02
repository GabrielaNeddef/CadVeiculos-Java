import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {
    // BD em memória
    private List<Veiculo> frota = new ArrayList<>();

    public void adicionar(Veiculo veiculo) throws Exception {
        if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty())
            throw new Exception("Não é permitido cadastrar veiculo sem marca");
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty())
            throw new Exception("Não e possível cadastar um veiculo sem modelo!");
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty())
            throw new Exception("Coloque uma placa válida! (O número não pode ser negativo ou 0)");
        if (veiculo.getAno() <= 0 )
            throw new Exception("Coloque um ano válido! (Deve ser maior que 0)");

        for (Veiculo veiculofrota : frota) {
            if (veiculofrota.getPlaca().equalsIgnoreCase(veiculo.getPlaca()))
                throw new Exception("Já existe um veiculo cadastrado com esta placa");
        }
        frota.add(veiculo);
    }

    public List<Veiculo> pesquisar(String placa) {
        return pesquisar(placa, null);
    }
    public List<Veiculo> pesquisar(String placa, String modelo) {
        List<Veiculo> veiculoEncontrados = new ArrayList<>();

        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().toLowerCase().contains(placa.toLowerCase())) {
                if (modelo == null
                        ||veiculo.getModelo().toLowerCase().contains(modelo.toLowerCase())) {
                    veiculoEncontrados.add(veiculo);
                }
            }
        }

        return veiculoEncontrados;
    }

    public Veiculo removerPorPlaca(String placa) throws Exception {
        Veiculo veiculoRemovido = null;
        for (Veiculo veiculo : frota) {
            if (veiculo.getPlaca().equals(placa)) {
                veiculoRemovido = veiculo;
                frota.remove(veiculo);
                break;
            }
        }
        if (veiculoRemovido ==  null)
            throw new Exception("Veiculo não encontrado com esta placa");
        return veiculoRemovido;
    }

    public List<Veiculo> pesquisarTodos() {
        return this.frota;
    }

}