package cu.pdi.bookstore.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Objects;
import java.util.function.Function;

public final class HashMaker {

    /**
     * Convert a bytes array into String using hexadecimal values
     *
     * @param digest bytes array
     * @return String
     */
    private static String toHexadecimal(byte[] digest) {
        StringBuilder hash = new StringBuilder();
        for (byte aux : digest) {
            int b = aux & 0xff;
            String hexString = Integer.toHexString(b);
            hash = hexString.length() == 1 ? hash.append("0") : hash.append(hexString);
        }
        return hash.toString();
    }

    /**
     *
     * Hash a text message through a hash algorithm.
     *
     * @param message   text to hash
     * @param algorithm hash algorithm, valid values: MD2, MD5, SHA-1,
     *                  SHA-256, SHA-384, SHA-512
     * @return Hashed message
     */
    public static String hashedTypeFor(String message, HashAlgorithm algorithm) {
        return hashedTypeFor(message, algorithm, null, HashMaker::toHexadecimal);
    }

    /**
     * Hash a text message through a hash algorithm implemented by the given provider.
     *
     * @param message   text encrypt
     * @param algorithm hash algorithm, valid values: MD2, MD5, SHA-1,
     *                  SHA-256, SHA-384, SHA-512
     * @param provider provider of hash algorithm implementation
     * @return hashed text
     */
    public static String hashedTypeFor(String message, HashAlgorithm algorithm, Provider provider) {
        return hashedTypeFor(message, algorithm, provider, HashMaker::toHexadecimal);
    }

    /**
     * Hash a text message through a hash algorithm implemented by the given provider
     * customizing the output with the specified mapper.
     *
     * @param message        text to hash
     * @param algorithm      hash algorithm, valid values: MD2, MD5, SHA-1,
     *                       SHA-256, SHA-384, SHA-512
     * @param asTypeMapper   mapper to get hash string representation
     * @return hashed message
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
