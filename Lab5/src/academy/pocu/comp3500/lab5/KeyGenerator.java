package academy.pocu.comp3500.lab5;

import java.math.BigInteger;

public class KeyGenerator {
    public static boolean isPrime(final BigInteger number) {
        if (number.compareTo(BigInteger.ONE) <= 0 || number.compareTo(BigInteger.valueOf(4)) == 0) {
            return false;
        }
        if (number.compareTo(BigInteger.valueOf(3)) <= 0) {
            return true;
        }

        int[] arrPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int i = 0;
        BigInteger prime = BigInteger.valueOf(arrPrimes[0]);
        BigInteger n1 = new BigInteger(number.toString());
        n1 = n1.subtract(BigInteger.ONE);
        while (number.compareTo(prime) == 1) {
            BigInteger s = new BigInteger(n1.toString());

            while (true) {
                BigInteger r = prime.modPow(s, number);
                if (r.compareTo(n1) == 0) {
                    break;
                } else if (s.testBit(0)) {
                    if (r.compareTo(BigInteger.ONE) == 0) {
                        break;
                    } else {
                        return false;
                    }
                }
                s = s.shiftRight(1);
            }
            if (arrPrimes.length > ++i) {
                prime = BigInteger.valueOf(arrPrimes[i]);
            } else {
                break;
            }
        }

        return true;
    }
}