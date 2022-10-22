import java.math.BigInteger;
import java.security.SecureRandom;

public class myRSA {
    private static BigInteger privateKey;
    private static BigInteger publicKey;
    private static BigInteger n;
    private static BigInteger q;
    private static BigInteger p;
    private static BigInteger eiler;
    private static BigInteger e;
    private static BigInteger d;


    static void getKey()
    {
        p = BigInteger.probablePrime(1024, new SecureRandom());
        System.out.println("p: " + p);
        q = BigInteger.probablePrime(1024, new SecureRandom());
        System.out.println("q: " + q);
        n = p.multiply(q);
        System.out.println("modulus (n): " + n);
        eiler = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("eiler: " + eiler);
        e = new BigInteger("65537"); // common 
        System.out.println("e: " + e);
        d = e.modInverse(eiler);
        publicKey = e;
        System.out.println("publicKey: " + publicKey);
        privateKey = d; //publicKey.modInverse(eiler);
        System.out.println("privateKey: " + privateKey);
    }

    private static BigInteger findD(BigInteger e, BigInteger modulus)
    {
            BigInteger d;
            for (d = new BigInteger("3"); !d.equals(modulus) ; d = d.add(BigInteger.ONE)) {
                if (d.multiply(e).mod(modulus).equals(BigInteger.ONE)) {
                    return d;
                }
            }
            return d;
    }
    // (e*d) mod ((p-1)*(q-1))=1
    private static BigInteger findE(BigInteger d, BigInteger modulus)
    {
        BigInteger e = modulus.subtract(BigInteger.TEN);
        while (true)
        {
            if (((d.multiply(e)).mod(modulus)).equals(BigInteger.ONE))
            {
                return e;
            }
            else
                e = e.subtract(BigInteger.ONE);
        }
        //return e;
    }


    static BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, n);
    }

    static BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, n);
    }
}