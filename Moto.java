public class Moto extends Veiculo {
    private boolean partidaEletrica;

    public boolean ispartidaEletrica() {
        return partidaEletrica;
    }

    public void setpartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public String toString() {
        String descricao = super.toString();
        String partidaEletricaStr = this.ispartidaEletrica() ? "Sim" : "Não";
        return "Moto: " + descricao + " - Partida Elétrica: " + partidaEletricaStr;
    }
}
