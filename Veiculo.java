public class Veiculo {
    private String marca;
    private String modelo;
    private String placa;
    private int ano;
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        String descricao =  " - Modelo: " + this.getModelo()
                            + " - Marca: " + this.getMarca()
                            + " - Placa: " + this.getPlaca()
                            + " - Ano: " + this.getAno();
        return descricao;
    }

    
}