package cu.pdi.bookstore.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Objects;
import java.util.function.Function;

public final class HashMaker {

    /**
     * *
     * * Convierte un arreglo de bytes a String usando valores hexadecimales
     *
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private static String toHexadecimal(byte[] digest) {
        StringBuilder hash = new StringBuilder();
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash.append("0");
            }
            hash.append(Integer.toHexString(b));
        }
        return hash.toString();
    }

    /**
     * *
     * * Encripta un mensaje de texto mediante algoritmo de resumen de
     * mensaje.
     *
     * @param message   texto a encriptar
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     *                  SHA-256, SHA-384, SHA-512
     * @return mensaje encriptado
     */
    public static String hashedTypeFor(String message, HashAlgorithm algorithm) {
        return hashedTypeFor(message, algorithm, null, HashMaker::toHexadecimal);
    }

    /**
     * *
     * * Encripta un mensaje de texto mediante algoritmo de resumen de
     * mensaje.
     *
     * @param message   texto a encriptar
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     *                  SHA-256, SHA-384, SHA-512
     * @param provider provider of hash algorithm implementation
     * @return mensaje encriptado
     */
    public static String hashedTypeFor(String message, HashAlgorithm algorithm, Provider provider) {
        return hashedTypeFor(message, algorithm, provider, HashMaker::toHexadecimal);
    }

    /**
     * *
     * * Encripta un mensaje de texto mediante algoritmo de resumen de
     * mensaje.
     *
     * @param message        texto a encriptar
     * @param algorithm      algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     *                       SHA-256, SHA-384, SHA-512
     * @param asTypeMapper mapper to get hash string representation
     * @return mensaje encriptado
     */
    public static <T> T hashedTypeFor(String message, HashAlgorithm algorithm, Provider provider, Function<byte[], T> asTypeMapper) {
        byte[] digest = null;
        try {
            digest = algorithm.hashFor(Objects.requireNonNull(message), provider);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return asTypeMapper.apply(Objects.requireNonNull(digest));
    }

    public enum HashAlgorithm {
        MD2 {
            @Override
            protected MessageDigest algorithmInstance() throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("MD2");
            }

            @Override
            protected MessageDigest algorithmInstance(Provider provider) throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("MD2", provider);
            }

        }, SHA1 {
            @Override
            protected MessageDigest algorithmInstance() throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-1");
            }

            @Override
            protected MessageDigest algorithmInstance(Provider provider) throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-1", provider);
            }
        }, SHA256 {
            @Override
            protected MessageDigest algorithmInstance() throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-256");
            }

            @Override
            protected MessageDigest algorithmInstance(Provider provider) throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-256", provider);
            }
        }, SHA384 {
            @Override
            protected MessageDigest algorithmInstance() throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-384");
            }

            @Override
            protected MessageDigest algorithmInstance(Provider provider) throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-384", provider);
            }
        }, SHA512 {
            @Override
            protected MessageDigest algorithmInstance() throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-512");
            }

            @Override
            protected MessageDigest algorithmInstance(Provider provider) throws NoSuchAlgorithmException {
                return MessageDigest.getInstance("SHA-512", provider);
            }
        };

        protected abstract MessageDigest algorithmInstance() throws NoSuchAlgorithmException;

        protected abstract MessageDigest algorithmInstance(Provider provider) throws NoSuchAlgorithmException;

        private byte[] hashFor(String stringToHash, Provider provider) throws NoSuchAlgorithmException, UnsupportedEncodingException {
            byte[] buffer = stringToHash.getBytes("UTF-8");
            MessageDigest algorithmInstance;
            if (provider == null)
                algorithmInstance = algorithmInstance();
            else
                algorithmInstance = algorithmInstance(provider);
            algorithmInstance.reset();
            algorithmInstance.update(buffer);
            return algorithmInstance.digest();
        }

    }
}
