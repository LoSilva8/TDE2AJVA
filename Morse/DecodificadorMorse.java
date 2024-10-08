public class DecodificadorMorse {

    private NoArvore raizMorse;

    public DecodificadorMorse() {
        construirArvoreMorse();
    }

    private void construirArvoreMorse() {
        raizMorse = new NoArvore(
            '\0',
            construirSubArvoreE(),
            construirSubArvoreT()
        );
    }

    private NoArvore construirSubArvoreE() {
        return new NoArvore(
            'E',
            new NoArvore(
                'I',
                new NoArvore('S',
                    new NoArvore('H', null, null),
                    new NoArvore('V', null, null)
                ),
                new NoArvore('U',
                    new NoArvore('F', null, null),
                    new NoArvore('\0', null, null)
                )
            ),
            new NoArvore(
                'A',
                new NoArvore('R',
                    new NoArvore('L', null, null),
                    new NoArvore('\0', null, null)
                ),
                new NoArvore('W',
                    new NoArvore('P', null, null),
                    new NoArvore('J', null, null)
                )
            )
        );
    }

    private NoArvore construirSubArvoreT() {
        return new NoArvore(
            'T',
            new NoArvore(
                'N',
                new NoArvore('D',
                    new NoArvore('B',null, null),
                    new NoArvore('X', null, null)
                ),
                new NoArvore('K',
                    new NoArvore('C', null, null),
                    new NoArvore('Y', null, null)
                )
            ),
            new NoArvore(
                'M',
                new NoArvore('G',
                    new NoArvore('Z', null, null),
                    new NoArvore('\0', null, null)
                ),
                new NoArvore('O',
                    new NoArvore('\0', null, null),
                    new NoArvore('\0', null, null)  
                )
            )
        );
    }

    private char morseParaCaractere(NoArvore raiz, String sequencia, int indice) {
        if (raiz == null) {
            return '\0';
        }

        if (indice == sequencia.length()) {
            return raiz.getCaractere();
        } else {
            char simbolo = sequencia.charAt(indice);
            if (simbolo == '.') {
                return morseParaCaractere(raiz.getFilhoEsquerdo(), sequencia, indice + 1);
            } else if (simbolo == '-') {
                return morseParaCaractere(raiz.getFilhoDireito(), sequencia, indice + 1);
            } else {
                return '\0';
            }
        }
    }

    public String decodificarMorse(String morse) {
        StringBuilder decodificado = new StringBuilder();
        String[] sequencias = morse.trim().split("\\s+");

        for (String seq : sequencias) {
            if (seq.equals("/")) {
                decodificado.append(' ');
            } else {
                char caractere = morseParaCaractere(raizMorse, seq, 0);
                if (caractere != '\0') {
                    decodificado.append(caractere);
                } else {
                    decodificado.append('?');
                }
            }
        }

        return decodificado.toString();
    }

    public String codificarMorse(String texto) {
        StringBuilder codigoMorse = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            String codigo = obterCodigoMorse(caractere);
            if (codigo != null) {
                codigoMorse.append(codigo).append(" ");
            } else {
                codigoMorse.append("? ");
            }
        }

        return codigoMorse.toString().trim();
    }

    private String obterCodigoMorse(char caractere) {
        switch (caractere) {
            case 'A': return ".-";
            case 'B': return "-...";
            case 'C': return "-.-.";
            case 'D': return "-..";
            case 'E': return ".";
            case 'F': return "..-.";
            case 'G': return "--.";
            case 'H': return "....";
            case 'I': return "..";
            case 'J': return ".---";
            case 'K': return "-.-";
            case 'L': return ".-..";
            case 'M': return "--";
            case 'N': return "-.";
            case 'O': return "---";
            case 'P': return ".--.";
            case 'Q': return "--.-";
            case 'R': return ".-.";
            case 'S': return "...";
            case 'T': return "-";
            case 'U': return "..-";
            case 'V': return "...-";
            case 'W': return ".--";
            case 'X': return "-..-";
            case 'Y': return "-.--";
            case 'Z': return "--..";
            case ' ': return "/";
            default: return null;
        }
    }

    public void visualizarArvore() {
        System.out.println("Árvore Morse:");
        visualizarArvore(raizMorse, "", true);
    }

    private void visualizarArvore(NoArvore no, String prefixo, boolean ehFilhoEsquerdo) {
        if (no != null) {
            System.out.println(prefixo + (ehFilhoEsquerdo ? "├── " : "└── ") + (no.getCaractere() == '\0' ? "∅" : no.getCaractere()));

            visualizarArvore(no.getFilhoEsquerdo(), prefixo + (ehFilhoEsquerdo ? "│   " : "    "), true);
            visualizarArvore(no.getFilhoDireito(), prefixo + (ehFilhoEsquerdo ? "│   " : "    "), false);
        }
    }
}
