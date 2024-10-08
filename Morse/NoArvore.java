
class NoArvore {
    private char caractere;
    private NoArvore filhoEsquerdo;
    private NoArvore filhoDireito;

    public NoArvore(char caractere, NoArvore esquerdo, NoArvore direito) {
        this.caractere = caractere;
        this.filhoEsquerdo = esquerdo;
        this.filhoDireito = direito;
    }

    public char getCaractere() {
        return caractere;
    }

    public NoArvore getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public NoArvore getFilhoDireito() {
        return filhoDireito;
    }
}

