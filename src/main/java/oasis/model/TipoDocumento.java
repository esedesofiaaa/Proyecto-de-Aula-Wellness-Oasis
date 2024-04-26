package oasis.model;

public enum TipoDocumento {
    CC("CC"),
    TI("TI"),
    CE("CE"),
    RC("RC"),
    PA("PA");

    private final String documento;

    TipoDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }
}
