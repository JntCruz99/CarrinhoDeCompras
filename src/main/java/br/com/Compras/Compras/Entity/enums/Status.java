package br.com.Compras.Compras.Entity.enums;

public enum Status {
    PENDENTE(1),

    COCLUIDO(2),

    CANCELADO(3);

    private int code;

    private Status(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Status valueOf(int code) {
        for (Status value : Status.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }

}
