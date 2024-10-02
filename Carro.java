public class Carro extends Veiculo {
    private int nPortas;

    public int getnPortas() {
        return nPortas;
    }

    public void setnPortas(int nPortas) {
        this.nPortas = nPortas;
    }

    @Override
    public String toString() {
        String descricao = super.toString();
        return "carro: " + descricao + " - Numero de portas: " + this.getnPortas();
    }


}